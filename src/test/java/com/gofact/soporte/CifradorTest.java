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
public class CifradorTest {
    @Test
    public void testSha() {
        System.out.println("sha");
        assertEquals("Falla: SHA no es el cifrado correcto",
                Cifrador.sha("hola"),
                "b221d9dbb083a7f33428d7c2a3c3198ae925614d70210e28716ccaa7cd4ddb79");
    }
}