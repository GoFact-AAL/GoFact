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
import modelo.persistencia.entidades.Factura;
import modelo.persistencia.entidades.Gasto;
import modelo.persistencia.jpacontroladores.FacturaJpaController;
import modelo.persistencia.jpacontroladores.exceptions.IllegalOrphanException;
import modelo.persistencia.jpacontroladores.exceptions.NonexistentEntityException;

/**
 *
 * @author camm
 */
public class ModeloFactura extends Modelo{

    private final FacturaJpaController facturaControl;
    
    public ModeloFactura() {
        this.facturaControl = new FacturaJpaController(emf);
    }

    public void create(Factura factura) {
        this.facturaControl.create(factura);
    }

    public void edit(Factura factura) {
        try {
            ModeloGasto modeloGasto = new ModeloGasto();
            for (Gasto gasto : factura.getGastoList()) {
                modeloGasto.edit(gasto);
            }
            this.facturaControl.edit(factura);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy(Integer idFactura) {
        try {
            Factura factura = this.facturaControl.findFactura(idFactura);
            destroyGastos(factura);
            this.facturaControl.destroy(idFactura);
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(ModeloFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroyGastos(Factura factura) {
        ModeloGasto modeloGasto = new ModeloGasto();
        for (Gasto gasto: factura.getGastoList()) {
            modeloGasto.destroy(gasto.getIdgasto());
        }
    }

    public Factura findFacturaByIdentificador(String identificador) {
        EntityManager em = this.facturaControl.getEntityManager();
        List<Factura> factura =  em.createNamedQuery("Factura.findByIdentificador", Factura.class)
                .setParameter("identificador", identificador)
                .getResultList();
        return (factura.isEmpty())? null : factura.get(0);
    }
    
}
