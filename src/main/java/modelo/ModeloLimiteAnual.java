/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.persistencia.entidades.Limitesanuales;
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

	public List<Limitesanuales> findLimitesByYear(Integer year){
		EntityManager em = this.limites.getEntityManager();
		List<Limitesanuales> limites =  em.createNamedQuery("Limitesanuales.findByAnio", Limitesanuales.class)
				.setParameter("anio", year)
				.getResultList();
		return limites;
	}
}
