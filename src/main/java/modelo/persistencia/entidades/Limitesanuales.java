/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author camm
 */
@Entity
@Table(catalog = "", schema = "GOFACT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Limitesanuales.findAll", query = "SELECT l FROM Limitesanuales l"),
    @NamedQuery(name = "Limitesanuales.findByIdlimite", query = "SELECT l FROM Limitesanuales l WHERE l.idlimite = :idlimite"),
    @NamedQuery(name = "Limitesanuales.findByAnio", query = "SELECT l FROM Limitesanuales l WHERE l.anio = :anio"),
    @NamedQuery(name = "Limitesanuales.findByLimite", query = "SELECT l FROM Limitesanuales l WHERE l.limite = :limite")})
public class Limitesanuales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idlimite;
    @Basic(optional = false)
    @Column(nullable = false)
    private int anio;
    @Basic(optional = false)
    @Column(nullable = false)
    private int limite;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "IDCATEGORIA", nullable = false)
    @ManyToOne(optional = false)
    private Categoria categoria;

    public Limitesanuales() {
    }

    public Limitesanuales(Integer idlimite) {
        this.idlimite = idlimite;
    }

    public Limitesanuales(Integer idlimite, int anio, int limite) {
        this.idlimite = idlimite;
        this.anio = anio;
        this.limite = limite;
    }

    public Integer getIdlimite() {
        return idlimite;
    }

    public void setIdlimite(Integer idlimite) {
        this.idlimite = idlimite;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlimite != null ? idlimite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Limitesanuales)) {
            return false;
        }
        Limitesanuales other = (Limitesanuales) object;
        if ((this.idlimite == null && other.idlimite != null) || (this.idlimite != null && !this.idlimite.equals(other.idlimite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.entidades.Limitesanuales[ idlimite=" + idlimite + " ]";
    }
    
}
