package principal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EntradaXML extends Entrada {
	
	private DocumentBuilderFactory factory;
	private Document documento;
	private DocumentBuilder builder;
	private Node raiz;
	private NodeList hijos;
	private Node fecha;
	private Node cliente;
	private int pos;

	public EntradaXML(String fichero) throws ParserConfigurationException, SAXException, IOException{
		super();
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
	
	public boolean isFinalFichero(){
		if(this.pos < this.hijos.getLength() - 1){
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<String> getLinVenta() {
		ArrayList<String> linVenta = new ArrayList<String>();
		//Si hay hijos (documento vacío)
		if(!ficheroVacio()){
			if(this.pos < this.hijos.getLength() - 1){
				//Vamos a ver el nodo actual
				Node linea = this.hijos.item(pos);
				//¿Será una línea normal?
				if(linea.getNamespaceURI().equals("linVenta")){
					//Si es el caso, sacamos todas las propiedades
					NamedNodeMap atributos = linea.getAttributes();
					linVenta.add(atributos.getNamedItem("codProd").toString());
					linVenta.add(atributos.getNamedItem("cant").toString());
					this.pos++;
					
				} else if(linea.getNamespaceURI().equals("deshacerLinVenta")){
					//¿Será deshacerLinVenta?
					linVenta.add("deshacerLinVenta");
					this.pos++;
					return linVenta;
					
				} else if(linea.getNamespaceURI().equals("cancelarVenta")){
					this.pos++;
					//¿Será cancelarVenta?
					return linVenta;
				}
			}
		} else {
			return null;
		}
		
		return linVenta;
	}
	
}