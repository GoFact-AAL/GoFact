/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controlador;

import controlador.factura.ControladorExportarXML;
import controlador.factura.ControladorFactura;
import controlador.proveedor.ControladorProveedor;
import controlador.reporte.ControladorGenerarReporte;
import controlador.usuario.ControladorEliminacionUsuarios;
import controlador.usuario.ControladorModificarContrasena;
import controlador.usuario.ControladorModificarInformacion;
import presentacion.DialogAbout;
import presentacion.FrmInicioSesion;
import presentacion.FrmMenuPrincipal;
import presentacion.factura.DialogExportarXML;
import presentacion.factura.DialogFacturas;
import presentacion.proveedor.DialogProv;
import presentacion.usuario.DialogEditarInformacionUsuario;
import presentacion.usuario.DialogEliminarUsuario;
import presentacion.usuario.DialogModificarContrasena;
import modelo.persistencia.entidades.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloFactura;
import modelo.ModeloProveedor;
import modelo.ModeloUsuario;
import presentacion.reportes.DialogReporte;

/**
 *
 * @author camm
 */
public class ControladorMenuPrincipal implements ActionListener{

	private final FrmMenuPrincipal vista;
	private final ModeloUsuario modelo;
	private Usuario usuarioIngresado;
	//private EntityManagerFactory emf;

	public ControladorMenuPrincipal(FrmMenuPrincipal vista
			, Usuario usuarioIngresado) {
		this.vista = vista;
		this.usuarioIngresado = usuarioIngresado;
		this.modelo = new ModeloUsuario();

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

	private void actualizarDatos(){
		this.usuarioIngresado = this.modelo.findUserByCI(this.usuarioIngresado.getCedulaidentidad());
		this.vista.getLblBienvenida().setText("Bienvenido " + this.usuarioIngresado.getNombre());
	}

	private void consultaProveedor() {
		DialogProv vistaProv = new DialogProv(this.vista, true);
		ModeloProveedor modeloProv = new ModeloProveedor();
		ControladorProveedor controlador = new ControladorProveedor(vistaProv, modeloProv);
		vistaProv.setVisible(true);
	}

	private void consultaFactura() {
		DialogFacturas vistaFactura = new DialogFacturas(this.vista, true);
		ModeloFactura modeloFactura = new ModeloFactura();
		ControladorFactura controladorFactura = new ControladorFactura(vistaFactura, modeloFactura, this.usuarioIngresado);
		vistaFactura.setVisible(true);
	}

	private void exportarXML() {
		DialogExportarXML vistaXML = new DialogExportarXML(this.vista, true);
		ControladorExportarXML controlador = new ControladorExportarXML(vistaXML, this.usuarioIngresado.getFacturaList());
		vistaXML.setVisible(true);
	}

	private void modificarContrasena() {
		DialogModificarContrasena vistaMC = new DialogModificarContrasena(this.vista, true);
		ControladorModificarContrasena controlador = new ControladorModificarContrasena(vistaMC, this.modelo, this.usuarioIngresado);
		vistaMC.setVisible(true);
	}

	private void modificarInformacion() {
		DialogEditarInformacionUsuario vistaMI = new DialogEditarInformacionUsuario(this.vista, true);
		ControladorModificarInformacion controlador = new ControladorModificarInformacion(vistaMI, this.modelo, this.usuarioIngresado);
		vistaMI.setVisible(true);
	}

	private void eliminarUsuario() {
		DialogEliminarUsuario vistaEU = new DialogEliminarUsuario(this.vista, true);
		ControladorEliminacionUsuarios controlador = new ControladorEliminacionUsuarios(vistaEU, this.modelo, this.usuarioIngresado);
		vistaEU.setVisible(true);
	}

	private void obtenerReporte() {
		DialogReporte vistaReporte = new DialogReporte(this.vista, true);
		ModeloFactura modeloReporte = new ModeloFactura();
		ControladorGenerarReporte controladorReporte =
				new ControladorGenerarReporte(vistaReporte, modeloReporte, this.usuarioIngresado);
		vistaReporte.setVisible(true);
	}

	private void acerca() {
		DialogAbout about = new DialogAbout(this.vista, true);
		about.setVisible(true);
	}

	private void cerrarSesion() {
		this.vista.dispose();
		FrmInicioSesion vistaIU = new FrmInicioSesion();
		ControladorIngresoUsuario controlador = new ControladorIngresoUsuario(vistaIU, this.modelo);
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
		actualizarDatos();

		if(ae.getSource() == this.vista.getMenuItemCerrarSistema()){
			cerrarSistema();
		}
	}
}
