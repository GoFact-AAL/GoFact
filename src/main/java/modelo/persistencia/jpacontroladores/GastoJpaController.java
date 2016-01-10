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
import modelo.persistencia.entidades.Factura;
import modelo.persistencia.entidades.Gasto;
import modelo.persistencia.jpacontroladores.exceptions.NonexistentEntityException;

/**
 *
 * @author camm
 */
public class GastoJpaController implements Serializable {

    public GastoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gasto gasto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria idcategoria = gasto.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                gasto.setIdcategoria(idcategoria);
            }
            Factura idfactura = gasto.getIdfactura();
            if (idfactura != null) {
                idfactura = em.getReference(idfactura.getClass(), idfactura.getIdfactura());
                gasto.setIdfactura(idfactura);
            }
            em.persist(gasto);
            if (idcategoria != null) {
                idcategoria.getGastoList().add(gasto);
                idcategoria = em.merge(idcategoria);
            }
            if (idfactura != null) {
                idfactura.getGastoList().add(gasto);
                idfactura = em.merge(idfactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gasto gasto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gasto persistentGasto = em.find(Gasto.class, gasto.getIdgasto());
            Categoria idcategoriaOld = persistentGasto.getIdcategoria();
            Categoria idcategoriaNew = gasto.getIdcategoria();
            Factura idfacturaOld = persistentGasto.getIdfactura();
            Factura idfacturaNew = gasto.getIdfactura();
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                gasto.setIdcategoria(idcategoriaNew);
            }
            if (idfacturaNew != null) {
                idfacturaNew = em.getReference(idfacturaNew.getClass(), idfacturaNew.getIdfactura());
                gasto.setIdfactura(idfacturaNew);
            }
            gasto = em.merge(gasto);
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getGastoList().remove(gasto);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getGastoList().add(gasto);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            if (idfacturaOld != null && !idfacturaOld.equals(idfacturaNew)) {
                idfacturaOld.getGastoList().remove(gasto);
                idfacturaOld = em.merge(idfacturaOld);
            }
            if (idfacturaNew != null && !idfacturaNew.equals(idfacturaOld)) {
                idfacturaNew.getGastoList().add(gasto);
                idfacturaNew = em.merge(idfacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gasto.getIdgasto();
                if (findGasto(id) == null) {
                    throw new NonexistentEntityException("The gasto with id " + id + " no longer exists.");
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
            Gasto gasto;
            try {
                gasto = em.getReference(Gasto.class, id);
                gasto.getIdgasto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gasto with id " + id + " no longer exists.", enfe);
            }
            Categoria idcategoria = gasto.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getGastoList().remove(gasto);
                idcategoria = em.merge(idcategoria);
            }
            Factura idfactura = gasto.getIdfactura();
            if (idfactura != null) {
                idfactura.getGastoList().remove(gasto);
                idfactura = em.merge(idfactura);
            }
            em.remove(gasto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gasto> findGastoEntities() {
        return findGastoEntities(true, -1, -1);
    }

    public List<Gasto> findGastoEntities(int maxResults, int firstResult) {
        return findGastoEntities(false, maxResults, firstResult);
    }

    private List<Gasto> findGastoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gasto.class));
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

    public Gasto findGasto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gasto.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gasto> rt = cq.from(Gasto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
