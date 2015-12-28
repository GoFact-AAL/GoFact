/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author camm
 */
public class GeneradorDataSet {
    public static PieDataset createPieDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Vivienda", 120);
        result.setValue("Alimentación", 220);
        result.setValue("Salud", 222);
        result.setValue("Educación", 322);
        result.setValue("Vestimenta", 10);
        return result;
    }
}
