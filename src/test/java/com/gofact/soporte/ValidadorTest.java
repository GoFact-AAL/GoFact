/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camm
 */
public class ValidadorTest {
    @Test
    public void testRucValido() {
        System.out.println("Un RUC Correcto");
        String ruc = "0702071119001";
        assertTrue("Falla: no se pudo validar el RUC", Validador.rucValido(ruc));
    }
    @Test
    public void testCedulaValida() {
        System.out.println("Una Cédula Correcta");
        String ci = "0702071119";
        assertTrue("Falla: no se pudo validar la cedula", Validador.cedulaValida(ci));
    }
}
