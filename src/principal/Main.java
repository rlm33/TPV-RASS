package principal;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {
	
	private static Sistema sistema;

	/**
	 * Punto de entrada al programa
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws IOException{
		sistema = new Sistema();
		sistema.inicializar();
		
		DirectorEntrada de = new DirectorEntrada(new EntradaXML("ficheroVenta.xml"));
		
	}

}
