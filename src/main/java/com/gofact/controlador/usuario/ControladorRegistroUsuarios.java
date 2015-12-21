/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import persistencia.entidades.Usuario;
import com.gofact.presentacion.usuarios.DialogRegistroUsuario;
import com.gofact.soporte.Cifrador;
import com.gofact.soporte.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import persistencia.jpacontroladores.UsuarioJpaController;

/**
 *
 * @author camm
 */
public class ControladorRegistroUsuarios implements ActionListener{
    public DialogRegistroUsuario vistaRU = new DialogRegistroUsuario(null, true);
    public UsuarioJpaController modeloRU = new UsuarioJpaController(null);

    public ControladorRegistroUsuarios(DialogRegistroUsuario vistaRU,
            UsuarioJpaController modeloRU) {
        this.vistaRU = vistaRU;
        this.modeloRU = modeloRU;
        this.vistaRU.getBtnGuardar().addActionListener(this);
        this.vistaRU.getBtnCancelar().addActionListener(this);
    }

    private boolean validarCampos(){
        return sinCamposVacios()
                && cedulaValida(this.vistaRU.getTxtCedula().getText())
                && contrasenaValidada(new String(this.vistaRU.getPassContrasena().getPassword()))
                && validarCoincidencia(new String(this.vistaRU.getPassContrasena().getPassword()),
                        new String(this.vistaRU.getPassConfirmacion().getPassword()))
                && validarCedulaUnica(this.vistaRU.getTxtCedula().getText());
    }

    private boolean sinCamposVacios(){
        return nombreLleno() && apellidoLleno();
    }

    private boolean nombreLleno(){
        if(!this.vistaRU.getTxtNombre().getText().equals("")){
            return true;
        }
        else{
            this.vistaRU.mostrarMensaje("El nombre no puede estar vacio.");
            return false;
        }
    }

    private boolean apellidoLleno(){
        if(!this.vistaRU.getTxtApellido().getText().equals("")){
            return true;
        }
        else{
            this.vistaRU.mostrarMensaje("El apellido no puede estar vacio.");
            return false;
        }
    }

    private boolean cedulaValida(String cedula){
        if (Validador.cedulaValida(cedula)) {
            return true;
        }
        else{
            this.vistaRU.mostrarMensaje("La cedula es incorrecta.");
            return false;
        }
    }

    private boolean contrasenaValidada(String contrasena) {
        if (Validador.contrasenaValida(contrasena)) {
            return true;
        }
        else {
            this.vistaRU.mostrarMensaje("Contraseña incorrecta.");
            return false;
        }
    }

    private boolean validarCoincidencia(String contrasena, String confirmacion) {
        if (contrasena.equals(confirmacion)) {
            return true;
        }
        else{
            this.vistaRU.mostrarMensaje("Las contraseñas no coinciden.");
            return false;
        }
    }

    private Usuario obtenerUsuario() {
        Usuario usrNuevo = new Usuario();
        usrNuevo.setNombre(this.vistaRU.getTxtNombre().getText().trim());
        usrNuevo.setApellido(this.vistaRU.getTxtApellido().getText().trim());
        usrNuevo.setCedulaidentidad(this.vistaRU.getTxtCedula().getText().trim());
        usrNuevo.setPassword(Cifrador.sha(
                new String(this.vistaRU.getPassContrasena().getPassword()).trim()));
        usrNuevo.setRespuesta1(this.vistaRU.getTxtResp1().getText().trim());
        usrNuevo.setRespuesta2(this.vistaRU.getTxtResp2().getText().trim());
        usrNuevo.setPregunta1(this.vistaRU.getCmbPregunta1().getSelectedIndex());
        usrNuevo.setPregunta2(this.vistaRU.getCmbPregunta2().getSelectedIndex());
        return usrNuevo;
    }

    private boolean validarCedulaUnica(String ci) {
        Usuario enBase = this.modeloRU.findUserByCI(ci);
        if (enBase != null) {
            this.vistaRU.mostrarMensaje("El usuario con esa cédula ya está registrado");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaRU.getBtnGuardar()) {
            if (validarCampos()) {
                Usuario usr = obtenerUsuario();
                this.modeloRU.create(usr);
                this.vistaRU.mostrarMensaje("¡Correcto!");
            }
        }
        else if (ae.getSource() == this.vistaRU.getBtnCancelar()) {
            this.vistaRU.dispose();
        }
    }
}
