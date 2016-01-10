/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia.jpacontroladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.persistencia.entidades.Categoria;
import modelo.persistencia.entidades.Limitesanuales;
import modelo.persistencia.jpacontroladores.exceptions.NonexistentEntityException;

/**
 *
 * @author camm
 */
public class LimitesanualesJpaController implements Serializable {

    public LimitesanualesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Limitesanuales limitesanuales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria = limitesanuales.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdcategoria());
                limitesanuales.setCategoria(categoria);
            }
            em.persist(limitesanuales);
            if (categoria != null) {
                categoria.getLimitesanualesList().add(limitesanuales);
                categoria = em.merge(categoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Limitesanuales limitesanuales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Limitesanuales persistentLimitesanuales = em.find(Limitesanuales.class, limitesanuales.getIdlimite());
            Categoria categoriaOld = persistentLimitesanuales.getCategoria();
            Categoria categoriaNew = limitesanuales.getCategoria();
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdcategoria());
                limitesanuales.setCategoria(categoriaNew);
            }
            limitesanuales = em.merge(limitesanuales);
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getLimitesanualesList().remove(limitesanuales);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getLimitesanualesList().add(limitesanuales);
                categoriaNew = em.merge(categoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = limitesanuales.getIdlimite();
                if (findLimitesanuales(id) == null) {
                    throw new NonexistentEntityException("The limitesanuales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Limitesanuales limitesanuales;
            try {
                limitesanuales = em.getReference(Limitesanuales.class, id);
                limitesanuales.getIdlimite();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The limitesanuales with id " + id + " no longer exists.", enfe);
            }
            Categoria categoria = limitesanuales.getCategoria();
            if (categoria != null) {
                categoria.getLimitesanualesList().remove(limitesanuales);
                categoria = em.merge(categoria);
            }
            em.remove(limitesanuales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Limitesanuales> findLimitesanualesEntities() {
        return findLimitesanualesEntities(true, -1, -1);
    }

    public List<Limitesanuales> findLimitesanualesEntities(int maxResults, int firstResult) {
        return findLimitesanualesEntities(false, maxResults, firstResult);
    }

    private List<Limitesanuales> findLimitesanualesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Limitesanuales.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Limitesanuales findLimitesanuales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Limitesanuales.class, id);
        } finally {
            em.close();
        }
    }

    public int getLimitesanualesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Limitesanuales> rt = cq.from(Limitesanuales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
