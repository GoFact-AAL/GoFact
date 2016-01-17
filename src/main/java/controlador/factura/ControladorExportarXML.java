/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controlador.factura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.ModeloFactura;
import modelo.persistencia.entidades.Factura;
import presentacion.factura.DialogExportarXML;
import soporte.JaxbTranslator;
import soporte.Transformador;

/**
 *
 * @author camm
 */
public class ControladorExportarXML implements ActionListener {

	private final DialogExportarXML vistaXML;
	private final List<Factura> facturas;

	public ControladorExportarXML(DialogExportarXML vistaXML
		, List<Factura> facturas) {
		this.vistaXML = vistaXML;
		this.facturas = facturas;

		mostrarFacturas(facturas);

		this.vistaXML.getBtnExportar().addActionListener(this);
		this.vistaXML.getBtnCancelar().addActionListener(this);
	}

	private void exportarXML(Factura factura) {
		JFileChooser  fileChooser = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("xml", "XML");
		fileChooser.setFileFilter(fileFilter);
		JOptionPane.showMessageDialog(this.vistaXML, "Asigne una ubicación y nombre para el archivo:");
		int respuesta = fileChooser.showSaveDialog(null);
		String filename;

		if (respuesta == JFileChooser.APPROVE_OPTION) {
			filename = fileChooser.getSelectedFile().toString() + ".xml";
			saveFactura(factura, filename);
		}
	}

	private void saveFactura(Factura factura, String filepath){
		JaxbTranslator jaxbTranslator = new JaxbTranslator();
		File file = new File(filepath);
		if(archivoValido(file)){
			jaxbTranslator.toXMLFile(factura, file);
			this.vistaXML.mostrarMensaje("¡Éxito!\nSu archivo se encuentra en: " + filepath);
		}
	}

	private boolean archivoValido(File file){
		if (file.exists()) {
			if(this.vistaXML.confirmacion("¿Desea sobreescribir el archivo?")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return true;
		}
	}

	private void salirXML() {
		this.vistaXML.dispose();
	}

	private boolean seleccionUnica() {
		int fila = this.vistaXML.getjTable1().getSelectedRow();
		int numFilas = this.vistaXML.getjTable1().getSelectedRowCount();
		if(fila >= 0 && numFilas == 1){
			return true;
		}
		else{
			this.vistaXML.mostrarMensaje("Recuerde seleccionar solo una fila");
			return false;
		}
	}

	private void mostrarFacturas(List<Factura> facturas){
		DefaultTableModel tablaFacturas = Transformador.fromListFacturaToDataModel(facturas);
		this.vistaXML.getjTable1().setModel(tablaFacturas);
	}

	private Factura obtenerFactura() {
		ModeloFactura modeloFactura = new ModeloFactura();
		return modeloFactura.findFacturaByIdentificador(this.vistaXML.getIdentificador());
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.vistaXML.getBtnExportar()){
			if (seleccionUnica()) {
				Factura factura = obtenerFactura();
				exportarXML(factura);
			}
		}
		else if(ae.getSource() == this.vistaXML.getBtnCancelar()){
			salirXML();
		}
	}
}
