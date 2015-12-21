/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador;

import persistencia.exceptions.NonexistentEntityException;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.UsuarioJpaController;
import com.gofact.presentacion.DialogRestaurarContrasena;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GoFact
 */
public class ControladorRestaurarContrasena implements ActionListener {
    //Definicion modelo
    UsuarioJpaController modeloRC = new UsuarioJpaController(null);
    DialogRestaurarContrasena vistaRC = new DialogRestaurarContrasena(null, true);

    public ControladorRestaurarContrasena(DialogRestaurarContrasena vistaRC
            , UsuarioJpaController modeloRC){
    this.vistaRC = vistaRC;
    this.modeloRC = modeloRC;
    this.vistaRC.getBtnAceptar().addActionListener(this);
    this.vistaRC.getBtnCancelar().addActionListener(this);
    this.vistaRC.getBtnVerificar().addActionListener(this);
    }

    private void verificarContraseña() {
        String ci = this.vistaRC.getTxtCedula().getText();
        Usuario usuario = this.modeloRC.findUserByCI(ci);

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
        Usuario usuario = this.modeloRC.findUserByCI(ci);

        if (usuario == null) {
            this.vistaRC.mostrarMensaje("El usuario no está registrado");
        }
        else {
            if (usuario.getRespuesta1().equals(this.vistaRC.getTxtResp1().getText())
                    && usuario.getRespuesta2().equals(this.vistaRC.getTxtResp2().getText())){
                try {
                    usuario.setPassword(Cifrador.sha256(usuario.getCedulaidentidad()));
                    this.modeloRC.edit(usuario);
                    this.vistaRC.mostrarMensaje("Su nueva contraseña es: "+ usuario.getCedulaidentidad()
                            + "\nPorfavor cambiela");
                    this.vistaRC.dispose();
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ControladorRestaurarContrasena.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorRestaurarContrasena.class.getName()).log(Level.SEVERE, null, ex);
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
