/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloFactura;
import modelo.persistencia.entidades.Usuario;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import presentacion.reportes.DialogGrafica;
import presentacion.reportes.DialogReporte;
import soporte.GastosTotales;
import soporte.GeneradorDataSet;
import soporte.Graficador;
import soporte.Transformador;

/**
 *
 * @author camm
 */
public class ControladorGenerarReporte implements ActionListener{

    private final DialogReporte vistaReporte;
    private final ModeloFactura modeloFactura;
    private final Usuario usuario;

    public ControladorGenerarReporte(DialogReporte vistaReporte
                        , ModeloFactura modeloFactura
                        , Usuario usuario) {
        this.vistaReporte = vistaReporte;
        this.modeloFactura = modeloFactura;
        this.usuario = usuario;

        mostrarGastos();
        this.vistaReporte.getBtnGrafica().addActionListener(this);
    }

    private void mostrarGastos(){
        GastosTotales gastosTotales = new GastosTotales();
        gastosTotales.sumarRubrosFacturas(this.usuario.getFacturaList());
        this.vistaReporte.setGridGastos(Transformador.fromHashMapToDataModel(gastosTotales.getGastosTotales()));
    }

	private ChartPanel generarPanel(CategoryDataset dataset) {
        ChartPanel chartPanel = new ChartPanel(Graficador.createBarChart(dataset, "Rubros"));
		return chartPanel;
	}

	private ChartPanel generarPanel(PieDataset dataset) {
        ChartPanel chartPanel = new ChartPanel(Graficador.createChart(dataset, "Rubros"));
		return chartPanel;
	}

    private void generarGrafica() {
        DialogGrafica grafica = new DialogGrafica(null, true);
		GastosTotales gastos = new GastosTotales();
		gastos.sumarRubrosFacturas(this.usuario.getFacturaList());
		if (this.vistaReporte.getRdbtnPasteles().isSelected()) {
			PieDataset dataset = GeneradorDataSet.createPieDataset(gastos.getGastosTotales());
			grafica.setContentPane(generarPanel(dataset));
		} else {
			CategoryDataset dataset = GeneradorDataSet.createCategoryDataset(gastos.getGastosTotales());
			grafica.setContentPane(generarPanel(dataset));
		}
        grafica.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.vistaReporte.getBtnGrafica()){
            generarGrafica();
        }
    }

}
