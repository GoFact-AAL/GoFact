/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.reporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.ModeloFactura;
import modelo.persistencia.entidades.Usuario;
import org.jfree.chart.ChartPanel;
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
        this.vistaReporte.getBtnExcel().addActionListener(this);
        this.vistaReporte.getBtnGenerar().addActionListener(this);
        this.vistaReporte.getBtnGrafica().addActionListener(this);
    }

    private void mostrarGastos(){
        GastosTotales gastosTotales = new GastosTotales();
        gastosTotales.sumarRubrosFacturas(this.usuario.getFacturaList());
        this.vistaReporte.setGridGastos(Transformador.fromHashMapToDataModel(gastosTotales.getGastosTotales()));
    }

	private ChartPanel generarPanel() {
		GastosTotales gastos = new GastosTotales();
		gastos.sumarRubrosFacturas(this.usuario.getFacturaList());
		PieDataset dataset = GeneradorDataSet.createPieDataset(gastos.getGastosTotales());
        ChartPanel chartPanel = new ChartPanel(Graficador.createChart(dataset, "Rubros"));
		return chartPanel;
	}

    private void generarGrafica() {
        DialogGrafica grafica = new DialogGrafica(null, true);
		grafica.setContentPane(generarPanel());
        grafica.setVisible(true);
    }

    private void generarExcel() {
        JFileChooser  fileChooser = new JFileChooser();
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("xls", "xlsx");
        fileChooser.setFileFilter(fileFilter);
        this.vistaReporte.mostrarMensaje("Asigne una ubicación y nombre para el archivo:");
        int respuesta = fileChooser.showSaveDialog(null);
        String filename = "";

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            filename = fileChooser.getSelectedFile().toString();
        }
    }

    private void generarReporte() {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.vistaReporte.getBtnExcel()){
            generarExcel();
        }
        else if(ae.getSource() == this.vistaReporte.getBtnGenerar()){
            generarReporte();
        }
        else if(ae.getSource() == this.vistaReporte.getBtnGrafica()){
            generarGrafica();
        }
    }

}
