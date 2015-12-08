/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador;

import com.gofact.modelo.TablaUsuario;
import com.gofact.modelo.Usuario;
import com.gofact.presentacion.DialogRestaurarContrasena;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author GoFact
 */
public class ControladorRestaurarContrasena implements ActionListener {
    //Definicion modelo
    TablaUsuario modeloRC = new TablaUsuario();
    DialogRestaurarContrasena vistaRC = new DialogRestaurarContrasena(null, true);

    public ControladorRestaurarContrasena(DialogRestaurarContrasena vistaRC, TablaUsuario modeloRC){
    this.vistaRC = vistaRC;
    this.modeloRC = modeloRC;
    this.vistaRC.getBtnAceptar().addActionListener(this);
    this.vistaRC.getBtnCancelar().addActionListener(this);
    this.vistaRC.getBtnVerificar().addActionListener(this);
    }

    private void verificarContraseña() {
        String ci = this.vistaRC.getTxtCedula().getText();
        Usuario usuario = this.modeloRC.obtenerUsuarioPorCedula(ci);

        if (usuario == null) {
            this.vistaRC.mostrarMensaje("El usuario no está registrado");
        }
        else {
            habilitarControles();
            this.vistaRC.getCmbPregunta1().setSelectedIndex(usuario.getPregunta1());
            this.vistaRC.getCmbPregunta2().setSelectedIndex(usuario.getPregunta2());
        }
    }

    private void habilitarControles(){
        this.vistaRC.getCmbPregunta1().setEnabled(true);
        this.vistaRC.getCmbPregunta2().setEnabled(true);
        this.vistaRC.getTxtResp1().setEnabled(true);
        this.vistaRC.getTxtResp2().setEnabled(true);
    }

    private void cambiarContrasena(){
    String ci = this.vistaRC.getTxtCedula().getText();
        Usuario usuario = this.modeloRC.obtenerUsuarioPorCedula(ci);

        if (usuario == null) {
            this.vistaRC.mostrarMensaje("El usuario no está registrado");
        }
        else {
            if (usuario.getRespuesta1().equals(this.vistaRC.getTxtResp1().getText())
                    && usuario.getRespuesta2().equals(this.vistaRC.getTxtResp2().getText())){
                usuario.setContrasena(Cifrador.md5(usuario.getCedula()));
                //actualizando la base
                if(this.modeloRC.editarContrasena(usuario)){
                    this.vistaRC.mostrarMensaje("Su nueva contraseña es: "+ usuario.getCedula()
                    + "\nPorfavor cambiela");
                    this.vistaRC.dispose();
                }
            }
            else {
                this.vistaRC.mostrarMensaje("No se pudo cambiar la contraseña.\nVerifique sus respuestas.");
            }
        }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vistaRC.getBtnAceptar()){
            cambiarContrasena();
        }
        else if (e.getSource() == this.vistaRC.getBtnCancelar()){
            this.vistaRC.dispose();
        }
        else if (e.getSource() == this.vistaRC.getBtnVerificar()){
            verificarContraseña();
        }
    }
}
