/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador;

import com.gofact.controlador.usuario.ControladorRegistroUsuarios;
import com.gofact.presentacion.DialogRestaurarContrasena;
import com.gofact.presentacion.FrmInicioSesion;
import com.gofact.presentacion.FrmMenuPrincipal;
import com.gofact.presentacion.usuarios.DialogRegistroUsuario;
import com.gofact.soporte.Cifrador;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.UsuarioJpaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author camm
 */
public class ControladorIngresoUsuario implements ActionListener, MouseListener{
    public EntityManagerFactory emf;
    public FrmInicioSesion vistaIU;
    public UsuarioJpaController modeloUsuario;
    private Usuario usuarioIngresado;

    public ControladorIngresoUsuario(FrmInicioSesion vistaIU
            , UsuarioJpaController modeloUsuario
            , EntityManagerFactory emf) {

        this.vistaIU = vistaIU;
        this.modeloUsuario = modeloUsuario;
        this.emf = emf;

        this.vistaIU.getBtnIngresar().addActionListener(this);
        this.vistaIU.getBtnRegistrarse().addActionListener(this);
        this.vistaIU.getBtnSalir().addActionListener(this);
        this.vistaIU.getLblOlvidoContrasena().addMouseListener(this);
    }

    private boolean camposValidos() {
        this.usuarioIngresado =
                this.modeloUsuario.findUserByCI(this.vistaIU.getTxtCedulaIdentidad().getText());
        String contrasena = new String(this.vistaIU.getPassContrasena().getPassword());
        return this.usuarioIngresado != null && this.usuarioIngresado.getPassword().equals(Cifrador.sha(contrasena));
    }

    private void ingresar() {
        if(camposValidos()){
            FrmMenuPrincipal vistaPrincipal = new FrmMenuPrincipal(this.usuarioIngresado);
            ControladorMenuPrincipal controlPrincipal =
                new ControladorMenuPrincipal(vistaPrincipal, this.emf, this.usuarioIngresado);
            vistaPrincipal.setVisible(true);
            this.vistaIU.dispose();
        }
        else{
            this.vistaIU.mostrarMensaje("Credenciales incorrectas");
        }
    }

    private void registrarse() {
        DialogRegistroUsuario vistaRU = new DialogRegistroUsuario(this.vistaIU, true);
        UsuarioJpaController modeloRU = new UsuarioJpaController(emf);
        ControladorRegistroUsuarios controladorUsuario =
            new ControladorRegistroUsuarios(vistaRU, modeloRU);
        vistaRU.setVisible(true);
    }

    private void salir(){
        System.exit(0);
    }

    private void restaurarContrasena() {
        DialogRestaurarContrasena vistaRC =
            new DialogRestaurarContrasena (this.vistaIU, true);
        UsuarioJpaController modeloRC = new UsuarioJpaController(this.emf);
        ControladorRestaurarContrasena controladorRC =
            new ControladorRestaurarContrasena(vistaRC, modeloRC);
        vistaRC.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.vistaIU.getBtnIngresar()){
            ingresar();
        }
        else if(ae.getSource() == this.vistaIU.getBtnRegistrarse()){
            registrarse();
        }
        else if(ae.getSource() == this.vistaIU.getBtnSalir()){
            salir();
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {
        if (me.getSource() == this.vistaIU.getLblOlvidoContrasena()) {
            restaurarContrasena();
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent me) {}

    @Override
    public void mouseReleased(java.awt.event.MouseEvent me) {}

    @Override
    public void mouseEntered(java.awt.event.MouseEvent me) {}

    @Override
    public void mouseExited(java.awt.event.MouseEvent me) {}

}
