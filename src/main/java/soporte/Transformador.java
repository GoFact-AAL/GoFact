/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import modelo.persistencia.entidades.Factura;
import modelo.persistencia.entidades.Gasto;
import modelo.persistencia.entidades.Proveedor;

/**
 *
 * @author camm
 */
public class Transformador {
    public static DefaultTableModel fromListProveedorToDataModel(List<Proveedor> proveedores){
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


    public static DefaultTableModel fromHashMapToDataModel(HashMap<String, Integer>  gastos){
        DefaultTableModel modelo = new DefaultTableModel();
        Object []fila = new Object[2];
        modelo.addColumn("Categoría");
        modelo.addColumn("Valor");
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        for (Map.Entry<String, Integer> entrySet : gastos.entrySet()) {
            fila[0] = entrySet.getKey();
            fila[1] = df.format((double) entrySet.getValue()/100.0);
            modelo.addRow(fila);
        }
        return modelo;
    }

    public static DefaultTableModel fromListGastoToDataModel(List<Gasto> gastos) {
        DefaultTableModel modelo = new DefaultTableModel();

        Object []fila = new Object[2];
        modelo.addColumn("Categoría");
        modelo.addColumn("Valor");

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        for (Gasto gasto : gastos) {
            fila[0] = gasto.getIdcategoria().getNombre();
            fila[1] = df.format((double) gasto.getValor()/100.0);
            modelo.addRow(fila);
        }
        return modelo;
    }

    public static HashMap<String, Integer> fromListToHashMap(List<Gasto> gastos) {
        HashMap<String, Integer> hashGastos = new HashMap<>();
        for (Gasto gasto : gastos) {
            hashGastos.put(gasto.getIdcategoria().getNombre(), gasto.getValor());
        }
        return hashGastos;
    }

	public static DefaultTableModel fromListFacturaToDataModel(List<Factura> facturas) {
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
}
