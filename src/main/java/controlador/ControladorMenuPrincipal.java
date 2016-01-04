/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.factura.ControladorFactura;
import controlador.proveedor.ControladorProveedor;
import controlador.usuario.ControladorEliminacionUsuarios;
import controlador.usuario.ControladorModificarContrasena;
import controlador.usuario.ControladorModificarInformacion;
import presentacion.DialogAbout;
import presentacion.FrmInicioSesion;
import presentacion.FrmMenuPrincipal;
import presentacion.factura.DialogExportarXML;
import presentacion.factura.DialogFacturas;
import presentacion.proveedor.DialogProv;
import presentacion.reportes.DialogGenerarReporte;
import presentacion.usuario.DialogEditarInformacionUsuario;
import presentacion.usuario.DialogEliminarUsuario;
import presentacion.usuario.DialogModificarContrasena;
import persistencia.jpacontroladores.UsuarioJpaController;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.ProveedorJpaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManagerFactory;
import persistencia.jpacontroladores.FacturaJpaController;

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

        this.vista.getLblBienvenida().setText("Bienvenido " + this.usuarioIngresado.getNombre());
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
        DialogFacturas vistaFactura = new DialogFacturas(this.vista, true);
        FacturaJpaController modeloFactura = new FacturaJpaController(this.emf);
        ControladorFactura controladorFactura = new ControladorFactura(vistaFactura, modeloFactura, this.emf, this.usuarioIngresado);
        vistaFactura.setVisible(true);
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
        ActualizarNombreUsuario();
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

    private void ActualizarNombreUsuario(){
        this.vista.getLblBienvenida().setText("Bienvenido " + this.usuarioIngresado.getNombre());
        
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
