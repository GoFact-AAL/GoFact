/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

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
@Table(name = "FACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdfactura", query = "SELECT f FROM Factura f WHERE f.idfactura = :idfactura"),
    @NamedQuery(name = "Factura.findByTotalsiniva", query = "SELECT f FROM Factura f WHERE f.totalsiniva = :totalsiniva"),
    @NamedQuery(name = "Factura.findByIva", query = "SELECT f FROM Factura f WHERE f.iva = :iva"),
    @NamedQuery(name = "Factura.findByTotaltotal", query = "SELECT f FROM Factura f WHERE f.totaltotal = :totaltotal"),
    @NamedQuery(name = "Factura.findByDireccion", query = "SELECT f FROM Factura f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "Factura.findByTelefono", query = "SELECT f FROM Factura f WHERE f.telefono = :telefono")})
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFACTURA")
    private Integer idfactura;
    @Basic(optional = false)
    @Column(name = "TOTALSINIVA")
    private int totalsiniva;
    @Basic(optional = false)
    @Column(name = "IVA")
    private int iva;
    @Basic(optional = false)
    @Column(name = "TOTALTOTAL")
    private int totaltotal;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;
    @JoinColumn(name = "IDGASTO", referencedColumnName = "IDGASTO")
    @ManyToOne(optional = false)
    private Gasto idgasto;
    @JoinColumn(name = "IDPROVEEDOR", referencedColumnName = "IDPROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idproveedor;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Factura() {
    }

    public Factura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Factura(Integer idfactura, int totalsiniva, int iva, int totaltotal) {
        this.idfactura = idfactura;
        this.totalsiniva = totalsiniva;
        this.iva = iva;
        this.totaltotal = totaltotal;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public int getTotalsiniva() {
        return totalsiniva;
    }

    public void setTotalsiniva(int totalsiniva) {
        this.totalsiniva = totalsiniva;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getTotaltotal() {
        return totaltotal;
    }

    public void setTotaltotal(int totaltotal) {
        this.totaltotal = totaltotal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Gasto getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(Gasto idgasto) {
        this.idgasto = idgasto;
    }

    public Proveedor getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Proveedor idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfactura != null ? idfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idfactura == null && other.idfactura != null) || (this.idfactura != null && !this.idfactura.equals(other.idfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Factura[ idfactura=" + idfactura + " ]";
    }
    
}
