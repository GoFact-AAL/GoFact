/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpacontroladores;

import com.gofact.controlador.exceptions.IllegalOrphanException;
import com.gofact.controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.entidades.Categoria;
import persistencia.entidades.Factura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.entidades.Gasto;

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
        if (gasto.getFacturaCollection() == null) {
            gasto.setFacturaCollection(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria idcategoria = gasto.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                gasto.setIdcategoria(idcategoria);
            }
            Collection<Factura> attachedFacturaCollection = new ArrayList<Factura>();
            for (Factura facturaCollectionFacturaToAttach : gasto.getFacturaCollection()) {
                facturaCollectionFacturaToAttach = em.getReference(facturaCollectionFacturaToAttach.getClass(), facturaCollectionFacturaToAttach.getIdfactura());
                attachedFacturaCollection.add(facturaCollectionFacturaToAttach);
            }
            gasto.setFacturaCollection(attachedFacturaCollection);
            em.persist(gasto);
            if (idcategoria != null) {
                idcategoria.getGastoCollection().add(gasto);
                idcategoria = em.merge(idcategoria);
            }
            for (Factura facturaCollectionFactura : gasto.getFacturaCollection()) {
                Gasto oldIdgastoOfFacturaCollectionFactura = facturaCollectionFactura.getIdgasto();
                facturaCollectionFactura.setIdgasto(gasto);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
                if (oldIdgastoOfFacturaCollectionFactura != null) {
                    oldIdgastoOfFacturaCollectionFactura.getFacturaCollection().remove(facturaCollectionFactura);
                    oldIdgastoOfFacturaCollectionFactura = em.merge(oldIdgastoOfFacturaCollectionFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gasto gasto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gasto persistentGasto = em.find(Gasto.class, gasto.getIdgasto());
            Categoria idcategoriaOld = persistentGasto.getIdcategoria();
            Categoria idcategoriaNew = gasto.getIdcategoria();
            Collection<Factura> facturaCollectionOld = persistentGasto.getFacturaCollection();
            Collection<Factura> facturaCollectionNew = gasto.getFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaCollectionOldFactura : facturaCollectionOld) {
                if (!facturaCollectionNew.contains(facturaCollectionOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaCollectionOldFactura + " since its idgasto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                gasto.setIdcategoria(idcategoriaNew);
            }
            Collection<Factura> attachedFacturaCollectionNew = new ArrayList<Factura>();
            for (Factura facturaCollectionNewFacturaToAttach : facturaCollectionNew) {
                facturaCollectionNewFacturaToAttach = em.getReference(facturaCollectionNewFacturaToAttach.getClass(), facturaCollectionNewFacturaToAttach.getIdfactura());
                attachedFacturaCollectionNew.add(facturaCollectionNewFacturaToAttach);
            }
            facturaCollectionNew = attachedFacturaCollectionNew;
            gasto.setFacturaCollection(facturaCollectionNew);
            gasto = em.merge(gasto);
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getGastoCollection().remove(gasto);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getGastoCollection().add(gasto);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            for (Factura facturaCollectionNewFactura : facturaCollectionNew) {
                if (!facturaCollectionOld.contains(facturaCollectionNewFactura)) {
                    Gasto oldIdgastoOfFacturaCollectionNewFactura = facturaCollectionNewFactura.getIdgasto();
                    facturaCollectionNewFactura.setIdgasto(gasto);
                    facturaCollectionNewFactura = em.merge(facturaCollectionNewFactura);
                    if (oldIdgastoOfFacturaCollectionNewFactura != null && !oldIdgastoOfFacturaCollectionNewFactura.equals(gasto)) {
                        oldIdgastoOfFacturaCollectionNewFactura.getFacturaCollection().remove(facturaCollectionNewFactura);
                        oldIdgastoOfFacturaCollectionNewFactura = em.merge(oldIdgastoOfFacturaCollectionNewFactura);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Factura> facturaCollectionOrphanCheck = gasto.getFacturaCollection();
            for (Factura facturaCollectionOrphanCheckFactura : facturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Gasto (" + gasto + ") cannot be destroyed since the Factura " + facturaCollectionOrphanCheckFactura + " in its facturaCollection field has a non-nullable idgasto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria idcategoria = gasto.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getGastoCollection().remove(gasto);
                idcategoria = em.merge(idcategoria);
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
