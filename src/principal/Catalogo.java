package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import utilidades.Propiedades;

public class Catalogo {

	private java.net.URL URL;
	private int numItemsMemoria;
	private ArrayList<Producto> productos;
	private static Catalogo instancia=null;
	
	private Catalogo() throws IOException
	{
		//Properties propiedades = Propiedades.getInstancia().getPropiedades();
		URL = new java.net.URL(Propiedades.getProperty("Catalogo.URL"));
		numItemsMemoria = Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria"));
		productos = new ArrayList<Producto>();
		
		
		      InputStream in=URL.openStream ();
		      BufferedReader dis = new BufferedReader (new InputStreamReader (in));
		      String line;
		      int contador=0;
		      line = dis.readLine ();
		      while ( (line = dis.readLine ()) != null  && contador<numItemsMemoria) {
		        String[] partes =  line.split(";");
		        Producto p =  new Producto(Integer.valueOf(partes[0].split(". ")[1]),partes[1],Float.valueOf(partes[2]));
		        productos.add(p);
		        contador++;
		      }

		      in.close ();
		
	}	
	
	public static Catalogo getInstancia() throws IOException
	{
		if(instancia==null)
			instancia = new Catalogo();
		
		return instancia;
	}
	
	public ArrayList<Producto> getProductos()
	{
		return productos;
	}
}
