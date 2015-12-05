/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.presentacion;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author camm
 */
public class FrmInicioSesionTest {
    
    private FrameFixture demo;

    @Before
    public void setUp() {
        FrmInicioSesion fis = new FrmInicioSesion();
        fis.setVisible(true);
        demo = new FrameFixture(fis);
    }

    @After
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    public void testMain() {
        demo.textBox("txtCI").setText("1725651630");
        demo.textBox("txtPass").setText("holaMundo1");
        demo.button("btnIngresar").click();
    }

    @Test
    public void testSalir() {
        demo.button("btnSalir").click();
    }
}
