/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controlador.factura;

import controlador.proveedor.ControladorProveedorInsertar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloCategoria;
import modelo.ModeloFactura;
import modelo.ModeloGasto;
import modelo.ModeloProveedor;
import modelo.ModeloUsuario;
import modelo.persistencia.entidades.Categoria;
import modelo.persistencia.entidades.Factura;
import modelo.persistencia.entidades.Gasto;
import modelo.persistencia.entidades.Proveedor;
import modelo.persistencia.entidades.Usuario;
import presentacion.factura.DialogIngresoFactura;
import presentacion.proveedor.DialogInsertarProv;
import soporte.Totales;
import soporte.Transformador;
import soporte.ValidadorFormularioFactura;

/**
 *
 * @author camm
 */

public abstract class ControladorFacturas implements ActionListener{
	protected final DialogIngresoFactura vistaIngresoFactura;
	protected final ModeloFactura modeloIngresoFactura;
	protected Usuario usuario;
	protected HashMap<String, Integer> gastos;

	public ControladorFacturas(DialogIngresoFactura vistaIngresoFactura
			, ModeloFactura modeloIngresoFactura
			, Usuario usuario){
		this.vistaIngresoFactura = vistaIngresoFactura;
		this.modeloIngresoFactura = modeloIngresoFactura;
		this.usuario = usuario;
		this.gastos = new HashMap<>();

		mostrarFechas();
		this.vistaIngresoFactura.getBtnGuardar().addActionListener(this);
		this.vistaIngresoFactura.getBtnCancelar().addActionListener(this);
		this.vistaIngresoFactura.getBtnAnadirProv().addActionListener(this);
		this.vistaIngresoFactura.getBtnMas().addActionListener(this);
		this.vistaIngresoFactura.getBtnMenos().addActionListener(this);
	}

	protected abstract void actualizarFactura(Factura factura);

	protected void anadirProveedor() {
		DialogInsertarProv vistaProv = new DialogInsertarProv(null, true, false);
		ModeloProveedor modeloProv = new ModeloProveedor();
		ControladorProveedorInsertar controlador = new ControladorProveedorInsertar(vistaProv, modeloProv);
		vistaProv.setVisible(true);
	}

	private void agregarGasto() {
		String categoria = (String) this.vistaIngresoFactura.getCmbAlimentos().getSelectedItem();
		int gasto = (int) (this.vistaIngresoFactura.getValor()*100);
		if (this.gastos.containsKey(categoria)) {
			this.gastos.put(categoria, this.gastos.get(categoria) + gasto);
		}
		else{
			this.gastos.put(categoria, gasto);
		}
		mostrarGastos();
		actualizarTotales();
	}

	private void reducirGasto() {
		String categoria = (String) this.vistaIngresoFactura.getCmbAlimentos().getSelectedItem();
		int gasto = (int)(this.vistaIngresoFactura.getValor()*100);
		if (this.gastos.containsKey(categoria) && this.gastos.get(categoria) - gasto >= 0 ) {
			this.gastos.put(categoria, this.gastos.get(categoria) - gasto);
		}
		DefaultTableModel modelo = Transformador.fromHashMapToDataModel(this.gastos);
		this.vistaIngresoFactura.getGridRubros().setModel(modelo);
		actualizarTotales();
	}

	private void salir() {
		this.vistaIngresoFactura.dispose();
	}

	protected void actualizarTotales(){
		Totales totales = new Totales();
		totales.calculos(this.gastos);
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
		df.setRoundingMode(RoundingMode.CEILING);

		this.vistaIngresoFactura.setTxtTotalDeducible(df.format(totales.deducibles));
		this.vistaIngresoFactura.setTxtTotalNoDeducible(df.format(totales.noDeducibles));
		this.vistaIngresoFactura.setTxtTotalSinIVA(df.format(totales.totalSinIva));
		this.vistaIngresoFactura.setTxtTotal(df.format(totales.total));
	}

	private void establecerLimites(Date inicio, Date fin){
		this.vistaIngresoFactura.getDateFechaFactura().setMinSelectableDate(inicio);
		this.vistaIngresoFactura.getDateFechaFactura().setMaxSelectableDate(fin);
	}

	protected void mostrarDatos(){
		mostrarCategorias();
		mostrarProveedores();
		mostrarGastos();
	}

	private void mostrarProveedores(){
		ModeloProveedor modeloProveedor = new ModeloProveedor();
		List<Proveedor> proveedores = modeloProveedor.findProveedorEntities();
		DefaultTableModel dataModel = Transformador.fromListProveedorToDataModel(proveedores);
		this.vistaIngresoFactura.getGridProveedor().setModel(dataModel);
	}

	private void mostrarCategorias() {
		ModeloCategoria modeloCategoria = new ModeloCategoria();
		List<Categoria> categorias = modeloCategoria.findCategoriaEntities();
		for (Categoria categoria : categorias) {
			this.vistaIngresoFactura.getCmbAlimentos().addItem(categoria.getNombre());
		}
	}

	private void mostrarGastos(){
		DefaultTableModel modelo = Transformador.fromHashMapToDataModel(this.gastos);
		this.vistaIngresoFactura.getGridRubros().setModel(modelo);
	}

	private void mostrarFechas(){
		Calendar inicio = Calendar.getInstance();
		inicio.set(inicio.get(Calendar.YEAR), 0, 1);
		Calendar fin = Calendar.getInstance();
		establecerLimites(inicio.getTime(), fin.getTime());
	}

	private boolean entradaValida(){
		ValidadorFormularioFactura validador = new ValidadorFormularioFactura(this.vistaIngresoFactura);
		return validador.entradaValida();
	}

	protected boolean camposValidos() {
		return entradaValida();
	}

	public Factura obtenerFactura() {
		Factura factura = new Factura();
		factura.setIdentificador(this.vistaIngresoFactura.getTxtNumFactura().trim());
		factura.setFecha(this.vistaIngresoFactura.getDateFechaFactura().getDate());
		factura.setTelefono(this.vistaIngresoFactura.getTxtTelefono().trim());
		factura.setDireccion(this.vistaIngresoFactura.getTxtDireccion().trim());
		factura.setIva(this.vistaIngresoFactura.getIVA());
		factura.setTotalsiniva((int)(this.vistaIngresoFactura.getTotalSinIVA()*100));
		factura.setTotaltotal((int)(this.vistaIngresoFactura.getTotal()*100));
		factura.setIdusuario(this.usuario);
		factura.setIdproveedor(getProveedor());
		return factura;
	}

	protected boolean facturaUnica(){
		if (facturaInUsuariosFacturas()) {
			this.vistaIngresoFactura.mostrarMensaje("El identificador de esa factura ya fue guardado.");
			return false;
		} else {
			return true;
		}
	}

	private boolean facturaInUsuariosFacturas(){
		String idFactura = this.vistaIngresoFactura.getTxtNumFactura().trim();
		for (Factura factura : this.usuario.getFacturaList()) {
			if (factura.getIdentificador().equals(idFactura)) {
				return true;
			}
		}
		return false;
	}

	private Proveedor getProveedor(){
		ModeloProveedor modeloProveedor = new ModeloProveedor();
		return modeloProveedor.findProveedorByRUC(this.vistaIngresoFactura.getRuc());
	}

	protected void relacionarGastos(Factura factura){
		ModeloGasto modeloGasto = new ModeloGasto();
		ModeloCategoria modeloCat = new ModeloCategoria();
		for (Map.Entry<String, Integer> hashGasto : this.gastos.entrySet()) {
			Gasto gasto = new Gasto();
			gasto.setIdcategoria(modeloCat.findCategoriaByName(hashGasto.getKey()));
			gasto.setValor(hashGasto.getValue());
			gasto.setIdfactura(factura);
			modeloGasto.create(gasto);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.vistaIngresoFactura.getBtnGuardar()){
			if (camposValidos()) {
				Factura factura = obtenerFactura();
				actualizarFactura(factura);
			}
		}
		else if(ae.getSource() == this.vistaIngresoFactura.getBtnAnadirProv()){
			anadirProveedor();
			mostrarProveedores();
		}
		else if(ae.getSource() == this.vistaIngresoFactura.getBtnCancelar()){
			salir();
		}
		else if(ae.getSource() == this.vistaIngresoFactura.getBtnMas()){
			agregarGasto();
		}
		else if(ae.getSource() == this.vistaIngresoFactura.getBtnMenos()){
			reducirGasto();
		}
		actulizarFacturas();
	}

	private void actulizarFacturas() {
		ModeloUsuario modelo = new ModeloUsuario();
		this.usuario = modelo.findUser(this.usuario.getIdusuario());
	}
}
