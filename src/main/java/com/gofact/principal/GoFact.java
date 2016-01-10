/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.principal;

import controlador.ControladorIngresoUsuario;
import presentacion.FrmInicioSesion;
import modelo.ModeloUsuario;

public class GoFact {
    public static void main(String[] args) {
        FrmInicioSesion vistaIU = new FrmInicioSesion();
        ModeloUsuario modelo = new ModeloUsuario();
        ControladorIngresoUsuario controlador = new ControladorIngresoUsuario(vistaIU, modelo);
        vistaIU.setVisible(true);
    }
}
