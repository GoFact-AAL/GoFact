/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.principal;

import com.gofact.controlador.ControladorIngresoUsuario;
import com.gofact.modelo.TablaUsuario;
import com.gofact.presentacion.FrmInicioSesion;

/**
 *
 * @author camm
 */
public class GoFact {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmInicioSesion vistaIU = new FrmInicioSesion();
        TablaUsuario modelo = new TablaUsuario();
        ControladorIngresoUsuario controlador =
                new ControladorIngresoUsuario(vistaIU, modelo);
        vistaIU.setVisible(true);
    }
}
