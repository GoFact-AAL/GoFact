/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import java.util.HashMap;
import java.util.Map;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author camm
 */
public class GeneradorDataSet {
    public static PieDataset createPieDataset(HashMap<String, Integer> gastos) {
        DefaultPieDataset result = new DefaultPieDataset();

		for (Map.Entry<String, Integer> gasto : gastos.entrySet()) {
			result.setValue(gasto.getKey(), gasto.getValue()/100);
		}

        return result;
    }
}
