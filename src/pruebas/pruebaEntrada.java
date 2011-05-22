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
			
			s.inicializar("CAJA1", "venta1.xml", "salida1E.xml");			
			s.run();
			
			/*s.inicializar("CAJA1", "venta2.xml", "salida2D.xml");
			s.run();
			
			s.inicializar("CAJA1", "venta3.xml", "salida3D.xml");
			s.run();
			
			s.inicializar("CAJA1", "venta4.xml", "salida4D.xml");
			s.run();*/
			
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
