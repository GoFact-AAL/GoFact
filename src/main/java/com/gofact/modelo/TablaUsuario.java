/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean editar(Dato dato) {
        Usuario usr = (Usuario) dato;
        consulta = "UPDATE USUARIO SET "
                + " NOMBRE = ?,"
                + " APELLIDO = ?"
                + " WHERE CEDULAIDENTIDAD LIKE \'" + usr.getCedula() + "'";
        try {
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, usr.getNombre());
            pst.setString(2, usr.getApellido());
            return pst.executeUpdate() != 0;
        } catch (Exception ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(Dato datos) {
        Usuario usr = (Usuario) datos;
        consulta = "DELETE FROM USUARIO WHERE CEDULAIDENTIDAD = \'" + usr.getCedula() + "'";
        try {
            Statement sentencia = con.createStatement();
            sentencia.execute(consulta);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
    }

    public Usuario obtenerUsuarioPorCedula(String cedula){
        Usuario usr = null;
        consulta = "SELECT * FROM USUARIO WHERE CEDULAIDENTIDAD = \'" + cedula + "'";
        try {
            Statement sentencia = con.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);

            while (resultado.next()) {
                usr = new Usuario();
                usr.setCedula(resultado.getString("CEDULAIDENTIDAD"));
                usr.setNombre(resultado.getString("NOMBRE"));
                usr.setApellido(resultado.getString("APELLIDO"));
                usr.setContrasena(resultado.getString("PASSWORD"));
                usr.setPregunta1(resultado.getInt("PREGUNTA1"));
                usr.setPregunta2(resultado.getInt("PREGUNTA2"));
                usr.setRespuesta1(resultado.getString("RESPUESTA1"));
                usr.setRespuesta2(resultado.getString("RESPUESTA2"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }

    public boolean editarContrasena(Usuario usuario) {
    Usuario nuevousuario = usuario;
        consulta = "UPDATE USUARIO SET PASSWORD = ?"
                    + " WHERE CEDULAIDENTIDAD LIKE \'" + nuevousuario.getCedula() + "\'";

        try{
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, nuevousuario.getContrasena());
         
            return pst.executeUpdate() != 0;
        } catch(Exception ex){
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
