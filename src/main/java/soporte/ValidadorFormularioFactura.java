/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import java.util.Date;
import presentacion.factura.DialogIngresoFactura;

/**
 *
 * @author camm
 */
public class ValidadorFormularioFactura {

    DialogIngresoFactura vista;

    public ValidadorFormularioFactura(DialogIngresoFactura vista) {
        this.vista = vista;
    }

    public boolean entradaValida(){
        return numeroLleno()
                && fechaSeleccionada()
                && seleccionarUnProveedor();
    }

    private boolean seleccionarUnProveedor() {
        int fila = this.vista.getGridProveedor().getSelectedRow();
        int numFilas = this.vista.getGridProveedor().getSelectedRowCount();
        if(fila >= 0 && numFilas == 1){
            return true;
        }
        else{
            this.vista.mostrarMensaje("Debe seleccionar solo un proveedor.");
            return false;
        }
    }

    private boolean fechaSeleccionada() {
        Date fecha = this.vista.getDateFechaFactura().getDate();
        if(fecha == null){
            this.vista.mostrarMensaje("La fecha no puede estar vacía.");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean numeroLleno() {
        String factura = this.vista.getTxtNumFactura().getText().trim();
        if(factura.equals("")){
            this.vista.mostrarMensaje("El identificador de la factura no puede estar vacío.");
            return false;
        }
        else{
            return true;
        }
    }
}
