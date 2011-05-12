package principal;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	/**
	 * Punto de entrada al programa
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void Main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		
		Sistema sistema = new Sistema();
		
		sistema.inicializar("ficheroVenta.xml");
		
		CajaRegistradora c = new CajaRegistradora("CAJA1");
		
		sistema.crearNuevaVenta(c);
		
		sistema.anyadirLinVenta();	
	}

}
