/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.factura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Factura;
import persistencia.entidades.Usuario;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.jpacontroladores.FacturaJpaController;
import persistencia.jpacontroladores.UsuarioJpaController;
import presentacion.factura.DialogFacturas;
import presentacion.factura.DialogIngresoFactura;

/**
 *
 * @author camm
 */
public class ControladorFactura implements ActionListener{

    private final DialogFacturas vistaFactura;
    private final FacturaJpaController modeloFactura;
    private final UsuarioJpaController modeloUsuario;
    private final EntityManagerFactory emf;
    private Usuario usuario;

    public ControladorFactura(DialogFacturas vistaFactura
            , FacturaJpaController modeloFactura
            , EntityManagerFactory emf
            , Usuario usuario) {
        this.vistaFactura = vistaFactura;
        this.modeloFactura = modeloFactura;
        this.modeloUsuario = new UsuarioJpaController(emf);
        this.emf = emf;
        this.usuario = usuario;

        mostrarFacturas(this.usuario.getFacturaList());
        this.vistaFactura.getBtnAnadir().addActionListener(this);
        this.vistaFactura.getBtnEditar().addActionListener(this);
        this.vistaFactura.getBtnEliminar().addActionListener(this);
        this.vistaFactura.getBtnCancelar().addActionListener(this);
    }

    private void mostrarFacturas(List<Factura> facturas){
        DefaultTableModel tablaFacturas = fromListFacturaToDataModel(facturas);
        this.vistaFactura.getGridFacturas().setModel(tablaFacturas);
    }

    private DefaultTableModel fromListFacturaToDataModel(List<Factura>  facturas){
        DefaultTableModel tablaFacturas = new DefaultTableModel();

        tablaFacturas.addColumn("Número");
        tablaFacturas.addColumn("Proveedor");
        tablaFacturas.addColumn("Fecha");
        tablaFacturas.addColumn("Teléfono");
        tablaFacturas.addColumn("Total");
        Object fila[] = new Object[5];

        for (Factura factura : facturas) {
            fila[0] = factura.getIdentificador();
            fila[1] = factura.getIdproveedor().getNombrecomercial();
            fila[2] = factura.getFecha();
            fila[3] = factura.getTelefono();
            fila[4] = (double) factura.getTotaltotal()/100;
            tablaFacturas.addRow(fila);
        }
        return tablaFacturas;
    }

    private void nuevaFactura() {
        DialogIngresoFactura vistaIngresoFactura = new DialogIngresoFactura(null, true);
        ControladorIngresoFactura controladorIngresoFactura =
                new ControladorIngresoFactura(vistaIngresoFactura, this.modeloFactura, this.emf, this.usuario, null, false);
        vistaIngresoFactura.setVisible(true);
    }

    private DialogIngresoFactura setVistaEdicionFactura(Factura factura){
        DialogIngresoFactura vista = new DialogIngresoFactura(null, true);
        vista.setNumFactura(factura.getIdentificador());
        vista.setDateFechaFactura(factura.getFecha());
        vista.setTxtDireccion(factura.getDireccion());
        vista.setTxtTelefono(factura.getTelefono());
        /*vista.setGridRubros(null);
        vista.setTxtTotalDeducible(null);
        vista.setTxtTotalNoDeducible(null);*/
        return vista;
    }

    private void edicionDeFactura(Factura factura){
        DialogIngresoFactura vistaEdicion = setVistaEdicionFactura(factura);
        ControladorIngresoFactura controladorIngresoFactura =
                new ControladorIngresoFactura(vistaEdicion, this.modeloFactura, this.emf, this.usuario, factura, true);
        vistaEdicion.setVisible(true);
    }

    private void editarFactura() {
        int fila = this.vistaFactura.getGridFacturas().getSelectedRow();
        int numFilas = this.vistaFactura.getGridFacturas().getSelectedRowCount();
        if(fila >= 0 && numFilas == 1){
            Factura factura = findFacturaInUsuario((String) this.vistaFactura.getGridFacturas().getValueAt(fila, 0));
            edicionDeFactura(factura);
        }
        else{
            this.vistaFactura.mostrarMensaje("Recuerde seleccionar solo una fila");
        }
    }

    private Factura findFacturaInUsuario(String identificador){
        for (Factura fact : this.usuario.getFacturaList()) {
            if (fact.getIdentificador().equals(identificador)) {
                return fact;
            }
        }
        return null;
    }

    private void confirmarEliminacion(){
        int fila = this.vistaFactura.getGridFacturas().getSelectedRow();
        if(this.vistaFactura.confirmar("¿Está seguro que desea borrar este proveedor?")){
            try {
                Factura factura = findFacturaInUsuario((String) this.vistaFactura.getGridFacturas().getValueAt(fila, 0));
                this.modeloFactura.destroy(factura.getIdfactura());
                this.vistaFactura.mostrarMensaje("La factura ha sido eliminada");
            } catch (IllegalOrphanException ex) {
                System.err.println("No se pudo borrar la factura\nExcepcion huerfana");
            } catch (NonexistentEntityException ex) {
                System.err.println("No se pudo borrar la factura\nPorque no existe dicha entidad");
            }
        }
    }

    private void eliminarFactura() {
        int fila = this.vistaFactura.getGridFacturas().getSelectedRow();
        int numFilas = this.vistaFactura.getGridFacturas().getSelectedRowCount();
        if(fila >= 0 && numFilas == 1){
            confirmarEliminacion();
        }
        else{
            this.vistaFactura.mostrarMensaje("Recuerde seleccionar solo una fila");
        }
    }

    private void cerrar() {
        this.vistaFactura.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaFactura.getBtnAnadir()) {
            nuevaFactura();
        }
        else if (ae.getSource() == this.vistaFactura.getBtnEditar()) {
            editarFactura();
        }
        else if (ae.getSource() == this.vistaFactura.getBtnEliminar()) {
            eliminarFactura();
        }
        else if (ae.getSource() == this.vistaFactura.getBtnCancelar()) {
            cerrar();
        }
        this.usuario = this.modeloUsuario.findUserByCI(this.usuario.getCedulaidentidad());
        mostrarFacturas(this.usuario.getFacturaList());
    }
}
