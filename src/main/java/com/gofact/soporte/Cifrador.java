/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.soporte;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author camm
 */
public class Cifrador {
    public String md5(String textoPlano) {
        try{
            MessageDigest alg = MessageDigest.getInstance("MD5");
            alg.reset();
            alg.update(textoPlano.getBytes());
            byte[] msgDigest = alg.digest();

            BigInteger number = new BigInteger(1,msgDigest);

            String str = number.toString(16);
            return str;
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return "";
        }
    }
}
