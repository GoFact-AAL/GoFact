/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controlador.factura;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import modelo.ModeloCategoria;
import modelo.ModeloFactura;
import modelo.persistencia.entidades.Factura;
import modelo.persistencia.entidades.Gasto;
import modelo.persistencia.entidades.Usuario;
import presentacion.factura.DialogIngresoFactura;
import soporte.Transformador;

/**
 *
 * @author camm
 */

public class ControladorEditarFactura extends ControladorFacturas implements ActionListener{

	private final Factura factura;

	public ControladorEditarFactura(DialogIngresoFactura vistaIngresoFactura
			, ModeloFactura modeloIngresoFactura
			, Usuario usuario
			, Factura factura) {
		super(vistaIngresoFactura, modeloIngresoFactura, usuario);
		this.factura = factura;
		this.gastos = Transformador.fromListToHashMap(this.factura.getGastoList());
		actualizarTotales();
		mostrarDatos();
	}

	private void setFactura(Factura factura){
		this.factura.setIdentificador(factura.getIdentificador());
		this.factura.setFecha(factura.getFecha());
		this.factura.setTelefono(factura.getTelefono());
		this.factura.setDireccion(factura.getDireccion());
		this.factura.setIdusuario(factura.getIdusuario());
		this.factura.setIdproveedor(factura.getIdproveedor());
		this.factura.setIva(factura.getIva());
		this.factura.setTotalsiniva(factura.getTotalsiniva());
		this.factura.setTotaltotal(factura.getTotaltotal());
	}

	private ArrayList<Gasto> obtenerNuevosGastos() {
		ArrayList<Gasto> nuevosGastos = new ArrayList<>();
		ModeloCategoria modelo = new ModeloCategoria();
		for (Map.Entry<String, Integer> gasto : this.gastos.entrySet()) {
			Gasto nuevoGasto = new Gasto();
			nuevoGasto.setValor(gasto.getValue());
			nuevoGasto.setIdcategoria(modelo.findCategoriaByName(gasto.getKey()));
			nuevoGasto.setIdfactura(this.factura);
			nuevosGastos.add(nuevoGasto);
		}
		return nuevosGastos;
	}

	@Override
	protected void actualizarFactura(Factura factura) {
		setFactura(factura);
		this.modeloIngresoFactura.edit(this.factura, obtenerNuevosGastos());
		this.vistaIngresoFactura.mostrarMensaje("Factura actualizada.");
		this.vistaIngresoFactura.dispose();
	}
}
