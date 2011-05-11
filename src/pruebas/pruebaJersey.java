package pruebas;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import utilidades.RESTCalculoImpuestos;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class pruebaJersey {
	public static void main(String[] args) {
		
		RESTCalculoImpuestos e  = new RESTCalculoImpuestos();
		System.out.println("El porcentaje de 001730 es "+e.calcularImpuestos("001730") );
	
		
	}
}
