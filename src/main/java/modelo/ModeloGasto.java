/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.persistencia.entidades.Gasto;
import modelo.persistencia.jpacontroladores.GastoJpaController;
import modelo.persistencia.jpacontroladores.exceptions.NonexistentEntityException;

/**
 *
 * @author camm
 */
public class ModeloGasto extends Modelo{
    private final GastoJpaController gastoControl;

    public ModeloGasto() {
        this.gastoControl = new GastoJpaController(emf);
    }

    public void create(Gasto gasto) {
        this.gastoControl.create(gasto);
    }

    public void edit(Gasto gasto) {
        try {
            this.gastoControl.edit(gasto);
        } catch (Exception ex) {
            Logger.getLogger(ModeloGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy(Integer idGasto) {
        try {
            this.gastoControl.destroy(idGasto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloGasto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
