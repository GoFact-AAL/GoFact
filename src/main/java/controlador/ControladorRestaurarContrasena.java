/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import presentacion.DialogRestaurarContrasena;
import soporte.Cifrador;
import modelo.persistencia.entidades.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ModeloUsuario;

/**
 *
 * @author GoFact
 */
public class ControladorRestaurarContrasena implements ActionListener {
    private final ModeloUsuario modeloRC;
    private final DialogRestaurarContrasena vistaRC;
    private Usuario usuarioARestaurar;

    public ControladorRestaurarContrasena(DialogRestaurarContrasena vistaRC
            , ModeloUsuario modeloRC){
        this.vistaRC = vistaRC;
        this.modeloRC = modeloRC;

        this.vistaRC.getBtnAceptar().addActionListener(this);
        this.vistaRC.getBtnCancelar().addActionListener(this);
        this.vistaRC.getBtnVerificar().addActionListener(this);
    }

    private void verificarContraseña() {
        if (usuarioEnElSistema()) {
            habilitarControles();
        }
    }

    private void habilitarControles(){
        this.vistaRC.getCmbPregunta1().setSelectedIndex(this.usuarioARestaurar.getPregunta1());
        this.vistaRC.getCmbPregunta2().setSelectedIndex(this.usuarioARestaurar.getPregunta2());
        this.vistaRC.getTxtResp1().setEnabled(true);
        this.vistaRC.getTxtResp2().setEnabled(true);
    }

    private boolean usuarioEnElSistema(){
        String cedulaIdentidad = this.vistaRC.getTxtCedula().getText();
        this.usuarioARestaurar = this.modeloRC.findUserByCI(cedulaIdentidad);

        if(this.usuarioARestaurar == null) {
            this.vistaRC.mostrarMensaje("El usuario no está registrado");
            return false;
        }
        else {
            return true;
        }
    }

    private boolean respuestasValidas(){
        if(this.usuarioARestaurar.getRespuesta1().equals(this.vistaRC.getTxtResp1().getText())
                    && this.usuarioARestaurar.getRespuesta2().equals(this.vistaRC.getTxtResp2().getText())){
            return true;
        }
        else{
            this.vistaRC.mostrarMensaje("No se pudo cambiar la contraseña.\nVerifique sus respuestas.");
            return false;
        }
    }

    private boolean camposValidos(){
        return usuarioEnElSistema()
                && respuestasValidas();
    }

    private void cambiarContrasena(){
        if (camposValidos()) {
            try {
                usuarioARestaurar.setPassword(Cifrador.sha(usuarioARestaurar.getCedulaidentidad()));
                this.modeloRC.edit(usuarioARestaurar);
                this.vistaRC.mostrarMensaje("Su nueva contraseña es: "+ usuarioARestaurar.getCedulaidentidad()
                    + "\nPor su seguridad, por favor cambiela.");
                this.vistaRC.dispose();
            } catch (Exception ex) {
                Logger.getLogger(ControladorRestaurarContrasena.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cancelar() {
        this.vistaRC.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vistaRC.getBtnAceptar()){
            cambiarContrasena();
        }
        else if (e.getSource() == this.vistaRC.getBtnCancelar()){
            cancelar();
        }
        else if (e.getSource() == this.vistaRC.getBtnVerificar()){
            verificarContraseña();
        }
    }
}
