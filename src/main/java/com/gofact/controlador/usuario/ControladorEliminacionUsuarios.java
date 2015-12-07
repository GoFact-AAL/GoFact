/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import com.gofact.modelo.TablaUsuario;
import com.gofact.modelo.Usuario;
import com.gofact.presentacion.usuarios.DialogEliminarUsuario;
import com.gofact.presentacion.usuarios.DialogRegistroUsuario;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USRBDD
 */
public class ControladorEliminacionUsuarios implements ActionListener{

    public DialogEliminarUsuario vistaRU = new DialogEliminarUsuario(null, true);
    public TablaUsuario modeloRU = new TablaUsuario();
    public Usuario usuario;
    
    public ControladorEliminacionUsuarios(DialogEliminarUsuario vistaRU,
            TablaUsuario modeloRU, Usuario usuario) {
        this.vistaRU = vistaRU;
        this.modeloRU = modeloRU;
        this.usuario = usuario;
        this.vistaRU.getBtnAceptar().addActionListener(this);
        this.vistaRU.getBtnCancelar().addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaRU.getBtnAceptar()) {
            
            String con =(Cifrador.md5(
                new String(this.vistaRU.getPassContrasena().getPassword()).trim()));
            if (con.equals(this.usuario.getContrasena())) {
                if (this.modeloRU.eliminar(this.usuario)) {
                    this.vistaRU.mostrarMensaje("¡Correcto!");
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
