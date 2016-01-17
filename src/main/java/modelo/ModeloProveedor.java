/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import modelo.persistencia.entidades.Proveedor;
import modelo.persistencia.jpacontroladores.ProveedorJpaController;
import modelo.persistencia.jpacontroladores.exceptions.IllegalOrphanException;
import modelo.persistencia.jpacontroladores.exceptions.NonexistentEntityException;

/**
 *
 * @author camm
 */
public class ModeloProveedor extends Modelo{

    public final ProveedorJpaController proveedor;

    public ModeloProveedor() {
        this.proveedor = new ProveedorJpaController(this.emf);
    }

    public void create(Proveedor proveedor) {
        this.proveedor.create(proveedor);
    }

    public void edit(Proveedor proveedor){
        try {
            this.proveedor.edit(proveedor);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy(Integer idProveedor){
        try {
            this.proveedor.destroy(idProveedor);
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(ModeloProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return this.proveedor.findProveedorEntities();
    }

    public Proveedor findProveedorByRUC(String RUC) {
        EntityManager em = this.proveedor.getEntityManager();
        List<Proveedor> proveedor =  em.createNamedQuery("Proveedor.findByRuc", Proveedor.class)
                .setParameter("ruc", RUC)
                .getResultList();
        return (proveedor.isEmpty())? null : proveedor.get(0);
    }
}
