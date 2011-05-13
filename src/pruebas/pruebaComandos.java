package pruebas;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import principal.*;
import utilidades.*;

public class pruebaComandos {
	
	public static void main(String args[])
	{
		
		try {
			
			Sistema.getInstancia().inicializar("COMANDANTE", "ficheroVenta.xml", "pruebaComandos.xml");
			Comando c1 = new CrearNuevaVenta();
			
			
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
