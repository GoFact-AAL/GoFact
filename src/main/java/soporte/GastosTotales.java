/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import modelo.persistencia.entidades.Factura;
import modelo.persistencia.entidades.Gasto;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author camm
 */
public class GastosTotales {
    private HashMap<String, Integer> gastosTotales;

    public GastosTotales() {
        this.gastosTotales = new HashMap<>();
    }
    
    public void sumarRubrosFacturas(List<Factura> facturas){
        for (Factura factura : facturas) {
            sumarRubrosFactura(factura.getGastoList());
        }
    }
    
    public void sumarRubrosFactura(List<Gasto> gastos){
        String nombre;
        Integer valor;

        for (Gasto gasto : gastos) {
            nombre = gasto.getIdcategoria().getNombre();
            valor = gasto.getValor();
            if (this.gastosTotales.containsKey(nombre)) {
                this.gastosTotales.put(nombre, this.gastosTotales.get(nombre) + valor);
            }
            else{
                this.gastosTotales.put(nombre, valor);
            }
        }
    }

    public HashMap<String, Integer> getGastosTotales() {
        return gastosTotales;
    }
}
