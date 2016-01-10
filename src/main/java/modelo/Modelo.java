/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author camm
 */
public abstract class Modelo {
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("com_GoFact_jar_1.0PU");

    public void create(Object object){};
    public void edit(Object object){};
    public void destroy(Object object){};
    public void find(Object object){};
}
