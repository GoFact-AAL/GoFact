/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpacontroladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.entidades.Gasto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.entidades.Categoria;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

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

    public void create(Categoria categoria) throws PreexistingEntityException, Exception {
        if (categoria.getGastoList() == null) {
            categoria.setGastoList(new ArrayList<Gasto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Gasto> attachedGastoList = new ArrayList<Gasto>();
            for (Gasto gastoListGastoToAttach : categoria.getGastoList()) {
                gastoListGastoToAttach = em.getReference(gastoListGastoToAttach.getClass(), gastoListGastoToAttach.getIdgasto());
                attachedGastoList.add(gastoListGastoToAttach);
            }
            categoria.setGastoList(attachedGastoList);
            em.persist(categoria);
            for (Gasto gastoListGasto : categoria.getGastoList()) {
                Categoria oldIdcategoriaOfGastoListGasto = gastoListGasto.getIdcategoria();
                gastoListGasto.setIdcategoria(categoria);
                gastoListGasto = em.merge(gastoListGasto);
                if (oldIdcategoriaOfGastoListGasto != null) {
                    oldIdcategoriaOfGastoListGasto.getGastoList().remove(gastoListGasto);
                    oldIdcategoriaOfGastoListGasto = em.merge(oldIdcategoriaOfGastoListGasto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoria(categoria.getIdcategoria()) != null) {
                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
            }
            throw ex;
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
            List<Gasto> gastoListOld = persistentCategoria.getGastoList();
            List<Gasto> gastoListNew = categoria.getGastoList();
            List<String> illegalOrphanMessages = null;
            for (Gasto gastoListOldGasto : gastoListOld) {
                if (!gastoListNew.contains(gastoListOldGasto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Gasto " + gastoListOldGasto + " since its idcategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Gasto> attachedGastoListNew = new ArrayList<Gasto>();
            for (Gasto gastoListNewGastoToAttach : gastoListNew) {
                gastoListNewGastoToAttach = em.getReference(gastoListNewGastoToAttach.getClass(), gastoListNewGastoToAttach.getIdgasto());
                attachedGastoListNew.add(gastoListNewGastoToAttach);
            }
            gastoListNew = attachedGastoListNew;
            categoria.setGastoList(gastoListNew);
            categoria = em.merge(categoria);
            for (Gasto gastoListNewGasto : gastoListNew) {
                if (!gastoListOld.contains(gastoListNewGasto)) {
                    Categoria oldIdcategoriaOfGastoListNewGasto = gastoListNewGasto.getIdcategoria();
                    gastoListNewGasto.setIdcategoria(categoria);
                    gastoListNewGasto = em.merge(gastoListNewGasto);
                    if (oldIdcategoriaOfGastoListNewGasto != null && !oldIdcategoriaOfGastoListNewGasto.equals(categoria)) {
                        oldIdcategoriaOfGastoListNewGasto.getGastoList().remove(gastoListNewGasto);
                        oldIdcategoriaOfGastoListNewGasto = em.merge(oldIdcategoriaOfGastoListNewGasto);
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
            List<Gasto> gastoListOrphanCheck = categoria.getGastoList();
            for (Gasto gastoListOrphanCheckGasto : gastoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Gasto " + gastoListOrphanCheckGasto + " in its gastoList field has a non-nullable idcategoria field.");
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
