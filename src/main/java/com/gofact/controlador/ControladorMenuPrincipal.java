/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador;

import com.gofact.controlador.proveedor.ControladorProveedor;
import com.gofact.controlador.usuario.ControladorEliminacionUsuarios;
import com.gofact.controlador.usuario.ControladorModificarContrasena;
import com.gofact.controlador.usuario.ControladorModificarInformacion;
import com.gofact.presentacion.DialogAbout;
import com.gofact.presentacion.FrmInicioSesion;
import com.gofact.presentacion.FrmMenuPrincipal;
import com.gofact.presentacion.factura.DialogExportarXML;
import com.gofact.presentacion.factura.DialogInsertarFactura;
import com.gofact.presentacion.proveedores.DialogProv;
import com.gofact.presentacion.reportes.DialogGenerarReporte;
import com.gofact.presentacion.usuarios.DialogEditarInformacionUsuario;
import com.gofact.presentacion.usuarios.DialogEliminarUsuario;
import com.gofact.presentacion.usuarios.DialogModificarContrasena;
import persistencia.jpacontroladores.UsuarioJpaController;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.ProveedorJpaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author camm
 */
public class ControladorMenuPrincipal implements ActionListener{

    private final FrmMenuPrincipal vista;
    private final EntityManagerFactory emf;
    private final Usuario usuarioIngresado;

    public ControladorMenuPrincipal(FrmMenuPrincipal vista
            , EntityManagerFactory emf
            , Usuario usuarioIngresado) {
        this.vista = vista;
        this.emf = emf;
        this.usuarioIngresado = usuarioIngresado;

        this.vista.getLblBienvenida().setText("Bienvenido " + this.vista.getUsuarioIngresado().getNombre());
        this.vista.getMenuItemConsultarProveedor().addActionListener(this);
        this.vista.getMenuItemConsultarFactura().addActionListener(this);
        this.vista.getMenuItemExportarXML().addActionListener(this);
        this.vista.getmenuItemModificarContrasena().addActionListener(this);
        this.vista.getmenuItemModificarInformacion().addActionListener(this);
        this.vista.getMenuItemModificarEliminar().addActionListener(this);
        this.vista.getMenuItemObtenerReporte().addActionListener(this);
        this.vista.getMenuItemAcerca().addActionListener(this);
        this.vista.getMenuItemCerrarSesion().addActionListener(this);
        this.vista.getMenuItemCerrarSistema().addActionListener(this);
    }

    private void consultaProveedor() {
        DialogProv vistaProv = new DialogProv(this.vista, true);
        ProveedorJpaController modeloProv = new ProveedorJpaController(this.emf);
        ControladorProveedor controlador = new ControladorProveedor(vistaProv, modeloProv);
        vistaProv.setVisible(true);
    }

    private void consultaFactura() {
        DialogInsertarFactura dialogInsertarFactura = new DialogInsertarFactura(this.vista, true);
        dialogInsertarFactura.setVisible(true);
    }

    private void exportarXML() {
        DialogExportarXML dexml = new DialogExportarXML(this.vista, true);
        dexml.setVisible(true);
    }

    private void modificarContrasena() {
        DialogModificarContrasena vistaMC = new DialogModificarContrasena(this.vista, true);
        UsuarioJpaController modeloMC = new UsuarioJpaController(this.emf);
        ControladorModificarContrasena controlador = new ControladorModificarContrasena(vistaMC, modeloMC, this.usuarioIngresado);
        vistaMC.setVisible(true);
    }

    private void modificarInformacion() {
        DialogEditarInformacionUsuario vistaMI = new DialogEditarInformacionUsuario(this.vista, true);
        UsuarioJpaController modeloMI = new UsuarioJpaController(this.emf);
        ControladorModificarInformacion controlador = new ControladorModificarInformacion(vistaMI, modeloMI, this.usuarioIngresado);
        vistaMI.setVisible(true);
    }

    private void eliminarUsuario() {
        DialogEliminarUsuario vistaEU = new DialogEliminarUsuario(this.vista, true);
        UsuarioJpaController modeloUs = new UsuarioJpaController(this.emf);
        ControladorEliminacionUsuarios controlador = new ControladorEliminacionUsuarios(vistaEU, modeloUs, this.usuarioIngresado);
        vistaEU.setVisible(true);
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
        FrmInicioSesion vistaIU = new FrmInicioSesion();
        UsuarioJpaController modelo = new UsuarioJpaController(this.emf);
        ControladorIngresoUsuario controlador = new ControladorIngresoUsuario(vistaIU, modelo, this.emf);
        vistaIU.setVisible(true);
    }

    private void cerrarSistema() {
        this.vista.dispose();
        System.exit(0);
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
        else if(ae.getSource() == this.vista.getmenuItemModificarInformacion()){
            modificarInformacion();
        }
        else if(ae.getSource() == this.vista.getmenuItemModificarContrasena()){
            modificarContrasena();
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
}
