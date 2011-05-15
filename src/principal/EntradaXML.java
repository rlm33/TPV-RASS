package principal;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * Entrada XML, implementa la interfaz entrada siendo el archivo de entrada .xml
 * @author RASS
 *
 */
public class EntradaXML implements Entrada {
	
	private Document documento;
	private Element raiz;
	private NodeList hijos;
	private Node fecha;
	private Node cliente;
	private int pos;

	public EntradaXML(String fichero) throws ParserConfigurationException, SAXException, IOException{
		// Creamos el parseador  
		DOMParser parser = new DOMParser();  
		// Procesamos el fichero XML  
		parser.parse(new InputSource(new FileInputStream(fichero)));  
		// Obtenemos el objeto Document  
		documento = null;
		documento = parser.getDocument();		
		//RAIZ
		raiz = documento.getDocumentElement();
		hijos = raiz.getChildNodes();		
		if(this.hijos.getLength() >= 1){
		 this.fecha = sacarFecha();
		 this.cliente = sacarCliente();
		}
		this.pos = 4;
	}

	/**
	 * M�todos internos que nos permiten sacar de los hijos el cliente y la fecha
	 */
	
	private Node sacarCliente() {
		for(int i = 0; i < hijos.getLength(); i++){
			if(hijos.item(i).getLocalName() == "cliente"){
				return hijos.item(i);
			}
		}
		return null;
	}

	private Node sacarFecha() {
		for(int i = 0; i < hijos.getLength(); i++){
			if(hijos.item(i).getLocalName() == "fecha"){
				return hijos.item(i);
			}
		}
		return null;
	}

	/**
	 * Nos dice si hemos llegado ya al final de las lecturas o todav�a no
	 * @return Booleano a true si hemos llegado y a false en otro caso
	 */
	@Override
	public boolean isFinalFichero(){
		if(this.pos < this.hijos.getLength() - 1){
			return false;
		}
		return true;
	}

	/**
	 * getLinVenta nos devuelve la l�nea de venta que estamos leyendo
	 * @return String que contendr� la informaci�n necesaria para realizar los comandos
	 */
	@Override
	public String getLinVenta() {
		String linVenta = "";
		//Si hay hijos (documento vac�o)
		if(!isFinalFichero()){
			
			//Vamos a ver el nodo actual
			Node linea = this.hijos.item(pos-1);
			//�Ser� una l�nea normal?
			if(linea.getLocalName() == "linventa"){
				//Si es el caso, sacamos todas las propiedades
				linVenta = linea.getAttributes().item(1).getNodeValue() + "&&" + linea.getAttributes().item(0).getNodeValue();
				//NamedNodeMap atributos = linea.getAttributes();
				//linVenta+=atributos.getNamedItem("codProd").toString() + "&&";
				//linVenta+=atributos.getNamedItem("cant").toString();
				//System.out.print("NodeValue: "+linea.getAttributes().item(1).getNodeValue());
			} else if(linea.getLocalName() == "deshacerLinVenta"){
				//�Ser� deshacerLinVenta?
				linVenta+="deshacerLinVenta";
			} else if(linea.getLocalName() == "cancelarVenta"){
				linVenta+="cancelarVenta";
				}
			pos += 2;
			}		
		return linVenta;
	}

	/*
	 * GETTERS
	 * Nos devuelven otros valores que obtenemos de la Entrada, como es la tarjeta de fidelidad, si es empleado,
	 * el d�a y la hora
	 */
	
	@Override
	public boolean getTarjetaFid() {
		String resultado = "";
		if(cliente != null){
			resultado = cliente.getAttributes().item(1).getNodeValue();
			//System.out.print(cliente.getAttributes().item(1).getNodeValue());
		}		
		if(resultado.equals("true")){
			/*System.out.print("\ntarjeta true\n")*/;return true;
		}
		return false;
	}
	
	@Override
	public boolean getEmpleado() {
		// TODO Auto-generated method stub
		String resultado = "";
		if(cliente != null){
			resultado = cliente.getAttributes().item(0).getNodeValue();
		}		
		if(resultado.equals("true")){
			return true;
		}
		return false;
	}

	@Override
	public String getDia() {
		// TODO Auto-generated method stub
		String resultado = "";
		if(fecha != null){
			resultado = fecha.getAttributes().item(0).getNodeValue();
		}		
		return resultado;
	}
	
	@Override
	public String getHora() {
		// TODO Auto-generated method stub
		String resultado = "";
		if(this.fecha != null){
			resultado = fecha.getAttributes().item(1).getNodeValue();
		}		
		return resultado;
	}
	
}