/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controlador.factura;

import presentacion.factura.DialogIngresoFactura;
import modelo.persistencia.entidades.Factura;

import java.awt.event.ActionListener;
import modelo.ModeloFactura;
import modelo.persistencia.entidades.Usuario;
/**
 *
 * @author camm
 */
public class ControladorIngresoFactura extends ControladorFacturas implements ActionListener{

	public ControladorIngresoFactura(DialogIngresoFactura vistaIngresoFactura
			, ModeloFactura modeloIngresoFactura
			, Usuario usuario){
		super(vistaIngresoFactura, modeloIngresoFactura, usuario);
		mostrarDatos();
	}

	@Override
	protected void actualizarFactura(Factura factura) {
		if(facturaUnica()) {
			factura = this.modeloIngresoFactura.createReturnNewFactura(factura);
			relacionarGastos(factura);
			this.vistaIngresoFactura.mostrarMensaje("Se ha guardado la factura");
			this.vistaIngresoFactura.dispose();
		}
	}
}
