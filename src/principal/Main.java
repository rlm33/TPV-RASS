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
	public static void main(String[] args) throws IOException{
		
		
		Sistema sistema = new Sistema();
		
		sistema.inicializar(new EntradaXML("fichero.xml"));
		
		CajaRegistradora c = new CajaRegistradora("CAJA1");
		
		sistema.crearNuevaVenta(c);
		
		
		
	}
	
}
