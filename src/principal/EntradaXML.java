package principal;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Entrada XML, implementa la interfaz entrada siendo el archivo de entrada .xml
 * @author RASS
 *
 */
public class EntradaXML implements Entrada {
	
	private DocumentBuilderFactory factory;
	private Document documento;
	private DocumentBuilder builder;
	private Node raiz;
	private NodeList hijos;
	private Node fecha;
	private Node cliente;
	private int pos;

	public EntradaXML(String fichero) throws ParserConfigurationException, SAXException, IOException{
		this.factory = DocumentBuilderFactory.newInstance( );
		this.documento = null;
		this.builder = factory.newDocumentBuilder();
		this.documento = builder.parse(new File(fichero));
		this.raiz = documento.getFirstChild();
		this.hijos = this.raiz.getChildNodes();
		if(this.hijos.getLength() >= 2){
		 this.fecha = sacarFecha();
		 this.cliente = sacarCliente();
		}
		this.pos = 1;
	}

	/**
	 * M�todos internos que nos permiten sacar de los hijos el cliente y la fecha
	 */
	
	private Node sacarCliente() {
		return this.hijos.item(this.hijos.getLength() - 1);
	}

	private Node sacarFecha() {
		return this.hijos.item(0);
	}

	private boolean ficheroVacio() {
		if(this.hijos.getLength() != 0){
			return false;
		}
		return true;
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
		if(!ficheroVacio()){
			if(this.pos < this.hijos.getLength() - 1){
				//Vamos a ver el nodo actual
				Node linea = this.hijos.item(pos);
				//�Ser� una l�nea normal?
				if(linea.getNamespaceURI().equals("linVenta")){
					//Si es el caso, sacamos todas las propiedades
					NamedNodeMap atributos = linea.getAttributes();
					linVenta+=atributos.getNamedItem("codProd").toString() + "&&";
					linVenta+=atributos.getNamedItem("cant").toString();
					
				} else if(linea.getNamespaceURI().equals("deshacerLinVenta")){
					//�Ser� deshacerLinVenta?
					linVenta+="deshacerLinVenta";
					
				} else if(linea.getNamespaceURI().equals("cancelarVenta")){
					linVenta+="cancelarVenta";
				}
			}
			this.pos++;
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
		// TODO Auto-generated method stub
		String resultado = "";
		if(this.cliente != null){
			NamedNodeMap atributos = this.cliente.getAttributes();
			resultado += atributos.getNamedItem("tarjetaFid").toString();
		}		
		if(resultado == "true"){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean getEmpleado() {
		// TODO Auto-generated method stub
		String resultado = "";
		if(this.cliente != null){
			NamedNodeMap atributos = this.cliente.getAttributes();
			resultado += atributos.getNamedItem("empleado").toString();
		}		
		if(resultado == "true"){
			return true;
		}
		return false;
	}

	@Override
	public String getDia() {
		// TODO Auto-generated method stub
		String resultado = "";
		if(this.fecha != null){
			NamedNodeMap atributos = this.cliente.getAttributes();
			resultado += atributos.getNamedItem("dia").toString();
		}		
		return resultado;
	}
	
	@Override
	public String getHora() {
		// TODO Auto-generated method stub
		String resultado = "";
		if(this.fecha != null){
			NamedNodeMap atributos = this.cliente.getAttributes();
			resultado += atributos.getNamedItem("hora").toString();
		}		
		return resultado;
	}
	
}