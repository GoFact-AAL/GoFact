/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import persistencia.exceptions.NonexistentEntityException;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.UsuarioJpaController;
import com.gofact.presentacion.usuarios.DialogModificarContrasena;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.gofact.soporte.Validador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ControladorModificarContrasena implements ActionListener{
    
    public DialogModificarContrasena vistaMC = new DialogModificarContrasena(null, true);
    public UsuarioJpaController modeloMU = new UsuarioJpaController(null);
    public Usuario usuario;

    public ControladorModificarContrasena(DialogModificarContrasena vistaMC
            , UsuarioJpaController modeloMU
            , Usuario usuario) {
        this.vistaMC = vistaMC;  
        this.modeloMU = modeloMU;
        this.usuario = usuario;
        this.vistaMC.getBtnAceptar().addActionListener(this);
        this.vistaMC.getBtnCancelar().addActionListener(this);
    }
    
     private boolean contrasenaUsuario(String contrasena){
        if(contrasena.equals(this.usuario.getPassword())){
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
        this.usuario.setPassword(contrasena);
    }
    
    private void modificarContrasena(){
        if(camposCorrectos()){
            try {
                actualizarContrasena();
                this.modeloMU.edit(this.usuario);
                this.vistaMC.mostrarMensaje("La contraseña ha sido modificada");
                this.vistaMC.dispose();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorModificarContrasena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorModificarContrasena.class.getName()).log(Level.SEVERE, null, ex);
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
