package pruebas;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class pruebaJersey {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI()+"?producto=001730");
		
		// Get XML
		System.out.println(service.accept(
				MediaType.TEXT_XML).get(String.class));


	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://artemisa.dlsi.ua.es/~ccachero/isi/impuestos").build();
	}
}
