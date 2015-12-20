/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.controlador.proveedor;

import persistencia.exceptions.NonexistentEntityException;
import com.gofact.presentacion.proveedores.DialogInsertar;
import com.gofact.soporte.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.jpacontroladores.ProveedorJpaController;
import persistencia.entidades.Proveedor;

/**
 *
 * @author camm
 */
public class ControladorProveedorInsertar implements ActionListener{

    private DialogInsertar vistaProv = new DialogInsertar(null, true, true);
    public ProveedorJpaController modeloProv = new ProveedorJpaController(null);

    public ControladorProveedorInsertar(DialogInsertar vistaProv
            , ProveedorJpaController modeloProv) {
        this.vistaProv = vistaProv;
        this.modeloProv = modeloProv;
        this.vistaProv.getBtnGuardar().addActionListener(this);
        this.vistaProv.getBtnCancelar().addActionListener(this);
   }

    private Proveedor obtenerProveedor(){
        Proveedor nuevoProveedor = new Proveedor();
        nuevoProveedor.setNombrecomercial(this.vistaProv.getTxtNombreComercial().getText());
        nuevoProveedor.setRuc(this.vistaProv.getTxtRUC().getText());
        nuevoProveedor.setRazonsocial(this.vistaProv.getTxtRazonSocial().getText());
        nuevoProveedor.setDireccion(this.vistaProv.getTxtDireccion().getText());
        nuevoProveedor.setCiudad(this.vistaProv.getTxtCiudad().getText());
        nuevoProveedor.setPais(this.vistaProv.getTxtPais().getText());
        nuevoProveedor.setTelefono(this.vistaProv.getTxtTelefono().getText());
        return nuevoProveedor;
    }

    private void guardarProveedor() {
        if (camposValidos()) {
            Proveedor nuevo = obtenerProveedor();
            if(this.vistaProv.editar){
                try {
                    Proveedor provEnBase = this.modeloProv.findProveedorByRUC(nuevo.getRuc());
                    nuevo.setIdproveedor(provEnBase.getIdproveedor());
                    nuevo.setFacturaCollection(provEnBase.getFacturaCollection());
                    this.modeloProv.edit(nuevo);
                    this.vistaProv.mostrarMensaje("¡Éxito!");
                    this.vistaProv.dispose();
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ControladorProveedorInsertar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorProveedorInsertar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                if (this.modeloProv.findProveedorByRUC(this.vistaProv.getTxtRUC().getText()) == null) {
                    this.modeloProv.create(nuevo);
                    this.vistaProv.mostrarMensaje("¡Éxito!");
                    this.vistaProv.dispose();
                } else {
                    this.vistaProv.mostrarMensaje("Ese usuario ya se encuentra registrado");
                }
            }
        }
    }

    private boolean camposValidos() {
        return validarRUC(this.vistaProv.getTxtRUC().getText())
                && camposLlenos();
    }

    private boolean camposLlenos() {
        return razonSociallLleno() && nombreComercialLleno();
    }

    private boolean nombreComercialLleno() {
        if (!this.vistaProv.getTxtNombreComercial().getText().equals("")) {
            return true;
        } else {
            this.vistaProv.mostrarMensaje("El nombre comercial no puede estar vacio");
            return false;
        }
    }

    private boolean razonSociallLleno() {
        if (!this.vistaProv.getTxtRazonSocial().getText().equals("")) {
            return true;
        } else {
            this.vistaProv.mostrarMensaje("La razón social no puede estar vacia.");
            return false;
        }
    }

    private boolean validarRUC(String ruc) {
        if (Validador.rucValido(ruc)) {
            return true;
        } else {
            this.vistaProv.mostrarMensaje("RUC incorrecto");
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vistaProv.getBtnGuardar()) {
            guardarProveedor();
        }
        else if (ae.getSource() == this.vistaProv.getBtnCancelar()){
            this.vistaProv.dispose();
        }
    }

}
