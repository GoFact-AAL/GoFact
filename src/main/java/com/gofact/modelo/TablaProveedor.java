/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.derby.client.am.ResultSet;

/**
 *
 * @author camm
 */
public class TablaProveedor extends Tabla {

    public ArrayList<Proveedor> listarProveedores(){
        Proveedor proveedor;
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        this.consulta = "SELECT IDPROVEEDOR, RUC, RAZONSOCIAL,"
                + " NOMBRECOMERCIAL, DIRECCION, TELEFONO,"
                + " PAIS, CIUDAD FROM PROVEEDOR";
        try {
            PreparedStatement pst = this.con.prepareStatement(consulta);
            ResultSet resultado = (ResultSet) pst.executeQuery();
            while (resultado.next()) {
                proveedor = new Proveedor();
                proveedor.setIdproveedor(resultado.getInt(1));
                proveedor.setRuc(resultado.getString(2));
                proveedor.setRazonsocial(resultado.getString(3));
                proveedor.setNombrecomercial(resultado.getString(4));
                proveedor.setDireccion(resultado.getString(5));
                proveedor.setTelefono(resultado.getString(6));
                proveedor.setPais(resultado.getString(7));
                proveedor.setCiudad(resultado.getString(8));
                proveedores.add(proveedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedores;
    }

    public Proveedor buscarProveedorPorRUC(String RUC){
        Proveedor proveedor = null;
        this.consulta = "SELECT IDPROVEEDOR, RUC, RAZONSOCIAL,"
                + " NOMBRECOMERCIAL, DIRECCION, TELEFONO,"
                + " PAIS, CIUDAD FROM PROVEEDOR WHERE RUC LIKE \'" + RUC + "\'";
        try {
            PreparedStatement pst = this.con.prepareStatement(consulta);
            ResultSet resultado = (ResultSet) pst.executeQuery();
            while (resultado.next()) {
                proveedor = new Proveedor();
                proveedor.setIdproveedor(resultado.getInt(1));
                proveedor.setRuc(resultado.getString(2));
                proveedor.setRazonsocial(resultado.getString(3));
                proveedor.setNombrecomercial(resultado.getString(4));
                proveedor.setDireccion(resultado.getString(5));
                proveedor.setTelefono(resultado.getString(6));
                proveedor.setPais(resultado.getString(7));
                proveedor.setCiudad(resultado.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }

    @Override
    public DefaultTableModel mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(Dato datos) {
        Proveedor proveedor = (Proveedor) datos;
        consulta = "INSERT INTO PROVEEDOR (RUC, RAZONSOCIAL, NOMBRECOMERCIAL,"
                    + " DIRECCION, TELEFONO, PAIS, CIUDAD)"
                    + " VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.con.prepareStatement(consulta);
            pst.setString(1, proveedor.getRuc());
            pst.setString(2, proveedor.getRazonsocial());
            pst.setString(3, proveedor.getNombrecomercial());
            pst.setString(4, proveedor.getDireccion());
            pst.setString(5, proveedor.getTelefono());
            pst.setString(6, proveedor.getPais());
            pst.setString(7, proveedor.getCiudad());

            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean editar(Dato datos) {
        Proveedor proveedor = (Proveedor) datos;
        consulta = "UPDATE PROVEEDOR SET RAZONSOCIAL = ?, NOMBRECOMERCIAL = ?,"
                    + " DIRECCION = ?, TELEFONO = ?, PAIS = ?, CIUDAD = ?"
                    + " WHERE RUC LIKE \'" + proveedor.getRuc() + "\'";

        try{
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, proveedor.getRazonsocial());
            pst.setString(2, proveedor.getNombrecomercial());
            pst.setString(3, proveedor.getDireccion());
            pst.setString(4, proveedor.getTelefono());
            pst.setString(5, proveedor.getPais());
            pst.setString(6, proveedor.getCiudad());
            return pst.executeUpdate() != 0;
        } catch(Exception ex){
            Logger.getLogger(TablaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar(Dato datos) {
        Proveedor proveedor = (Proveedor) datos;
        this.consulta = "DELETE FROM PROVEEDOR WHERE RUC LIKE \'" + proveedor.getRuc() + "\'";

        try {
            Statement st = this.con.createStatement();
            return st.executeUpdate(consulta) != 0;
        } catch (SQLException ex) {
            Logger.getLogger(TablaProveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
