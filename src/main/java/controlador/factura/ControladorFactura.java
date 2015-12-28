/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.factura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManagerFactory;
import persistencia.entidades.Usuario;
import persistencia.jpacontroladores.FacturaJpaController;
import presentacion.factura.DialogFacturas;
import presentacion.factura.DialogIngresoFactura;

/**
 *
 * @author camm
 */
public class ControladorFactura implements ActionListener{

    private final DialogFacturas vistaFactura;
    private final FacturaJpaController modeloFactura;
    private final EntityManagerFactory emf;
    private Usuario usuario;

    public ControladorFactura(DialogFacturas vistaFactura
            , FacturaJpaController modeloFactura
            , EntityManagerFactory emf
            , Usuario usuario) {
        this.vistaFactura = vistaFactura;
        this.modeloFactura = modeloFactura;
        this.emf = emf;
        this.usuario = usuario;

        this.vistaFactura.getBtnAnadir().addActionListener(this);
        this.vistaFactura.getBtnEditar().addActionListener(this);
        this.vistaFactura.getBtnEliminar().addActionListener(this);
        this.vistaFactura.getBtnCancelar().addActionListener(this);
    }

    private void nuevaFactura() {
        DialogIngresoFactura vistaIngresoFactura = new DialogIngresoFactura(null, true, false);
        ControladorIngresoFactura controladorIngresoFactura =
                new ControladorIngresoFactura(vistaIngresoFactura, this.modeloFactura, this.emf, this.usuario);
        vistaIngresoFactura.setVisible(true);
    }

    private void editarFactura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarFactura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }
}
