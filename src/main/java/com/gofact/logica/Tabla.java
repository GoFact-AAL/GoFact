/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.logica;

import com.gofact.datos.Dato;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camm
 */
public abstract class Tabla {
    protected final Connection con;
    protected String consulta;

    public Tabla(){
        con = (new Conexion()).getCon();
        consulta = "";
    }

    public abstract DefaultTableModel mostrar();
    public abstract boolean insertar(Dato datos);
    public abstract boolean editar(Dato datos);
    public abstract boolean eliminar(Dato datos);
}
