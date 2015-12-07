/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador;

import com.gofact.presentacion.DialogAbout;
import com.gofact.presentacion.FrmInicioSesion;
import com.gofact.presentacion.FrmMenuPrincipal;
import com.gofact.presentacion.factura.DialogExportarXML;
import com.gofact.presentacion.factura.DialogInsertarFactura;
import com.gofact.presentacion.proveedores.DialogProv;
import com.gofact.presentacion.reportes.DialogGenerarReporte;
import com.gofact.presentacion.usuarios.DialogEditarInformacionUsuario;
import com.gofact.presentacion.usuarios.DialogEliminarUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author camm
 */
public class ControladorMenuPrincipal implements ActionListener{

    public FrmMenuPrincipal vista = new FrmMenuPrincipal(null);

    public ControladorMenuPrincipal(FrmMenuPrincipal vista) {
        this.vista = vista;
        this.vista.getLblBienvenida().setText("Bienvenido " + this.vista.getUsuarioIngresado().getNombre());
        this.vista.getMenuItemConsultarProveedor().addActionListener(this);
        this.vista.getMenuItemConsultarFactura().addActionListener(this);
        this.vista.getMenuItemExportarXML().addActionListener(this);
        this.vista.getMenuItemModificarUsuario().addActionListener(this);
        this.vista.getMenuItemModificarEliminar().addActionListener(this);
        this.vista.getMenuItemObtenerReporte().addActionListener(this);
        this.vista.getMenuItemAcerca().addActionListener(this);
        this.vista.getMenuItemCerrarSesion().addActionListener(this);
        this.vista.getMenuItemCerrarSistema().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.vista.getMenuItemConsultarProveedor()){
            consultaProveedor();
        }
        else if(ae.getSource() == this.vista.getMenuItemConsultarFactura()){
            consultaFactura();
        }
        else if(ae.getSource() == this.vista.getMenuItemExportarXML()){
            exportarXML();
        }
        else if(ae.getSource() == this.vista.getMenuItemModificarUsuario()){
            modificarUsuario();
        }
        else if(ae.getSource() == this.vista.getMenuItemModificarEliminar()){
            eliminarUsuario();
        }
        else if(ae.getSource() == this.vista.getMenuItemObtenerReporte()){
            obtenerReporte();
        }
        else if(ae.getSource() == this.vista.getMenuItemAcerca()){
            acerca();
        }
        else if(ae.getSource() == this.vista.getMenuItemCerrarSesion()){
            cerrarSesion();
        }
        else if(ae.getSource() == this.vista.getMenuItemCerrarSistema()){
            cerrarSistema();
        }
    }

    private void consultaProveedor() {
        DialogProv dialogProv = new DialogProv(this.vista, true);
        dialogProv.setVisible(true);
    }

    private void consultaFactura() {
        DialogInsertarFactura dialogInsertarFactura = new DialogInsertarFactura(this.vista, true);
        dialogInsertarFactura.setVisible(true);
    }

    private void exportarXML() {
        DialogExportarXML dexml = new DialogExportarXML(this.vista, true);
        dexml.setVisible(true);
    }

    private void modificarUsuario() {
        DialogEditarInformacionUsuario deiu = new DialogEditarInformacionUsuario(this.vista, true);
        deiu.setVisible(true);
    }

    private void eliminarUsuario() {
        DialogEliminarUsuario deu = new DialogEliminarUsuario(this.vista, true);
        deu.setVisible(true);
    }

    private void obtenerReporte() {
        DialogGenerarReporte dialogGenerarReporte = new DialogGenerarReporte(this.vista, true);
        dialogGenerarReporte.setVisible(true);
    }

    private void acerca() {
        DialogAbout about = new DialogAbout(this.vista, true);
        about.setVisible(true);
    }

    private void cerrarSesion() {
        this.vista.dispose();
        FrmInicioSesion frmInicioSesion = new FrmInicioSesion();
        frmInicioSesion.setVisible(true);
    }

    private void cerrarSistema() {
        this.vista.dispose();
        System.exit(0);
    }
}
