/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpacontroladores;

import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.entidades.Gasto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.entidades.Categoria;

/**
 *
 * @author camm
 */
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) {
        if (categoria.getGastoCollection() == null) {
            categoria.setGastoCollection(new ArrayList<Gasto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gasto> attachedGastoCollection = new ArrayList<Gasto>();
            for (Gasto gastoCollectionGastoToAttach : categoria.getGastoCollection()) {
                gastoCollectionGastoToAttach = em.getReference(gastoCollectionGastoToAttach.getClass(), gastoCollectionGastoToAttach.getIdgasto());
                attachedGastoCollection.add(gastoCollectionGastoToAttach);
            }
            categoria.setGastoCollection(attachedGastoCollection);
            em.persist(categoria);
            for (Gasto gastoCollectionGasto : categoria.getGastoCollection()) {
                Categoria oldIdcategoriaOfGastoCollectionGasto = gastoCollectionGasto.getIdcategoria();
                gastoCollectionGasto.setIdcategoria(categoria);
                gastoCollectionGasto = em.merge(gastoCollectionGasto);
                if (oldIdcategoriaOfGastoCollectionGasto != null) {
                    oldIdcategoriaOfGastoCollectionGasto.getGastoCollection().remove(gastoCollectionGasto);
                    oldIdcategoriaOfGastoCollectionGasto = em.merge(oldIdcategoriaOfGastoCollectionGasto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getIdcategoria());
            Collection<Gasto> gastoCollectionOld = persistentCategoria.getGastoCollection();
            Collection<Gasto> gastoCollectionNew = categoria.getGastoCollection();
            List<String> illegalOrphanMessages = null;
            for (Gasto gastoCollectionOldGasto : gastoCollectionOld) {
                if (!gastoCollectionNew.contains(gastoCollectionOldGasto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Gasto " + gastoCollectionOldGasto + " since its idcategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Gasto> attachedGastoCollectionNew = new ArrayList<Gasto>();
            for (Gasto gastoCollectionNewGastoToAttach : gastoCollectionNew) {
                gastoCollectionNewGastoToAttach = em.getReference(gastoCollectionNewGastoToAttach.getClass(), gastoCollectionNewGastoToAttach.getIdgasto());
                attachedGastoCollectionNew.add(gastoCollectionNewGastoToAttach);
            }
            gastoCollectionNew = attachedGastoCollectionNew;
            categoria.setGastoCollection(gastoCollectionNew);
            categoria = em.merge(categoria);
            for (Gasto gastoCollectionNewGasto : gastoCollectionNew) {
                if (!gastoCollectionOld.contains(gastoCollectionNewGasto)) {
                    Categoria oldIdcategoriaOfGastoCollectionNewGasto = gastoCollectionNewGasto.getIdcategoria();
                    gastoCollectionNewGasto.setIdcategoria(categoria);
                    gastoCollectionNewGasto = em.merge(gastoCollectionNewGasto);
                    if (oldIdcategoriaOfGastoCollectionNewGasto != null && !oldIdcategoriaOfGastoCollectionNewGasto.equals(categoria)) {
                        oldIdcategoriaOfGastoCollectionNewGasto.getGastoCollection().remove(gastoCollectionNewGasto);
                        oldIdcategoriaOfGastoCollectionNewGasto = em.merge(oldIdcategoriaOfGastoCollectionNewGasto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoria.getIdcategoria();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Gasto> gastoCollectionOrphanCheck = categoria.getGastoCollection();
            for (Gasto gastoCollectionOrphanCheckGasto : gastoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Gasto " + gastoCollectionOrphanCheckGasto + " in its gastoCollection field has a non-nullable idcategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
