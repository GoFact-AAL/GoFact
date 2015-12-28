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
import persistencia.entidades.Proveedor;
import persistencia.entidades.Usuario;
import persistencia.entidades.Gasto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.entidades.Factura;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

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
        if (factura.getGastoList() == null) {
            factura.setGastoList(new ArrayList<Gasto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
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
            List<Gasto> attachedGastoList = new ArrayList<Gasto>();
            for (Gasto gastoListGastoToAttach : factura.getGastoList()) {
                gastoListGastoToAttach = em.getReference(gastoListGastoToAttach.getClass(), gastoListGastoToAttach.getIdgasto());
                attachedGastoList.add(gastoListGastoToAttach);
            }
            factura.setGastoList(attachedGastoList);
            em.persist(factura);
            if (idproveedor != null) {
                idproveedor.getFacturaList().add(factura);
                idproveedor = em.merge(idproveedor);
            }
            if (idusuario != null) {
                idusuario.getFacturaList().add(factura);
                idusuario = em.merge(idusuario);
            }
            for (Gasto gastoListGasto : factura.getGastoList()) {
                Factura oldIdfacturaOfGastoListGasto = gastoListGasto.getIdfactura();
                gastoListGasto.setIdfactura(factura);
                gastoListGasto = em.merge(gastoListGasto);
                if (oldIdfacturaOfGastoListGasto != null) {
                    oldIdfacturaOfGastoListGasto.getGastoList().remove(gastoListGasto);
                    oldIdfacturaOfGastoListGasto = em.merge(oldIdfacturaOfGastoListGasto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdfactura());
            Proveedor idproveedorOld = persistentFactura.getIdproveedor();
            Proveedor idproveedorNew = factura.getIdproveedor();
            Usuario idusuarioOld = persistentFactura.getIdusuario();
            Usuario idusuarioNew = factura.getIdusuario();
            List<Gasto> gastoListOld = persistentFactura.getGastoList();
            List<Gasto> gastoListNew = factura.getGastoList();
            List<String> illegalOrphanMessages = null;
            for (Gasto gastoListOldGasto : gastoListOld) {
                if (!gastoListNew.contains(gastoListOldGasto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Gasto " + gastoListOldGasto + " since its idfactura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idproveedorNew != null) {
                idproveedorNew = em.getReference(idproveedorNew.getClass(), idproveedorNew.getIdproveedor());
                factura.setIdproveedor(idproveedorNew);
            }
            if (idusuarioNew != null) {
                idusuarioNew = em.getReference(idusuarioNew.getClass(), idusuarioNew.getIdusuario());
                factura.setIdusuario(idusuarioNew);
            }
            List<Gasto> attachedGastoListNew = new ArrayList<Gasto>();
            for (Gasto gastoListNewGastoToAttach : gastoListNew) {
                gastoListNewGastoToAttach = em.getReference(gastoListNewGastoToAttach.getClass(), gastoListNewGastoToAttach.getIdgasto());
                attachedGastoListNew.add(gastoListNewGastoToAttach);
            }
            gastoListNew = attachedGastoListNew;
            factura.setGastoList(gastoListNew);
            factura = em.merge(factura);
            if (idproveedorOld != null && !idproveedorOld.equals(idproveedorNew)) {
                idproveedorOld.getFacturaList().remove(factura);
                idproveedorOld = em.merge(idproveedorOld);
            }
            if (idproveedorNew != null && !idproveedorNew.equals(idproveedorOld)) {
                idproveedorNew.getFacturaList().add(factura);
                idproveedorNew = em.merge(idproveedorNew);
            }
            if (idusuarioOld != null && !idusuarioOld.equals(idusuarioNew)) {
                idusuarioOld.getFacturaList().remove(factura);
                idusuarioOld = em.merge(idusuarioOld);
            }
            if (idusuarioNew != null && !idusuarioNew.equals(idusuarioOld)) {
                idusuarioNew.getFacturaList().add(factura);
                idusuarioNew = em.merge(idusuarioNew);
            }
            for (Gasto gastoListNewGasto : gastoListNew) {
                if (!gastoListOld.contains(gastoListNewGasto)) {
                    Factura oldIdfacturaOfGastoListNewGasto = gastoListNewGasto.getIdfactura();
                    gastoListNewGasto.setIdfactura(factura);
                    gastoListNewGasto = em.merge(gastoListNewGasto);
                    if (oldIdfacturaOfGastoListNewGasto != null && !oldIdfacturaOfGastoListNewGasto.equals(factura)) {
                        oldIdfacturaOfGastoListNewGasto.getGastoList().remove(gastoListNewGasto);
                        oldIdfacturaOfGastoListNewGasto = em.merge(oldIdfacturaOfGastoListNewGasto);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Gasto> gastoListOrphanCheck = factura.getGastoList();
            for (Gasto gastoListOrphanCheckGasto : gastoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the Gasto " + gastoListOrphanCheckGasto + " in its gastoList field has a non-nullable idfactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor idproveedor = factura.getIdproveedor();
            if (idproveedor != null) {
                idproveedor.getFacturaList().remove(factura);
                idproveedor = em.merge(idproveedor);
            }
            Usuario idusuario = factura.getIdusuario();
            if (idusuario != null) {
                idusuario.getFacturaList().remove(factura);
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

    public Factura findFacturaByIdentificador(String identificador){
        EntityManager em = getEntityManager();
        List<Factura> factura =  em.createNamedQuery("Factura.findByIdentificador", Factura.class)
                .setParameter("identificador", identificador)
                .getResultList();
        return (factura.isEmpty())? null : factura.get(0);
    }
}
