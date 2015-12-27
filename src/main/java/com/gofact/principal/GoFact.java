/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.principal;

import com.gofact.controlador.ControladorIngresoUsuario;
import com.gofact.presentacion.FrmInicioSesion;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.jpacontroladores.UsuarioJpaController;

/**
 *
 * @author camm
 */
public class GoFact {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_GoFact_jar_1.0PU");
        FrmInicioSesion vistaIU = new FrmInicioSesion();
        UsuarioJpaController modelo = new UsuarioJpaController(emf);
        ControladorIngresoUsuario controlador =
                new ControladorIngresoUsuario(vistaIU, modelo, emf);
        vistaIU.setVisible(true);
    }
}
