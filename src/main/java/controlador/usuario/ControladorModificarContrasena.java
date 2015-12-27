/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.usuario;

import persistencia.exceptions.NonexistentEntityException;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.UsuarioJpaController;
import presentacion.usuario.DialogModificarContrasena;
import soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import soporte.Validador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ControladorModificarContrasena implements ActionListener{
    
    public DialogModificarContrasena vistaModiContrasena = new DialogModificarContrasena(null, true);
    public UsuarioJpaController modeloUsuario = new UsuarioJpaController(null);
    public Usuario usuario;

    public ControladorModificarContrasena(DialogModificarContrasena vistaMC
            , UsuarioJpaController modeloMU
            , Usuario usuario) {
        this.vistaModiContrasena = vistaMC;  
        this.modeloUsuario = modeloMU;
        this.usuario = usuario;
        this.vistaModiContrasena.getBtnAceptar().addActionListener(this);
        this.vistaModiContrasena.getBtnCancelar().addActionListener(this);
    }
    
     private boolean contrasenaUsuario(String contrasena){
        if(contrasena.equals(this.usuario.getPassword())){
            return true;
        }
        else{
            this.vistaModiContrasena.mostrarMensaje("La contraseña actual no es la misma.");
            return false;
        }
    }
    
    //Nueva contraseña
    private boolean contrasenaValida(String contrasena) {
        if (Validador.contrasenaValida(contrasena)) {
            return true;
        }
        else {
            this.vistaModiContrasena.mostrarMensaje("Contraseña inválida.");
            return false;
        }
    }
    
    private boolean validarCoincidencia(String contrasena, String confirmacion) {
        if (contrasena.equals(confirmacion)) {
            return true;
        }
        else{
            this.vistaModiContrasena.mostrarMensaje("Las contraseñas no coinciden.");
            return false;
        }
    }
    //fin nueva contraseña
    
    private boolean camposCorrectos(){
        String contrasenaLeida = new String(this.vistaModiContrasena.getPassContrasena().getPassword()).trim();
        String contrasenaActual = Cifrador.sha(contrasenaLeida);
        String nuevaContrasena = new String(this.vistaModiContrasena.getPssNuevaContra().getPassword()).trim();
        String confirmacion = new String(this.vistaModiContrasena.getPassConfirmacion().getPassword()).trim();
        
        return contrasenaUsuario(contrasenaActual)
                && contrasenaValida(nuevaContrasena) 
                && validarCoincidencia(nuevaContrasena, confirmacion);
    }
    
    
    private void actualizarContrasena(){
        String contrasena = Cifrador.sha((new String(this.vistaModiContrasena.getPssNuevaContra().getPassword())).trim());
        this.usuario.setPassword(contrasena);
    }
    
    private void modificarContrasena(){
        if(camposCorrectos()){
            try {
                actualizarContrasena();
                this.modeloUsuario.edit(this.usuario);
                this.vistaModiContrasena.mostrarMensaje("La contraseña ha sido modificada");
                this.vistaModiContrasena.dispose();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorModificarContrasena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorModificarContrasena.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaModiContrasena.getBtnAceptar()) {
            modificarContrasena();
        }
        else if (e.getSource() == this.vistaModiContrasena.getBtnCancelar()) {
            this.vistaModiContrasena.dispose();
        }
    }
}
