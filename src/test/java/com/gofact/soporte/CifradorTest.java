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
    public void testMd5() {
        System.out.println("md5");
        assertEquals("Falla: MD5 no es el cifrado correcto",
                Cifrador.md5("hola"),
                "4d186321c1a7f0f354b297e8914ab240");
    }
}