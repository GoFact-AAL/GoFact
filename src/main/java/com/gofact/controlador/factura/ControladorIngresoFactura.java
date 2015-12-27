/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.factura;

import com.gofact.controlador.proveedor.ControladorProveedorInsertar;
import com.gofact.presentacion.factura.DialogFacturas;
import com.gofact.presentacion.proveedores.DialogInsertarProv;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManagerFactory;
import persistencia.entidades.Factura;
import persistencia.jpacontroladores.FacturaJpaController;
import persistencia.jpacontroladores.ProveedorJpaController;

/**
 *
 * @author camm
 */
public class ControladorIngresoFactura implements ActionListener{

    private final DialogFacturas vistaIngresoFactura;
    private final FacturaJpaController modeloIngresoFactura;
    private final EntityManagerFactory emf;

    public ControladorIngresoFactura(DialogFacturas vistaIngresoFactura
            , FacturaJpaController modeloIngresoFactura
            , EntityManagerFactory emf) {
        this.vistaIngresoFactura = vistaIngresoFactura;
        this.modeloIngresoFactura = modeloIngresoFactura;
        this.emf = emf;
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
        String fecha = this.vistaIngresoFactura.getDateFechaFactura().getDateFormatString().trim();
        if(fecha.equals("")){
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
        else if(ae.getSource() == this.vistaIngresoFactura.getBtnAnadir()){
            anadirProveedor();
        }
        else if(ae.getSource() == this.vistaIngresoFactura.getBtnCancelar()){
            salir();
        }
    }
}
