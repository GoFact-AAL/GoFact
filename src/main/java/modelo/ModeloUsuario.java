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
import modelo.persistencia.entidades.Usuario;
import modelo.persistencia.jpacontroladores.UsuarioJpaController;
import modelo.persistencia.jpacontroladores.exceptions.IllegalOrphanException;
import modelo.persistencia.jpacontroladores.exceptions.NonexistentEntityException;

/**
 *
 * @author camm
 */
public class ModeloUsuario extends Modelo{

    private UsuarioJpaController usuarioControl;

    public ModeloUsuario() {
        this.usuarioControl = new UsuarioJpaController(emf);
    }

    public void create(Usuario usuario){
        this.usuarioControl.create(usuario);
    }


    public void edit(Usuario usuario){
        try {
            this.usuarioControl.edit(usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy(Integer usuarioId){
        try {
            Usuario usuario = this.usuarioControl.findUsuario(usuarioId);
            destroyFacturas(usuario);
            this.usuarioControl.destroy(usuarioId);
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroyFacturas(Usuario usuario){
        ModeloFactura modeloFactura = new ModeloFactura();
        for (Factura factura : usuario.getFacturaList()) {
            modeloFactura.destroy(factura.getIdentificador());
        }
    }

    public Usuario findUserByCI(String ci){
        EntityManager em = this.usuarioControl.getEntityManager();
        List<Usuario> usuario =  em.createNamedQuery("Usuario.findByCedulaidentidad", Usuario.class)
                .setParameter("cedulaidentidad", ci)
                .getResultList();
        return (usuario.isEmpty())? null : usuario.get(0);
    }
}
