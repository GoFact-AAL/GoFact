/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author camm
 */
public class Totales {
    public Double deducibles;
    public Double noDeducibles;
    public Double totalSinIva;
    public Double total;
    
    public void calculos(HashMap<String, Integer>  gastos){
        sumarNoDeduciblesValores(gastos);
        sumarDeduciblesValores(gastos);
        calculoTotalSinIva();
        calculoTotal();
    }
    
    private void sumarNoDeduciblesValores(HashMap<String, Integer>  gastos){
        noDeducibles = (gastos.get("Otros") == null)? 0 : (double) gastos.get("Otros")/100;
    }

    private void sumarDeduciblesValores(HashMap<String, Integer>  gastos){
        Integer suma = 0;
        for (Map.Entry<String, Integer> entrySet : gastos.entrySet()) {
            if (!entrySet.getKey().equals("Otros")) {
                suma += entrySet.getValue();
            }
        }
        deducibles = (double) suma/100;
    }
    
    private void calculoTotalSinIva(){
        totalSinIva = deducibles + noDeducibles;
    }
    
    private void calculoTotal(){
        total = totalSinIva*(1+12.0/100.0);
    }
}
