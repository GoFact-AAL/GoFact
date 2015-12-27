/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import soporte.Cifrador;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camm
 */
public class CifradorTest {
    @Test
    public void testSha() {
        System.out.println("sha");
        assertEquals("Falla: SHA no es el cifrado correcto",
                Cifrador.sha("hola"),
                "99800b85d3383e3a2fb45eb7d0066a4879a9dad0");
    }
}