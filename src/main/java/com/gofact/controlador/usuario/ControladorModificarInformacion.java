/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.usuario;

import com.gofact.modelo.TablaUsuario;
import com.gofact.modelo.Usuario;
import com.gofact.presentacion.usuarios.DialogEditarInformacionUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author USRSYS
 */
public class ControladorModificarInformacion implements ActionListener{
    
    public DialogEditarInformacionUsuario vistaMU = new DialogEditarInformacionUsuario(null, true);
    public TablaUsuario modeloMU = new TablaUsuario();
    public Usuario usuario;

    public ControladorModificarInformacion(DialogEditarInformacionUsuario vistaMU,
            TablaUsuario modeloMU, Usuario usuario) {
        this.vistaMU = vistaMU;  
        this.modeloMU = modeloMU;
        this.usuario = usuario;
        this.vistaMU.getBtnAceptar().addActionListener(this);
        this.vistaMU.getBtnCancelar().addActionListener(this);
    }

    private boolean nombreLleno() {
        if (this.vistaMU.getTxtNombre().getText().equals("")) {
            this.vistaMU.mostrarMensaje("Ingrese el nombre del usuario");
            return false;
        } else {
            return true;
        }
    }
    
    private boolean apellidoLleno() {
        if (this.vistaMU.getTxtApellido().getText().equals("")) {
            this.vistaMU.mostrarMensaje("Ingrese el apellido del usuario");
            return false;
        } else {
            return true;
        }
    }
    
    private boolean camposLlenos() {
        return nombreLleno() && apellidoLleno();
    }
    
    private void actualizarInformacion(){
        String nombre = this.vistaMU.getTxtNombre().getText().trim();
        String apellido = this.vistaMU.getTxtApellido().getText().trim();
        this.usuario.setNombre(nombre);
        this.usuario.setApellido(apellido);
    }
    
    private void modificarInformacion(){
        if(camposLlenos()){
            actualizarInformacion();
            if(this.modeloMU.editar(this.usuario)){
                this.vistaMU.mostrarMensaje("La información del usuario ha sido modificada");
                this.vistaMU.dispose();
            }
        }
    }   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaMU.getBtnAceptar()) {
            modificarInformacion();
        }
        else if (e.getSource() == this.vistaMU.getBtnCancelar()) {
            this.vistaMU.dispose();
        }
    }
}
