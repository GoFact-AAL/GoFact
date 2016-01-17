/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import modelo.persistencia.entidades.Factura;

/**
 *
 * @author camm
 */
public final class JaxbTranslator {

	public JaxbTranslator() {}

	public void toXMLFile(Object object, File file){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Factura.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(object, file);
		} catch (JAXBException e) {
			System.err.println(e);
		}
	}
}
