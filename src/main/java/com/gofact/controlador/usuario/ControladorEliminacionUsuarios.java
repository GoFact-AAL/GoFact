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

    public DialogEliminarUsuario vistaRU = new DialogEliminarUsuario(null, true);
    public UsuarioJpaController modeloRU = new UsuarioJpaController(null);
    public Usuario usuario;

    public ControladorEliminacionUsuarios(DialogEliminarUsuario vistaRU
            , UsuarioJpaController modeloRU
            , Usuario usuario) {
        this.vistaRU = vistaRU;
        this.modeloRU = modeloRU;
        this.usuario = usuario;
        this.vistaRU.getBtnAceptar().addActionListener(this);
        this.vistaRU.getBtnCancelar().addActionListener(this);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaRU.getBtnAceptar()) {
            String contrasena = Cifrador
                    .sha(new String(this.vistaRU.getPassContrasena().getPassword()).trim());
            if (contrasena.equals(this.usuario.getPassword())) {
                try {
                    this.modeloRU.destroy(this.usuario.getIdusuario());
                    this.vistaRU.mostrarMensaje("¡Correcto!");
                    this.vistaRU.dispose();
                    this.vistaRU.padre.dispose();
                    
                    FrmInicioSesion vista = new FrmInicioSesion();
                    UsuarioJpaController modelo = this.modeloRU;
                    ControladorIngresoUsuario controlador = new ControladorIngresoUsuario(vista, modelo);
                    vista.setVisible(true);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(ControladorEliminacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ControladorEliminacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.vistaRU.mostrarMensaje("Contraseña Incorrecta");
            }
        }
        else if (ae.getSource() == this.vistaRU.getBtnCancelar()) {
            this.vistaRU.dispose();
        }
    }

}
