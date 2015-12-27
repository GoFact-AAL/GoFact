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
    public DialogRegistroUsuario vistaRegistroUsu = new DialogRegistroUsuario(null, true);
    public UsuarioJpaController modeloUsuario = new UsuarioJpaController(null);

    public ControladorRegistroUsuarios(DialogRegistroUsuario vistaRU,
            UsuarioJpaController modeloRU) {
        this.vistaRegistroUsu = vistaRU;
        this.modeloUsuario = modeloRU;
        this.vistaRegistroUsu.getBtnGuardar().addActionListener(this);
        this.vistaRegistroUsu.getBtnCancelar().addActionListener(this);
    }

    private boolean validarCampos(){
        return sinCamposVacios()
                && cedulaValida(this.vistaRegistroUsu.getTxtCedula().getText())
                && contrasenaValidada(new String(this.vistaRegistroUsu.getPassContrasena().getPassword()))
                && validarCoincidencia(new String(this.vistaRegistroUsu.getPassContrasena().getPassword()),
                        new String(this.vistaRegistroUsu.getPassConfirmacion().getPassword()))
                && validarCedulaUnica(this.vistaRegistroUsu.getTxtCedula().getText());
    }

    private boolean sinCamposVacios(){
        return nombreLleno() && apellidoLleno();
    }

    private boolean nombreLleno(){
        if(this.vistaRegistroUsu.getTxtNombre().getText().trim().equals("")){
            this.vistaRegistroUsu.mostrarMensaje("El nombre no puede estar vacío.");
            return false;
        }
        else{
            return true;
        }
        
    }

    private boolean apellidoLleno(){
        if(this.vistaRegistroUsu.getTxtApellido().getText().trim().equals("")){
            this.vistaRegistroUsu.mostrarMensaje("El apellido no puede estar vacío.");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean cedulaValida(String cedula){
        if (Validador.cedulaValida(cedula)) {
            return true;
        }
        else{
            this.vistaRegistroUsu.mostrarMensaje("La cedula es inválida.");
            return false;
        }
    }

    private boolean contrasenaValidada(String contrasena) {
        if (Validador.contrasenaValida(contrasena)) {
            return true;
        }
        else {
            this.vistaRegistroUsu.mostrarMensaje("Contraseña inválida.");
            return false;
        }
    }

    private boolean validarCoincidencia(String contrasena, String confirmacion) {
        if (contrasena.equals(confirmacion)) {
            return true;
        }
        else{
            this.vistaRegistroUsu.mostrarMensaje("Las contraseñas no coinciden.");
            return false;
        }
    }

    private Usuario obtenerUsuario() {
        Usuario usrNuevo = new Usuario();
        usrNuevo.setNombre(this.vistaRegistroUsu.getTxtNombre().getText().trim());
        usrNuevo.setApellido(this.vistaRegistroUsu.getTxtApellido().getText().trim());
        usrNuevo.setCedulaidentidad(this.vistaRegistroUsu.getTxtCedula().getText().trim());
        usrNuevo.setPassword(Cifrador.sha(new String(this.vistaRegistroUsu.getPassContrasena().getPassword()).trim()));
        usrNuevo.setRespuesta1(this.vistaRegistroUsu.getTxtResp1().getText().trim());
        usrNuevo.setRespuesta2(this.vistaRegistroUsu.getTxtResp2().getText().trim());
        usrNuevo.setPregunta1(this.vistaRegistroUsu.getCmbPregunta1().getSelectedIndex());
        usrNuevo.setPregunta2(this.vistaRegistroUsu.getCmbPregunta2().getSelectedIndex());
        return usrNuevo;
    }

    private boolean validarCedulaUnica(String ci) {
        Usuario enBase = this.modeloUsuario.findUserByCI(ci);
        if (enBase != null) {
            this.vistaRegistroUsu.mostrarMensaje("El usuario con esa cédula ya está registrado");
            return false;
        }
        else {
            return true;
        }
    }

    private void registroUsuario(){
        if (validarCampos()) {
                Usuario usr = obtenerUsuario();
                this.modeloUsuario.create(usr);
                this.vistaRegistroUsu.mostrarMensaje("¡Correcto!");
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaRegistroUsu.getBtnGuardar()) {
            registroUsuario();
        }
        else if (ae.getSource() == this.vistaRegistroUsu.getBtnCancelar()) {
            this.vistaRegistroUsu.dispose();
        }
    }
}
