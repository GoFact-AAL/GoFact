/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import com.gofact.soporte.Validador;
import com.gofact.modelo.TablaUsuario;
import com.gofact.modelo.Usuario;
import com.gofact.presentacion.usuarios.DialogModificarContrasena;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author root
 */
public class ControladorModificarContrasena implements ActionListener{
    
    public DialogModificarContrasena vistaMC = new DialogModificarContrasena(null, true);
    public TablaUsuario modeloMU = new TablaUsuario();
    public Usuario usuario;

    public ControladorModificarContrasena(DialogModificarContrasena vistaMC,
            TablaUsuario modeloMU, Usuario usuario) {
        this.vistaMC = vistaMC;  
        this.modeloMU = modeloMU;
        this.usuario = usuario;
        this.vistaMC.getBtnAceptar().addActionListener(this);
        this.vistaMC.getBtnCancelar().addActionListener(this);
    }
    
     private boolean contrasenaUsuario(String contrasena){
        if(contrasena.equals(this.usuario.getContrasena())){
            return true;
        }
        else{
            this.vistaMC.mostrarMensaje("La contraseña actual no es la misma.");
            return false;
        }
    }
    
    //Nueva contraseña
    private boolean contrasenaValida(String contrasena) {
        if (Validador.contrasenaValida(contrasena)) {
            return true;
        }
        else {
            this.vistaMC.mostrarMensaje("Contraseña incorrecta.");
            return false;
        }
    }
    
    private boolean validarCoincidencia(String contrasena, String confirmacion) {
        if (contrasena.equals(confirmacion)) {
            return true;
        }
        else{
            this.vistaMC.mostrarMensaje("Las contraseñas no coinciden.");
            return false;
        }
    }
    //fin nueva contraseña
    
    private boolean camposCorrectos(){
        String contrasenaLeida = new String(this.vistaMC.getPassContrasena().getPassword()).trim();
        String contrasenaActual = Cifrador.md5(contrasenaLeida);
        String nuevaContrasena = new String(this.vistaMC.getPssNuevaContra().getPassword()).trim();
        String confirmacion = new String(this.vistaMC.getPassConfirmacion().getPassword()).trim();
        
        return contrasenaUsuario(contrasenaActual)
                && contrasenaValida(nuevaContrasena) 
                && validarCoincidencia(nuevaContrasena, confirmacion);
    }
    
    
    private void actualizarContrasena(){
        String contrasena = Cifrador.md5((new String(this.vistaMC.getPssNuevaContra().getPassword())).trim());
        this.usuario.setContrasena(contrasena);
    }
    
    private void modificarContrasena(){
        if(camposCorrectos()){
            actualizarContrasena();
            if(this.modeloMU.editarContrasena(this.usuario)){
                this.vistaMC.mostrarMensaje("La contraseña ha sido modificada");
                this.vistaMC.dispose();
            }
        }
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaMC.getBtnAceptar()) {
            modificarContrasena();
        }
        else if (e.getSource() == this.vistaMC.getBtnCancelar()) {
            this.vistaMC.dispose();
        }
    }
}
