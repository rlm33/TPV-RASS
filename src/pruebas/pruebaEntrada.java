package pruebas;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import principal.Sistema;

public class pruebaEntrada {
	
	public static void main(String args[])
	{
		Sistema s = Sistema.getInstancia();
		
		try {
			
			s.inicializar("CAJA1", "pruebaEntrada.xml", "ticketPruebaEntrada.xml");
			s.run();
			
			s.inicializar("CAJA1", "cancelarVenta.xml", "ticketPruebaEntrada2.xml");
			s.run();
			
			s.inicializar("CAJA1", "deshacer1Venta.xml", "ticketPruebaEntrada3.xml");
			s.run();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
