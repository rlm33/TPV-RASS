package utilidades;

import java.io.ByteArrayInputStream;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class RESTCalculoImpuestos implements CalculoImpuestos{

	@Override
	public int calcularImpuestos(String cod) {
		/*
		int resultado = -1;
		
		try{
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		
		String uri = Propiedades.getProperty("CalculadoraImpuestos.URI");
		WebResource service = client.resource(uri+"?producto="+cod);
		
		// Get XML
		String xml = service.accept(MediaType.TEXT_XML).get(String.class);
		
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		 
		
    	Document doc =  documentBuilder.parse(is);
    	
    	resultado = 0;
    	NodeList nl = doc.getElementsByTagName("iva");
    	if(nl.getLength()>0)
    	{
    		resultado += Integer.valueOf(nl.item(0).getTextContent());
    	}
    	nl = doc.getElementsByTagName("especial");
    	if(nl.getLength()>0)
    	{
    		resultado += Integer.valueOf(nl.item(0).getTextContent());
    	}
		}catch(Exception e){e.printStackTrace();}
		
    	return resultado;*/
		return 7;
	}

}
