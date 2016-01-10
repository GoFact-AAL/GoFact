/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.usuario;

import presentacion.usuario.DialogEliminarUsuario;
import com.gofact.principal.GoFact;
import soporte.Cifrador;
import modelo.persistencia.entidades.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloUsuario;

/**
 *
 * @author Jose
 */
public class ControladorEliminacionUsuarios implements ActionListener{

    public DialogEliminarUsuario vistaRU;
    public ModeloUsuario modeloRU;
    public Usuario usuario;

    public ControladorEliminacionUsuarios(DialogEliminarUsuario vistaRU
            , ModeloUsuario modeloRU
            , Usuario usuario) {
        this.vistaRU = vistaRU;
        this.modeloRU = modeloRU;
        this.usuario = usuario;

        this.vistaRU.getBtnAceptar().addActionListener(this);
        this.vistaRU.getBtnCancelar().addActionListener(this);
    }

    private void iniciarNuevaSesion(){
        GoFact.main(null);
    }

    private void cerrarPresentacionActual() {
        this.vistaRU.dispose();
        this.vistaRU.padre.dispose();
    }

    private boolean verificarCoincidencia() {
        String contrasena = new String(this.vistaRU.getPassContrasena().getPassword()).trim();
        String contrasenaCifrada = Cifrador.sha(contrasena);
        if(contrasenaCifrada.equals(this.usuario.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }

    private void eliminarUsuario() {
        if (verificarCoincidencia()) {
            this.modeloRU.destroy(this.usuario.getIdusuario());
            this.vistaRU.mostrarMensaje("¡Correcto!");
            cerrarPresentacionActual();
            iniciarNuevaSesion();
        }else{
            this.vistaRU.mostrarMensaje("Contraseña Incorrecta");
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaRU.getBtnAceptar()) {
            eliminarUsuario();
        }
        else if (ae.getSource() == this.vistaRU.getBtnCancelar()) {
            this.vistaRU.dispose();
        }
    }
}
