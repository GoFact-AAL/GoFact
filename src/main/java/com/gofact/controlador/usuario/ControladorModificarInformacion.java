/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import persistencia.exceptions.NonexistentEntityException;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.UsuarioJpaController;
import com.gofact.presentacion.usuarios.DialogEditarInformacionUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USRSYS
 */
public class ControladorModificarInformacion implements ActionListener{
    
    public DialogEditarInformacionUsuario vistaEditUsuario = new DialogEditarInformacionUsuario(null, true);
    public UsuarioJpaController modeloUsuario = new UsuarioJpaController(null);
    public Usuario usuario;

    public ControladorModificarInformacion(DialogEditarInformacionUsuario vistaMU
            , UsuarioJpaController modeloMU
            , Usuario usuario) {
        this.vistaEditUsuario = vistaMU;  
        this.modeloUsuario = modeloMU;
        this.usuario = usuario;
        this.vistaEditUsuario.getBtnAceptar().addActionListener(this);
        this.vistaEditUsuario.getBtnCancelar().addActionListener(this);
    }

    private boolean nombreLleno() {
        if (this.vistaEditUsuario.getTxtNombre().getText().equals("")) {
            this.vistaEditUsuario.mostrarMensaje("Ingrese el nombre del usuario");
            return false;
        } else {
            return true;
        }
    }
    
    private boolean apellidoLleno() {
        if (this.vistaEditUsuario.getTxtApellido().getText().equals("")) {
            this.vistaEditUsuario.mostrarMensaje("Ingrese el apellido del usuario");
            return false;
        } else {
            return true;
        }
    }
    
    private boolean camposLlenos() {
        return nombreLleno() && apellidoLleno();
    }
    
    private void actualizarInformacion(){
        String nombre = this.vistaEditUsuario.getTxtNombre().getText().trim();
        String apellido = this.vistaEditUsuario.getTxtApellido().getText().trim();
        this.usuario.setNombre(nombre);
        this.usuario.setApellido(apellido);
    }
    
    private void modificarInformacion(){
        if(camposLlenos()){
            try {
                actualizarInformacion();
                this.modeloUsuario.edit(this.usuario);
                this.vistaEditUsuario.mostrarMensaje("La información del usuario ha sido modificada");
                this.vistaEditUsuario.dispose();
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorModificarInformacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorModificarInformacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaEditUsuario.getBtnAceptar()) {
            modificarInformacion();
        }
        else if (e.getSource() == this.vistaEditUsuario.getBtnCancelar()) {
            this.vistaEditUsuario.dispose();
        }
    }
}
