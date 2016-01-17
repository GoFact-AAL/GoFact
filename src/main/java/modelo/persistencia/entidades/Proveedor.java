/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author camm
 */
@Entity
@Table(catalog = "", schema = "GOFACT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RUC"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor"),
    @NamedQuery(name = "Proveedor.findByRuc", query = "SELECT p FROM Proveedor p WHERE p.ruc = :ruc"),
    @NamedQuery(name = "Proveedor.findByRazonsocial", query = "SELECT p FROM Proveedor p WHERE p.razonsocial = :razonsocial"),
    @NamedQuery(name = "Proveedor.findByNombrecomercial", query = "SELECT p FROM Proveedor p WHERE p.nombrecomercial = :nombrecomercial"),
    @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedor.findByCiudad", query = "SELECT p FROM Proveedor p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Proveedor.findByPais", query = "SELECT p FROM Proveedor p WHERE p.pais = :pais"),
    @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idproveedor;
    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private String ruc;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String razonsocial;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nombrecomercial;
    @Column(length = 100)
    private String direccion;
    @Column(length = 30)
    private String ciudad;
    @Column(length = 30)
    private String pais;
    @Column(length = 20)
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproveedor")
    private List<Factura> facturaList;

    public Proveedor() {
    }

    public Proveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Proveedor(Integer idproveedor, String ruc, String razonsocial, String nombrecomercial) {
        this.idproveedor = idproveedor;
        this.ruc = ruc;
        this.razonsocial = razonsocial;
        this.nombrecomercial = nombrecomercial;
    }

	@XmlTransient
    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproveedor != null ? idproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idproveedor == null && other.idproveedor != null) || (this.idproveedor != null && !this.idproveedor.equals(other.idproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.entidades.Proveedor[ idproveedor=" + idproveedor + " ]";
    }

}
