/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.logica;

import com.gofact.datos.Dato;
import com.gofact.datos.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camm
 */
public class TablaUsuario extends  Tabla {

    @Override
    public DefaultTableModel mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(Dato dato) {
        Usuario usr = (Usuario) dato;
            consulta = "INSERT INTO USUARIO (CEDULAIDENTIDAD, NOMBRE, APELLIDO,"
                    + " PASSWORD, RESPUESTA1, RESPUESTA2, PREGUNTA1, PREGUNTA2)"
                    + " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.con.prepareStatement(consulta);
            pst.setString(1, usr.getCedula());
            pst.setString(2, usr.getNombre());
            pst.setString(3, usr.getApellido());
            pst.setString(4, usr.getContrasena());
            pst.setString(5, usr.getRespuesta1());
            pst.setString(6, usr.getRespuesta2());
            pst.setInt(7, usr.getPregunta1());
            pst.setInt(8, usr.getPregunta2());

            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean editar(Dato datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Dato datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
