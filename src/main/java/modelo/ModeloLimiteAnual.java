/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.persistencia.jpacontroladores.LimitesanualesJpaController;

/**
 *
 * @author camm
 */
public class ModeloLimiteAnual extends Modelo{
    private final LimitesanualesJpaController limites;

    public ModeloLimiteAnual() {
        this.limites = new LimitesanualesJpaController(emf);
    }

}
