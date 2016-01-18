/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.proveedor;

import presentacion.proveedor.DialogInsertarProv;
import soporte.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.ModeloProveedor;
import modelo.persistencia.entidades.Proveedor;

/**
 *
 * @author camm
 */
public class ControladorProveedorInsertar implements ActionListener, KeyListener{

    private DialogInsertarProv vistaProv;
    public ModeloProveedor modeloProv;

    public ControladorProveedorInsertar(DialogInsertarProv vistaProv
            , ModeloProveedor modeloProv) {
        this.vistaProv = vistaProv;
        this.modeloProv = modeloProv;
        this.vistaProv.getBtnGuardar().addActionListener(this);
        this.vistaProv.getBtnCancelar().addActionListener(this);
        this.vistaProv.getTxtRUC().addKeyListener(this);
        this.vistaProv.getTxtRazonSocial().addKeyListener(this);
        this.vistaProv.getTxtNombreComercial().addKeyListener(this);
        this.vistaProv.getTxtTelefono().addKeyListener(this);
        this.vistaProv.getTxtDireccion().addKeyListener(this);
        this.vistaProv.getTxtCiudad().addKeyListener(this);
        this.vistaProv.getTxtPais().addKeyListener(this);
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
                Proveedor provEnBase = this.modeloProv.findProveedorByRUC(nuevo.getRuc());
                nuevo.setIdproveedor(provEnBase.getIdproveedor());
                nuevo.setFacturaList(provEnBase.getFacturaList());
                this.modeloProv.edit(nuevo);
                this.vistaProv.mostrarMensaje("¡Éxito!");
                this.vistaProv.dispose();
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

    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getSource() == this.vistaProv.getTxtRUC() || ke.getSource() == this.vistaProv.getTxtTelefono()) {
            char c = ke.getKeyChar();
            if (c < '0' || c > '9') ke.consume();
	}
        else if(ke.getSource() == this.vistaProv.getTxtRazonSocial() 
            || ke.getSource() == this.vistaProv.getTxtNombreComercial() 
            || ke.getSource() == this.vistaProv.getTxtCiudad() 
            || ke.getSource() == this.vistaProv.getTxtPais()){
            char c = ke.getKeyChar();
            if (!(c < '0' || c > '9')) ke.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }
}
