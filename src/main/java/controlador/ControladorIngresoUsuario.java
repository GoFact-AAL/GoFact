/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.usuario.ControladorRegistroUsuarios;
import presentacion.DialogRestaurarContrasena;
import presentacion.FrmInicioSesion;
import presentacion.FrmMenuPrincipal;
import presentacion.usuario.DialogRegistroUsuario;
import soporte.Cifrador;
import modelo.persistencia.entidades.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import modelo.ModeloUsuario;

/**
 *
 * @author camm
 */
public class ControladorIngresoUsuario implements ActionListener, MouseListener, KeyListener{
    public FrmInicioSesion vistaIU;
    public ModeloUsuario modeloUsuario;
    private Usuario usuarioIngresado;

    public ControladorIngresoUsuario(FrmInicioSesion vistaIU
            , ModeloUsuario modeloUsuario) {

        this.vistaIU = vistaIU;
        this.modeloUsuario = modeloUsuario;

        this.vistaIU.getBtnIngresar().addActionListener(this);
        this.vistaIU.getBtnRegistrarse().addActionListener(this);
        this.vistaIU.getBtnSalir().addActionListener(this);
        this.vistaIU.getLblOlvidoContrasena().addMouseListener(this);
        this.vistaIU.getTxtCedulaIdentidad().addKeyListener(this);
    }

    private boolean camposValidos() {
        this.usuarioIngresado =
                this.modeloUsuario.findUserByCI(this.vistaIU.getTxtCedulaIdentidad().getText());
        String contrasena = new String(this.vistaIU.getPassContrasena().getPassword());
        return this.usuarioIngresado != null && this.usuarioIngresado.getPassword().equals(Cifrador.sha(contrasena));
    }

    private void ingresar() {
        if(camposValidos()){
            FrmMenuPrincipal vistaPrincipal = new FrmMenuPrincipal();
            ControladorMenuPrincipal controlPrincipal =
                new ControladorMenuPrincipal(vistaPrincipal, this.usuarioIngresado);
            vistaPrincipal.setVisible(true);
            this.vistaIU.dispose();
        }
        else{
            this.vistaIU.mostrarMensaje("Credenciales incorrectas");
        }
    }

    private void registrarse() {
        DialogRegistroUsuario vistaRU = new DialogRegistroUsuario(this.vistaIU, true);
        ControladorRegistroUsuarios controladorUsuario =
            new ControladorRegistroUsuarios(vistaRU, this.modeloUsuario);
        vistaRU.setVisible(true);
    }

    private void salir(){
        System.exit(0);
    }

    private void restaurarContrasena() {
        DialogRestaurarContrasena vistaRC =
            new DialogRestaurarContrasena (this.vistaIU, true);
        ModeloUsuario modeloRC = new ModeloUsuario();
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
    
    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getSource() == this.vistaIU.getTxtCedulaIdentidad()) {
            char c = ke.getKeyChar();
            if (c < '0' || c > '9') ke.consume();
	}
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

}
