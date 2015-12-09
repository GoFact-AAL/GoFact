/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import com.gofact.modelo.TablaUsuario;
import com.gofact.modelo.Usuario;
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
        String contrasenaLeida = new String(this.vistaMU.getPassContrasena().getPassword()).trim();
        String contrasenaActual = Cifrador.md5(contrasenaLeida);
        String nuevaContrasena = new String(this.vistaMU.getPssNuevaContra().getPassword());
        String confirmacion = new String(this.vistaMU.getPassConfirmacion().getPassword());
        
        return contrasenaUsuario(contrasenaActual)
                && contrasenaValida(nuevaContrasena) 
                && validarCoincidencia(nuevaContrasena, confirmacion);
    }
    
    private void modificarUsuario(){
        if(camposCorrectos()){
            String nombre = this.vistaMU.getTxtNombre().getText().trim();
            String apellido = this.vistaMU.getTxtApellido().getText().trim();
            String contrasena = Cifrador.md5((new String(this.vistaMU.getPassContrasena().getPassword())).trim());
            this.usuario.setNombre(nombre);
            this.usuario.setApellido(apellido);
            this.usuario.setContrasena(contrasena);
            if(this.modeloMU.editar(this.usuario)){
                this.vistaMU.mostrarMensaje("Su contraseña ha sido modificada");
                this.vistaMU.dispose();
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
