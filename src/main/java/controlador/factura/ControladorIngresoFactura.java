/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.factura;

import controlador.proveedor.ControladorProveedorInsertar;
import presentacion.factura.DialogIngresoFactura;
import presentacion.proveedor.DialogInsertarProv;
import persistencia.entidades.Categoria;
import persistencia.entidades.Factura;
import persistencia.jpacontroladores.CategoriaJpaController;
import persistencia.jpacontroladores.FacturaJpaController;
import persistencia.jpacontroladores.ProveedorJpaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Gasto;
import persistencia.entidades.Proveedor;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.GastoJpaController;
import soporte.Totales;
import soporte.Transformador;
import soporte.ValidadorFormularioFactura;
/**
 *
 * @author camm
 */
public class ControladorIngresoFactura implements ActionListener{

    private final DialogIngresoFactura vistaIngresoFactura;
    private final FacturaJpaController modeloIngresoFactura;
    private final EntityManagerFactory emf;
    private final Usuario usuario;
    private final HashMap<String, Integer> gastos;

    public ControladorIngresoFactura(DialogIngresoFactura vistaIngresoFactura
            , FacturaJpaController modeloIngresoFactura
            , EntityManagerFactory emf
            , Usuario usuario) {
        this.vistaIngresoFactura = vistaIngresoFactura;
        this.modeloIngresoFactura = modeloIngresoFactura;
        this.emf = emf;
        this.usuario = usuario;
        this.gastos = new HashMap<>();

        mostrarCategorias();
        mostrarProveedores();
        mostrarGastos();
        this.vistaIngresoFactura.getBtnGuardar().addActionListener(this);
        this.vistaIngresoFactura.getBtnCancelar().addActionListener(this);
        this.vistaIngresoFactura.getBtnAnadirProv().addActionListener(this);
        this.vistaIngresoFactura.getBtnMas().addActionListener(this);
        this.vistaIngresoFactura.getBtnMenos().addActionListener(this);
    }

    private void mostrarProveedores(){
        ProveedorJpaController modeloProveedor = new ProveedorJpaController(emf);
        List<Proveedor> proveedores = modeloProveedor.findProveedorEntities();
        DefaultTableModel dataModel = Transformador.fromListProveedorToDataModel(proveedores);
        this.vistaIngresoFactura.getGridProveedor().setModel(dataModel);
    }

    private void mostrarCategorias() {
        CategoriaJpaController modeloCategoria = new CategoriaJpaController(emf);
        List<Categoria> categorias = modeloCategoria.findCategoriaEntities();
        for (Categoria categoria : categorias) {
            this.vistaIngresoFactura.getCmbAlimentos().addItem(categoria.getNombre());
        }
    }

    private boolean facturaUnica(){
        String factura = this.vistaIngresoFactura.getTxtNumFactura().getText().trim();
        if (this.modeloIngresoFactura.findFacturaByIdentificador(factura) == null) {
            return true;
        } else {
            this.vistaIngresoFactura.mostrarMensaje("El identificador de esa factura ya fue guardado.");
            return false;
        }
    }

    private boolean entradaValida(){
        ValidadorFormularioFactura validador = new ValidadorFormularioFactura(this.vistaIngresoFactura);
        return validador.entradaValida();
    }

    private boolean camposValidos() {
        return entradaValida()
                && facturaUnica();
    }

    private Proveedor getProveedor(){
        ProveedorJpaController modeloProveedor = new ProveedorJpaController(emf);
        return modeloProveedor.findProveedorByRUC(this.vistaIngresoFactura.getRuc());
    }

    private Factura obtenerFactura() {
        Factura factura = new Factura();
        factura.setIdentificador(this.vistaIngresoFactura.getTxtNumFactura().getText().trim());
        factura.setFecha(this.vistaIngresoFactura.getDateFechaFactura().getDate());
        factura.setTelefono(this.vistaIngresoFactura.getTxtTelefono().getText().trim());
        factura.setDireccion(this.vistaIngresoFactura.getTxtDireccion().getText().trim());
        factura.setIva(this.vistaIngresoFactura.getIVA());
        factura.setTotalsiniva((int)(this.vistaIngresoFactura.getTotalSinIVA()*100));
        factura.setTotaltotal((int)(this.vistaIngresoFactura.getTotal()*100));
        factura.setIdusuario(this.usuario);
        factura.setIdproveedor(getProveedor());
        return factura;
    }

    private void relacionarGastos(){
        String identificador = this.vistaIngresoFactura.getTxtNumFactura().getText().trim();
        Factura factura = this.modeloIngresoFactura.findFacturaByIdentificador(identificador);
        for (Map.Entry<String, Integer> entrySet : this.gastos.entrySet()) {
            crearGasto(entrySet.getKey(), entrySet.getValue(), factura);
        }
    }

    private void crearGasto(String nombreCat, Integer valor, Factura factura){
        CategoriaJpaController modeloCat = new CategoriaJpaController(this.emf);
        GastoJpaController modeloGasto = new GastoJpaController(this.emf);
        Categoria categoria = modeloCat.findCategoriaByName(nombreCat);

        Gasto gasto = new Gasto();
        gasto.setIdcategoria(categoria);
        gasto.setIdfactura(factura);
        gasto.setValor(valor);
        modeloGasto.create(gasto);
    }

    private void actualizarTotales(){
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

    private void anadirFactura() {
        if(camposValidos()){
            Factura factura = obtenerFactura();
            this.modeloIngresoFactura.create(factura);
            relacionarGastos();
            this.vistaIngresoFactura.mostrarMensaje("Se ha guardado la factura");
            this.vistaIngresoFactura.dispose();
        }
    }

    private void anadirProveedor() {
        DialogInsertarProv vistaProv = new DialogInsertarProv(null, true, false);
        ProveedorJpaController modeloProv = new ProveedorJpaController(this.emf);
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

    private void mostrarGastos(){
        DefaultTableModel modelo = Transformador.fromHashMapToDataModel(this.gastos);
        this.vistaIngresoFactura.getGridRubros().setModel(modelo);
    }

    private void reducirGasto() {
        String categoria = (String) this.vistaIngresoFactura.getCmbAlimentos().getSelectedItem();
        int gasto = (int)(this.vistaIngresoFactura.getValor()*100);
        if (this.gastos.containsKey(categoria)) {
            if (this.gastos.get(categoria) - gasto >= 0 ) {
                this.gastos.put(categoria, this.gastos.get(categoria) - gasto);
            }
        }
        DefaultTableModel modelo = Transformador.fromHashMapToDataModel(this.gastos);
        this.vistaIngresoFactura.getGridRubros().setModel(modelo);
        actualizarTotales();
    }

    private void salir() {
        this.vistaIngresoFactura.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.vistaIngresoFactura.getBtnGuardar()){
            anadirFactura();
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
    }
}
