/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.proveedor;

import com.gofact.modelo.Proveedor;
import com.gofact.modelo.TablaProveedor;
import com.gofact.presentacion.proveedores.DialogInsertar;
import com.gofact.presentacion.proveedores.DialogProv;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author camm
 */
public class ControladorProveedor implements ActionListener, KeyListener{
    public DialogProv vistaProv = new DialogProv(null, true);
    public TablaProveedor modeloProv = new TablaProveedor();

    public ControladorProveedor(DialogProv vistaProv, TablaProveedor modeloProv) {
        this.vistaProv = vistaProv;
        this.modeloProv = modeloProv;
        this.vistaProv.getBtnAnadir().addActionListener(this);
        this.vistaProv.getBtnEditar().addActionListener(this);
        this.vistaProv.getBtnEliminar().addActionListener(this);
        this.vistaProv.getBtnCancelar().addActionListener(this);
        this.vistaProv.getTxtFiltro().addActionListener(this);
        llenarTabla(this.vistaProv.getTableProveedores());
    }

    public void llenarTabla(JTable tablaProv){
        ArrayList<Proveedor> proveedores = this.modeloProv.listarProveedores();
        Object []fila = new Object[5];
        DefaultTableModel dtm = new DefaultTableModel();
        tablaProv.setModel(dtm);

        dtm.addColumn("Nombre");
        dtm.addColumn("RUC");
        dtm.addColumn("Razón Social");
        dtm.addColumn("Dirección");
        dtm.addColumn("Teléfono");

        for (Proveedor proveedore : proveedores) {
            fila[0] = proveedore.getNombrecomercial();
            fila[1] = proveedore.getRuc();
            fila[2] = proveedore.getRazonsocial();
            fila[3] = proveedore.getDireccion();
            fila[4] = proveedore.getTelefono();
            dtm.addRow(fila);
        }
    }

    private void anadirProveedor() {
        DialogInsertar vistaProvIns = new DialogInsertar(null, true, false);
        ControladorProveedorInsertar controlador =
                new ControladorProveedorInsertar(vistaProvIns, this.modeloProv);
        vistaProvIns.setVisible(true);
    }

    private void editarProveedor() {
        DialogInsertar vistaProvIns = new DialogInsertar(null, true, true);
        ControladorProveedorInsertar controlador =
                new ControladorProveedorInsertar(vistaProvIns, this.modeloProv);
        int fila = this.vistaProv.getTableProveedores().getSelectedRow();
        int numFilas = this.vistaProv.getTableProveedores().getSelectedRowCount();
        if(fila >= 0 && numFilas == 1){
            String RUC = (String)this.vistaProv.getTableProveedores().getValueAt(fila, 1);
            Proveedor proveedor = this.modeloProv.buscarProveedorPorRUC(RUC);
            vistaProvIns.getTxtNombreComercial().setText(proveedor.getNombrecomercial());
            vistaProvIns.getTxtRUC().setText(proveedor.getRuc());
            vistaProvIns.getTxtRazonSocial().setText(proveedor.getRazonsocial());
            vistaProvIns.getTxtDireccion().setText(proveedor.getDireccion());
            vistaProvIns.getTxtCiudad().setText(proveedor.getCiudad());
            vistaProvIns.getTxtPais().setText(proveedor.getPais());
            vistaProvIns.getTxtTelefono().setText(proveedor.getTelefono());
            vistaProvIns.setVisible(true);
        }
        else{
            this.vistaProv.mostrarMensaje("Recuerde seleccionar solo una fila");
        }
    }

    private void eliminar() {
        int fila = this.vistaProv.getTableProveedores().getSelectedRow();
        int numFilas = this.vistaProv.getTableProveedores().getSelectedRowCount();
        if(fila >= 0 && numFilas == 1){
            if(this.vistaProv.confirmar("¿Está seguro que desea borrar este proveedor?")){
                Proveedor prov = this.modeloProv
                        .buscarProveedorPorRUC((String)this.vistaProv.getTableProveedores().getValueAt(fila, 1));
                if(this.modeloProv.eliminar(prov)){
                    this.vistaProv.mostrarMensaje("El proveedor ha sido eliminado");
                }
            }
        }
        else{
            this.vistaProv.mostrarMensaje("Recuerde seleccionar solo una fila");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaProv.getBtnAnadir()) {
            anadirProveedor();
        }
        else if (ae.getSource() == this.vistaProv.getBtnEditar()) {
            editarProveedor();
        }
        else if (ae.getSource() == this.vistaProv.getBtnEliminar()) {
            eliminar();
        }
        else if (ae.getSource() == this.vistaProv.getBtnCancelar()) {
            this.vistaProv.dispose();
        }
        else if (ae.getSource() == this.vistaProv.getTxtFiltro()) {

        }
        llenarTabla(this.vistaProv.getTableProveedores());
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}

}
