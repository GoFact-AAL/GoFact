/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;
import persistencia.jpacontroladores.ProveedorJpaController;

/**
 *
 * @author camm
 */
public class TestDB {
    @Test
    public void testConnection(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_GoFact_jar_1.0PU");
        ProveedorJpaController modelo = new ProveedorJpaController(emf);
        Assert.assertEquals(0, modelo.getProveedorCount());
    }
}
