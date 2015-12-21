/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador;

import com.gofact.controlador.usuario.ControladorRegistroUsuarios;
import persistencia.entidades.Usuario;
import com.gofact.presentacion.DialogRestaurarContrasena;
import com.gofact.presentacion.FrmInicioSesion;
import com.gofact.presentacion.FrmMenuPrincipal;
import com.gofact.presentacion.usuarios.DialogRegistroUsuario;
import com.gofact.soporte.Cifrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.jpacontroladores.UsuarioJpaController;

/**
 *
 * @author camm
 */
public class ControladorIngresoUsuario implements ActionListener, MouseListener{
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_GoFact_jar_1.0PU");
    public FrmInicioSesion vistaIU = new FrmInicioSesion();
    public UsuarioJpaController modeloUsuario = new UsuarioJpaController(null);

    public ControladorIngresoUsuario(FrmInicioSesion vistaIU
            , UsuarioJpaController modeloUsuario) {
        this.vistaIU = vistaIU;
        this.modeloUsuario = modeloUsuario;
        this.vistaIU.getBtnIngresar().addActionListener(this);
        this.vistaIU.getBtnRegistrarse().addActionListener(this);
        this.vistaIU.getBtnSalir().addActionListener(this);
        this.vistaIU.getLblOlvidoContrasena().addMouseListener(this);
    }

    private boolean validarCampos() {
        Usuario usuario =
                this.modeloUsuario.findUserByCI(this.vistaIU.getTxtCedulaIdentidad().getText());
        String contrasena = new String(this.vistaIU.getPassContrasena().getPassword());
        return usuario != null && usuario.getPassword().equals(Cifrador.sha(contrasena));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.vistaIU.getBtnIngresar()){
            if(validarCampos()){
                Usuario usuario =
                        this.modeloUsuario.findUserByCI(this.vistaIU.getTxtCedulaIdentidad().getText());
                FrmMenuPrincipal principal = new FrmMenuPrincipal(usuario);
                ControladorMenuPrincipal controlPrincipal =
                        new ControladorMenuPrincipal(principal);
                principal.setVisible(true);
                this.vistaIU.dispose();
            }
            else{
                this.vistaIU.mostrarMensaje("Credenciales incorrectas");
            }
        }
        else if(ae.getSource() == this.vistaIU.getBtnRegistrarse()){
            DialogRegistroUsuario vistaRU = new DialogRegistroUsuario(this.vistaIU, true);
            UsuarioJpaController modeloRU = new UsuarioJpaController(emf);
            ControladorRegistroUsuarios controladorUsuario =
                    new ControladorRegistroUsuarios(
                            vistaRU
                            , modeloRU);

            vistaRU.setVisible(true);
        }
        else if(ae.getSource() == this.vistaIU.getBtnSalir()){
            this.vistaIU.dispose();
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent me) {
        if (me.getSource() == this.vistaIU.getLblOlvidoContrasena()) {
            DialogRestaurarContrasena vistaRC =
                            new DialogRestaurarContrasena (this.vistaIU, true);
            UsuarioJpaController modeloRC = new UsuarioJpaController(this.emf);
            ControladorRestaurarContrasena controladorRC = new ControladorRestaurarContrasena(vistaRC, modeloRC);
            
            vistaRC.setVisible(true);
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
