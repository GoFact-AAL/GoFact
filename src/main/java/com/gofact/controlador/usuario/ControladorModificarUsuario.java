/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import com.gofact.controlador.ControladorIngresoUsuario;
import com.gofact.modelo.TablaUsuario;
import com.gofact.modelo.Usuario;
import com.gofact.presentacion.FrmInicioSesion;
import com.gofact.presentacion.usuarios.DialogEditarInformacionUsuario;
import com.gofact.soporte.Cifrador;
import com.gofact.soporte.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USRSYS
 */
public class ControladorModificarUsuario implements ActionListener{
    
    public DialogEditarInformacionUsuario vistaMU = new DialogEditarInformacionUsuario(null, true);
    public TablaUsuario modeloMU = new TablaUsuario();
    public Usuario usuario;

    public ControladorModificarUsuario(DialogEditarInformacionUsuario vistaMU,
            TablaUsuario modeloMU, Usuario usuario) {
        this.vistaMU = vistaMU;  
        this.modeloMU = modeloMU;
        this.usuario = usuario;
        this.vistaMU.getBtnAceptar().addActionListener(this);
        this.vistaMU.getBtnCancelar().addActionListener(this);
    }
    
    private boolean cedulaLleno(){
        if(!this.vistaMU.getTxtCedula().getText().equals("")){
            return true;
        }
        else{
            this.vistaMU.mostrarMensaje("La cédula no puede estar vacía.");
            return false;
        }
    }
    
    private boolean contrasenaActualLleno(){
        if(!this.vistaMU.getPassContrasena().getPassword().equals("")){
            return true;
        }
        else{
            this.vistaMU.mostrarMensaje("Debe ingresar la contraseña actual.");
            return false;
        }
    } 
    
    private boolean nuevaContrasenaLleno(){
        if(!this.vistaMU.getPssNuevaContra().getPassword().equals("")){
            return true;
        }
        else{
            this.vistaMU.mostrarMensaje("Debe ingresar una nueva contraseña.");
            return false;
        }
    } 
    
    private boolean confirmarContrasenaLleno(){
        if(!this.vistaMU.getPassConfirmacion().getPassword().equals("")){
            return true;
        }
        else{
            this.vistaMU.mostrarMensaje("Ingrese la contraseña de confirmación.");
            return false;
        }
    }
    
    private boolean camposLlenos(){
        return cedulaLleno() && contrasenaActualLleno() && nuevaContrasenaLleno()
                && confirmarContrasenaLleno();
    }
    
    private boolean cedulaValida(String cedula) {
        if (Validador.cedulaValida(cedula)) {
            return true;
        }
        else {
            this.vistaMU.mostrarMensaje("Cédula incorrecta.");
            return false;
        }
    }
    
    private boolean contrasenaValida(String contrasena) {
        if (Validador.contrasenaValida(contrasena)) {
            return true;
        }
        else {
            this.vistaMU.mostrarMensaje("Contraseña incorrecta.");
            return false;
        }
    }
    
    private boolean validarCoincidencia(String contrasena, String confirmacion) {
        if (contrasena.equals(confirmacion)) {
            return true;
        }
        else{
            this.vistaMU.mostrarMensaje("Las contraseñas no coinciden.");
            return false;
        }
    }
    
    private boolean contrasenaUsuario(String contrasena){
        if(contrasena.equals(this.usuario.getContrasena())){
            return true;
        }
        else{
            this.vistaMU.mostrarMensaje("La contraseña actual no es la misma.");
            return false;
        }
    }
    
    private boolean camposCorrectos(){
        String cedula = this.vistaMU.getTxtCedula().getText();
        String contrasenaActual = (Cifrador.md5(new String(this.vistaMU.
                getPassContrasena().getPassword()).trim()));
        String nuevaContrasena = new String(this.vistaMU.
                getPssNuevaContra().getPassword());
        String confirmacion = new String(this.vistaMU.
                getPassConfirmacion().getPassword());
        
        return camposLlenos() && cedulaValida(cedula) && contrasenaUsuario(contrasenaActual) 
               && contrasenaValida(nuevaContrasena) && validarCoincidencia(nuevaContrasena, confirmacion);
    }
    
    private void modificarUsuario(){
        if(camposCorrectos()){
            if(this.modeloMU.editar(this.usuario)){
                this.vistaMU.mostrarMensaje("Su contraseña ha sido modificada");
            }
        }
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaMU.getBtnAceptar()) {
            modificarUsuario();
        }
        else if (e.getSource() == this.vistaMU.getBtnCancelar()) {
            this.vistaMU.dispose();
        }
    }
}
