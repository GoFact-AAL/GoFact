/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import soporte.Totales;
import static org.junit.Assert.*;

/**
 *
 * @author camm
 */
public class TotalesTest {
    public HashMap<String, Integer>  gastos;
    public Totales totales;
    public Double epsilon = 0.000001;

    @Before
    public void setUp() {
        this.gastos = new HashMap<>();
        this.gastos.put("Alimentación", 1000);
        this.gastos.put("Vestimenta", 1000);
        this.gastos.put("Educación", 1000);
        this.gastos.put("Salud", 1000);
        this.gastos.put("Vivienda", 1000);
        this.gastos.put("Otros", 1000);

        this.totales = new Totales();
        this.totales.calculos(this.gastos);
    }

    @Test
    public void testGastosDeducibles() {
        System.out.println("Gastos deducibles");
        assertEquals("Falla: los gastos deducibles no son iguales", 50.0, this.totales.deducibles, epsilon);
    }

    @Test
    public void testGastosNoDeducibles() {
        System.out.println("Gastos no deducibles");
        assertEquals("Falla: los gastos no deducibles no son iguales", 10.0, this.totales.noDeducibles, epsilon);
    }

    @Test
    public void testTotalesSinIva() {
        System.out.println("Totales sin IVA");
        assertEquals("Falla: los totales sin IVA deducibles no son iguales", 60.0, this.totales.totalSinIva, epsilon);
    }

    @Test
    public void testTotalesConIva() {
        System.out.println("Totales con IVA");
        assertEquals("Falla: los totales con IVA no son iguales", 67.2, this.totales.total, epsilon);
    }
}
