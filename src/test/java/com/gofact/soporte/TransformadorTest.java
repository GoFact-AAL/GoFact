/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import modelo.persistencia.entidades.Proveedor;
import soporte.Transformador;

/**
 *
 * @author camm
 */
public class TransformadorTest {
    public List<Proveedor> proveedores;
    public HashMap<String, Integer> gastos;

    @Before
    public void setUpProveedores() {
        this.proveedores = new ArrayList<>();
        this.proveedores.add(new Proveedor(1, "0999999999001", "Razon Social 1", "Nombre Comercial 1"));
        this.proveedores.add(new Proveedor(2, "0999999998001", "Razon Social 2", "Nombre Comercial 2"));
    }

    @Before
    public void setUpGastos() {
        this.gastos = new HashMap<>();
        this.gastos.put("Alimentación", 1000);
        this.gastos.put("Vestimenta", 1000);
        this.gastos.put("Educación", 1000);
        this.gastos.put("Salud", 1000);
        this.gastos.put("Vivienda", 1000);
        this.gastos.put("Otros", 1000);
    }

    @Test
    public void testFromListProveedorToDataModel() {
        System.out.println("From List Proveedor To Default Data Model");
        DefaultTableModel expResult = new DefaultTableModel();
        expResult.addColumn("Nombre");
        expResult.addColumn("RUC");
        expResult.addColumn("Razón Social");
        Object rowProv1[] = {"Nombre Comercial 1", "0999999999001", "Razon Social 1"};
        Object rowProv2[] = {"Nombre Comercial 2", "0999999998001", "Razon Social 2"};
        expResult.addRow(rowProv1);
        expResult.addRow(rowProv2);

        DefaultTableModel result = Transformador.fromListProveedorToDataModel(proveedores);
        assertEquals(expResult.getValueAt(0, 0), result.getValueAt(0, 0));
        assertEquals(expResult.getValueAt(0, 1), result.getValueAt(0, 1));
        assertEquals(expResult.getValueAt(1, 0), result.getValueAt(1, 0));
        assertEquals(expResult.getValueAt(1, 1), result.getValueAt(1, 1));
    }

    @Test
    public void testFromHashMapToDataModel() {
        System.out.println("From HashMap To DefaultDataModel");

        DefaultTableModel expResult = new DefaultTableModel();
        expResult.addColumn("Categoría");
        expResult.addColumn("Valor");
        Object rowGasto1[] = {"Alimentación", 10};
        Object rowGasto2[] = {"Vestimenta", 10};
        Object rowGasto3[] = {"Educación", 10};
        Object rowGasto4[] = {"Salud", 10};
        Object rowGasto5[] = {"Vivienda", 10};
        Object rowGasto6[] = {"Otros", 10};
        expResult.addRow(rowGasto1);
        expResult.addRow(rowGasto2);
        expResult.addRow(rowGasto3);
        expResult.addRow(rowGasto4);
        expResult.addRow(rowGasto5);
        expResult.addRow(rowGasto6);

        DefaultTableModel result = Transformador.fromHashMapToDataModel(this.gastos);
        assertEquals(expResult.getRowCount(), result.getRowCount());
    }
}
