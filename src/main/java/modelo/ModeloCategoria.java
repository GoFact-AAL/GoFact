/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.persistencia.entidades.Categoria;
import modelo.persistencia.jpacontroladores.CategoriaJpaController;

/**
 *
 * @author camm
 */
public class ModeloCategoria extends Modelo{
    private final CategoriaJpaController categoriaControl;

    public ModeloCategoria() {
        this.categoriaControl = new CategoriaJpaController(emf);
    }

    public Categoria findCategoriaByName(String nombreCat) {
        EntityManager em = this.categoriaControl.getEntityManager();
        List<Categoria> categoria =  em.createNamedQuery("Categoria.findByNombre", Categoria.class)
                .setParameter("nombre", nombreCat)
                .getResultList();
        return (categoria.isEmpty())? null : categoria.get(0);
    }

    public List<Categoria> findCategoriaEntities() {
        return this.categoriaControl.findCategoriaEntities();
    }
}
