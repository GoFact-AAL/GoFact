/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

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
    @UniqueConstraint(columnNames = {"CEDULAIDENTIDAD"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByCedulaidentidad", query = "SELECT u FROM Usuario u WHERE u.cedulaidentidad = :cedulaidentidad"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByRespuesta1", query = "SELECT u FROM Usuario u WHERE u.respuesta1 = :respuesta1"),
    @NamedQuery(name = "Usuario.findByRespuesta2", query = "SELECT u FROM Usuario u WHERE u.respuesta2 = :respuesta2"),
    @NamedQuery(name = "Usuario.findByPregunta1", query = "SELECT u FROM Usuario u WHERE u.pregunta1 = :pregunta1"),
    @NamedQuery(name = "Usuario.findByPregunta2", query = "SELECT u FROM Usuario u WHERE u.pregunta2 = :pregunta2")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idusuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String cedulaidentidad;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String apellido;
    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private String password;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String respuesta1;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String respuesta2;
    @Basic(optional = false)
    @Column(nullable = false)
    private int pregunta1;
    @Basic(optional = false)
    @Column(nullable = false)
    private int pregunta2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    private List<Factura> facturaList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String cedulaidentidad, String nombre, String apellido, String password, String respuesta1, String respuesta2, int pregunta1, int pregunta2) {
        this.idusuario = idusuario;
        this.cedulaidentidad = cedulaidentidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getCedulaidentidad() {
        return cedulaidentidad;
    }

    public void setCedulaidentidad(String cedulaidentidad) {
        this.cedulaidentidad = cedulaidentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public int getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(int pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public int getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(int pregunta2) {
        this.pregunta2 = pregunta2;
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
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.entidades.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
