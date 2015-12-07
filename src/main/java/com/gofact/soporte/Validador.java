/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 *
 * @author camm
 */
public class Validador {

    public Validador() {
    }

    public static boolean rucValido(String ruc){
        if (ruc.length() > 13 || ruc.length() < 13
                || !Pattern.matches("[0-9]+", ruc)){
            return false;
        }
        else{
            char numerosCI [] = new char [10];
            char numerosRUC [] = new char [3];
            ruc.getChars(0, 10, numerosCI, 0);
            ruc.getChars(10, 13, numerosRUC, 0);
            return luhn(charsANumeros(numerosCI))
                    && (new String(numerosRUC)).equals("001");
        }
    }

    public static boolean cedulaValida(String cedula){
        if (cedula.length() > 10 || cedula.length() < 10
                || !Pattern.matches("[0-9]+", cedula)) {
            return false;
        }
        else{
            char numerosCI [] = new char [10];
            cedula.getChars(0, 10, numerosCI, 0);
            return luhn(charsANumeros(numerosCI));
        }
    }

    private static boolean luhn(int numeros[]){
        int numVerificacion = numeros[numeros.length - 1];
        int suma = 0;

        for (int i = 0; i < numeros.length - 1; i+=2) {
            numeros[i] = numeros[i]*2;
            if(numeros[i] > 9){
                numeros[i] -= 9;
            }
        }

        for (int i = 0; i < numeros.length - 1; i++) {
            suma += numeros[i];
        }

        int resultado = 10 - suma%10;
        resultado = (resultado < 10)? resultado : 0;

        return (resultado == numVerificacion);
    }

    public static boolean contrasenaValida(String contrasena){
        Pattern expR = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])"
                +".{8,16}$");
        Matcher comp = expR.matcher(contrasena); //comprobador

        Pattern espacios = Pattern.compile("([ ])");
        Matcher compEsp = espacios.matcher(contrasena);

        if(!comp.find() || compEsp.find()) {
            return false;
        }

        return true;
    }

    private static int[] charsANumeros(char[] chars) {
        int [] numeros = new int [chars.length];
        for (int i = 0; i < numeros.length; i++) {
                numeros[i] = (int) (chars[i]) - 48;
            }
        return numeros;
    }

    //con toUpperCase se hace mayuscula lo q recibe
    public void mayusculas() {
        String cadena = "hola dey";
        System.out.println(cadena.toUpperCase());
    }
}
