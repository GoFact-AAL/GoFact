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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Proveedor;
import persistencia.entidades.Usuario;
/**
 *
 * @author camm
 */
public class ControladorIngresoFactura implements ActionListener{

    private final DialogIngresoFactura vistaIngresoFactura;
    private final FacturaJpaController modeloIngresoFactura;
    private final EntityManagerFactory emf;
    private final Usuario usuario;

    public ControladorIngresoFactura(DialogIngresoFactura vistaIngresoFactura
            , FacturaJpaController modeloIngresoFactura
            , EntityManagerFactory emf
            , Usuario usuario) {
        this.vistaIngresoFactura = vistaIngresoFactura;
        this.modeloIngresoFactura = modeloIngresoFactura;
        this.emf = emf;
        this.usuario = usuario;

        mostrarCategorias();
        mostrarProveedores();
        this.vistaIngresoFactura.getBtnGuardar().addActionListener(this);
        this.vistaIngresoFactura.getBtnCancelar().addActionListener(this);
        this.vistaIngresoFactura.getBtnAnadirProv().addActionListener(this);
        this.vistaIngresoFactura.getBtnMasAlimentacion().addActionListener(this);
        this.vistaIngresoFactura.getBtnMenosAlimentacion().addActionListener(this);
    }

    private void mostrarProveedores(){
        ProveedorJpaController modeloProveedor = new ProveedorJpaController(emf);
        List<Proveedor> proveedores = modeloProveedor.findProveedorEntities();
        DefaultTableModel dataModel = obtenerModelo(proveedores);
        this.vistaIngresoFactura.getGridProveedor().setModel(dataModel);
    }

    private DefaultTableModel obtenerModelo(List<Proveedor> proveedores){
        DefaultTableModel dataModel = new DefaultTableModel();
        Object []fila = new Object[3];
        dataModel.addColumn("Nombre");
        dataModel.addColumn("RUC");
        dataModel.addColumn("Razón Social");

        for (Proveedor proveedor : proveedores) {
            fila[0] = proveedor.getNombrecomercial();
            fila[1] = proveedor.getRuc();
            fila[2] = proveedor.getRazonsocial();
            dataModel.addRow(fila);
        }
        return dataModel;
    }

    private void mostrarCategorias() {
        CategoriaJpaController modeloCategoria = new CategoriaJpaController(emf);
        List<Categoria> categorias = modeloCategoria.findCategoriaEntities();
        for (Categoria categoria : categorias) {
            this.vistaIngresoFactura.getCmbAlimentos().addItem(categoria.getNombre());
        }
    }

    private boolean seleccionarUnProveedor() {
        int fila = this.vistaIngresoFactura.getGridProveedor().getSelectedRow();
        int numFilas = this.vistaIngresoFactura.getGridProveedor().getSelectedRowCount();
        if(fila >= 0 && numFilas == 1){
            return true;
        }
        else{
            this.vistaIngresoFactura.mostrarMensaje("Debe seleccionar solo un proveedor.");
            return false;
        }
    }

    private boolean fechaSeleccionada() {
        Date fecha = this.vistaIngresoFactura.getDateFechaFactura().getDate();
        if(fecha == null){
            this.vistaIngresoFactura.mostrarMensaje("La fecha no puede estar vacía.");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean numeroLleno() {
        String factura = this.vistaIngresoFactura.getTxtNumFactura().getText().trim();
        if(factura.equals("")){
            this.vistaIngresoFactura.mostrarMensaje("El identificador de la factura no puede estar vacío.");
            return false;
        }
        else{
            return true;
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
    private boolean camposValidos() {
        return numeroLleno()
                && facturaUnica()
                && fechaSeleccionada()
                && seleccionarUnProveedor();
    }

    private Factura obtenerFactura() {
        Factura factura = new Factura();
        factura.setIdentificador(this.vistaIngresoFactura.getTxtNumFactura().getText().trim());
        factura.setFecha(this.vistaIngresoFactura.getDateFechaFactura().getDate());
        factura.setTelefono(this.vistaIngresoFactura.getTxtTelefono().getText().trim());
        factura.setDireccion(this.vistaIngresoFactura.getTxtDireccion().getText().trim());
        return factura;
    }

    private void anadirFactura() {
        Factura factura = obtenerFactura();
        if(camposValidos()){
            this.modeloIngresoFactura.create(factura);
        }
    }

    private void anadirProveedor() {
        DialogInsertarProv vistaProv = new DialogInsertarProv(null, true, false);
        ProveedorJpaController modeloProv = new ProveedorJpaController(this.emf);
        ControladorProveedorInsertar controlador = new ControladorProveedorInsertar(vistaProv, modeloProv);
        vistaProv.setVisible(true);
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
    }
}
