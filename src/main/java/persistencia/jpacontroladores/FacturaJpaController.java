/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpacontroladores;

import com.gofact.controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.entidades.Factura;
import persistencia.entidades.Gasto;
import persistencia.entidades.Proveedor;
import persistencia.entidades.Usuario;

/**
 *
 * @author camm
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gasto idgasto = factura.getIdgasto();
            if (idgasto != null) {
                idgasto = em.getReference(idgasto.getClass(), idgasto.getIdgasto());
                factura.setIdgasto(idgasto);
            }
            Proveedor idproveedor = factura.getIdproveedor();
            if (idproveedor != null) {
                idproveedor = em.getReference(idproveedor.getClass(), idproveedor.getIdproveedor());
                factura.setIdproveedor(idproveedor);
            }
            Usuario idusuario = factura.getIdusuario();
            if (idusuario != null) {
                idusuario = em.getReference(idusuario.getClass(), idusuario.getIdusuario());
                factura.setIdusuario(idusuario);
            }
            em.persist(factura);
            if (idgasto != null) {
                idgasto.getFacturaCollection().add(factura);
                idgasto = em.merge(idgasto);
            }
            if (idproveedor != null) {
                idproveedor.getFacturaCollection().add(factura);
                idproveedor = em.merge(idproveedor);
            }
            if (idusuario != null) {
                idusuario.getFacturaCollection().add(factura);
                idusuario = em.merge(idusuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdfactura());
            Gasto idgastoOld = persistentFactura.getIdgasto();
            Gasto idgastoNew = factura.getIdgasto();
            Proveedor idproveedorOld = persistentFactura.getIdproveedor();
            Proveedor idproveedorNew = factura.getIdproveedor();
            Usuario idusuarioOld = persistentFactura.getIdusuario();
            Usuario idusuarioNew = factura.getIdusuario();
            if (idgastoNew != null) {
                idgastoNew = em.getReference(idgastoNew.getClass(), idgastoNew.getIdgasto());
                factura.setIdgasto(idgastoNew);
            }
            if (idproveedorNew != null) {
                idproveedorNew = em.getReference(idproveedorNew.getClass(), idproveedorNew.getIdproveedor());
                factura.setIdproveedor(idproveedorNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                factura.setIdusuario(idusuarioNew);
            }
            factura = em.merge(factura);
            if (idgastoOld != null && !idgastoOld.equals(idgastoNew)) {
                idgastoOld.getFacturaCollection().remove(factura);
                idgastoOld = em.merge(idgastoOld);
            }
            if (idgastoNew != null && !idgastoNew.equals(idgastoOld)) {
                idgastoNew.getFacturaCollection().add(factura);
                idgastoNew = em.merge(idgastoNew);
            }
            if (idproveedorOld != null && !idproveedorOld.equals(idproveedorNew)) {
                idproveedorOld.getFacturaCollection().remove(factura);
                idproveedorOld = em.merge(idproveedorOld);
            }
            if (idproveedorNew != null && !idproveedorNew.equals(idproveedorOld)) {
                idproveedorNew.getFacturaCollection().add(factura);
                idproveedorNew = em.merge(idproveedorNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getFacturaCollection().remove(factura);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getFacturaCollection().add(factura);
                idusuarioNew = em.merge(idusuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdfactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            Gasto idgasto = factura.getIdgasto();
            if (idgasto != null) {
                idgasto.getFacturaCollection().remove(factura);
                idgasto = em.merge(idgasto);
            }
            Proveedor idproveedor = factura.getIdproveedor();
            if (idproveedor != null) {
                idproveedor.getFacturaCollection().remove(factura);
                idproveedor = em.merge(idproveedor);
            }
            Usuario idusuario = factura.getIdusuario();
            if (idusuario != null) {
                idusuario.getFacturaCollection().remove(factura);
                idusuario = em.merge(idusuario);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
