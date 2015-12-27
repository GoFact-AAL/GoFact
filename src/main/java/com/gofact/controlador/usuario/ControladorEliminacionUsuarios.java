/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import com.gofact.controlador.ControladorIngresoUsuario;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import com.gofact.presentacion.FrmInicioSesion;
import com.gofact.presentacion.usuarios.DialogEliminarUsuario;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.jpacontroladores.UsuarioJpaController;
import persistencia.entidades.Usuario;

/**
 *
 * @author USRBDD
 */
public class ControladorEliminacionUsuarios implements ActionListener{

    public DialogEliminarUsuario vistaEliUsu = new DialogEliminarUsuario(null, true);
    public UsuarioJpaController modeloUsu = new UsuarioJpaController(null);
    public Usuario usuario;

    public ControladorEliminacionUsuarios(DialogEliminarUsuario vistaRU
            , UsuarioJpaController modeloRU
            , Usuario usuario) {
        this.vistaEliUsu = vistaRU;
        this.modeloUsu = modeloRU;
        this.usuario = usuario;
        this.vistaEliUsu.getBtnAceptar().addActionListener(this);
        this.vistaEliUsu.getBtnCancelar().addActionListener(this);
        
    }
    
    private void eliminarUsuario(){
        String contrasena = Cifrador
                    .sha(new String(this.vistaEliUsu.getPassContrasena().getPassword()).trim());
            if (contrasena.equals(this.usuario.getPassword())) {
                try {
                    this.modeloUsu.destroy(this.usuario.getIdusuario());
                    this.vistaEliUsu.mostrarMensaje("¡Correcto!");
                    this.vistaEliUsu.dispose();
                    this.vistaEliUsu.padre.dispose();
                    
                    FrmInicioSesion vista = new FrmInicioSesion();
                    UsuarioJpaController modelo = this.modeloUsu;
                    ControladorIngresoUsuario controlador = new ControladorIngresoUsuario(vista, modelo);
                    vista.setVisible(true);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(ControladorEliminacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ControladorEliminacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.vistaEliUsu.mostrarMensaje("Contraseña Incorrecta");
            }
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaEliUsu.getBtnAceptar()) {
            eliminarUsuario();
        }
        else if (ae.getSource() == this.vistaEliUsu.getBtnCancelar()) {
            this.vistaEliUsu.dispose();
        }
    }

}
