/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.usuario;

import modelo.persistencia.entidades.Usuario;;
import presentacion.usuario.DialogEditarInformacionUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.ModeloUsuario;import presentacion.usuario.DialogEditarInformacionUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import modelo.ModeloUsuario;

/**
 *
 * @author USRSYS
 */
public class ControladorModificarInformacion implements ActionListener, KeyListener{
    
    public DialogEditarInformacionUsuario vistaEditUsuario;
    public ModeloUsuario modeloUsuario;
    public Usuario usuario;

    public ControladorModificarInformacion(DialogEditarInformacionUsuario vistaMU
            , ModeloUsuario modeloMU
            , Usuario usuario) {
        this.vistaEditUsuario = vistaMU;  
        this.modeloUsuario = modeloMU;
        this.usuario = usuario;

        this.vistaEditUsuario.getBtnAceptar().addActionListener(this);
        this.vistaEditUsuario.getBtnCancelar().addActionListener(this);
        this.vistaEditUsuario.getTxtNombre().addKeyListener(this);
        this.vistaEditUsuario.getTxtApellido().addKeyListener(this);
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
            actualizarInformacion();
            this.modeloUsuario.edit(this.usuario);
            this.vistaEditUsuario.mostrarMensaje("La información del usuario ha sido modificada");
            this.vistaEditUsuario.dispose();
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

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
        if (!(c < '0' || c > '9')) ke.consume();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
