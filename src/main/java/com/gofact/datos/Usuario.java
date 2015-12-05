/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.datos;

/**
 *
 * @author camm
 */
public class Usuario extends Dato{
    private String cedula;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String respuesta1;
    private String respuesta2;
    private int pregunta1;
    private int pregunta2;

    public Usuario() {
    }

    public Usuario(String cedula, String nombre, String apellido, String contrasena, String respuesta1, String respuesta2, int pregunta1, int pregunta2) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.pregunta1 = pregunta1;
        this.pregunta2 = pregunta2;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public int getPregunta1() {
        return pregunta1;
    }

    public int getPregunta2() {
        return pregunta2;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public void setPregunta1(int pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public void setPregunta2(int pregunta2) {
        this.pregunta2 = pregunta2;
    }
}
