/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import modelo.persistencia.jpacontroladores.FacturaJpaController;
import modelo.persistencia.jpacontroladores.GastoJpaController;
import modelo.persistencia.jpacontroladores.ProveedorJpaController;
import modelo.persistencia.jpacontroladores.UsuarioJpaController;

/**
 *
 * @author camm
 */
public class TestDB {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_GoFact_jar_1.0PU");
    EntityManager em = this.emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Before
    public void cleanDatabase(){
        String consultas[] =
        {
          "DELETE FROM GASTO"
        , "DELETE FROM FACTURA"
        , "DELETE FROM PROVEEDOR"
        , "DELETE FROM USUARIO"
        };


        this.tx.begin();
        for (String consulta : consultas) {
            Query q = this.em.createNativeQuery(consulta);
            q.executeUpdate();
        }
        this.tx.commit();
    }

    @Test
    public void testSinProveedores(){
        ProveedorJpaController modelo = new ProveedorJpaController(emf);
        Assert.assertEquals(0, modelo.getProveedorCount());
    }

    @Test
    public void testSinGastos(){
        GastoJpaController modelo = new GastoJpaController(emf);
        Assert.assertEquals(0, modelo.getGastoCount());
    }

    @Test
    public void testSinFacturas(){
        FacturaJpaController modelo = new FacturaJpaController(emf);
        Assert.assertEquals(0, modelo.getFacturaCount());
    }

    @Test
    public void testSinUsuarios(){
        UsuarioJpaController modelo = new UsuarioJpaController(emf);
        Assert.assertEquals(0, modelo.getUsuarioCount());
    }
}