/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.usuario;

import presentacion.usuario.DialogEliminarUsuario;
import com.gofact.principal.GoFact;
import soporte.Cifrador;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.UsuarioJpaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
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

    private void iniciarNuevaSesion(){
        GoFact.main(null);
    }

    private void cerrarPresentacionActual() {
        this.vistaRU.dispose();
        this.vistaRU.padre.dispose();
    }

    private boolean verificarCoincidencia() {
        String contrasena = new String(this.vistaRU.getPassContrasena().getPassword()).trim();
        String contrasenaCifrada = Cifrador.sha(contrasena);
        if(contrasenaCifrada.equals(this.usuario.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }

    private void eliminarUsuario() {
        if (verificarCoincidencia()) {
            try {
                this.modeloRU.destroy(this.usuario.getIdusuario());
                this.vistaRU.mostrarMensaje("¡Correcto!");
                cerrarPresentacionActual();
                iniciarNuevaSesion();
            } catch (IllegalOrphanException | NonexistentEntityException ex) {
                Logger.getLogger(ControladorEliminacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            this.vistaRU.mostrarMensaje("Contraseña Incorrecta");
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaRU.getBtnAceptar()) {
            eliminarUsuario();
        }
        else if (ae.getSource() == this.vistaRU.getBtnCancelar()) {
            this.vistaRU.dispose();
        }
    }
}
