package principal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import utilidades.Propiedades;

public class ProductoCSV implements EntradaCatalogo {

	@Override
	public ArrayList<Producto> obtenerEntrada() {

		ArrayList<Producto> productos = new ArrayList<Producto>();
		try{
		URL URL = new java.net.URL(Propiedades.getProperty("Catalogo.URL"));
		int numItemsMemoria = Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria"));
		 InputStream in=URL.openStream ();
	      BufferedReader dis = new BufferedReader (new InputStreamReader (in));
	      String line;
	      int contador=0;
	      line = dis.readLine ();
	      while ( (line = dis.readLine ()) != null  && contador<numItemsMemoria) {
	        String[] partes =  line.split(";");
	        String[] precio = partes[2].split("[.]");
	        if(precio.length <= 2){
	        	Producto p =  new Producto(partes[0].split(" ")[1],partes[1],Float.valueOf(partes[2]));
		        productos.add(p);	        	
	        }
	        contador++;
	      }
	      in.close();
	      
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return productos;
	}
	
	@Override
	public Producto obtenerProducto(String cod) //Devuelve null si no existe en el catálogo online y el objeto producto en caso contrario.
	{
		Producto p = null;
		try{
			URL URL = new java.net.URL(Propiedades.getProperty("Catalogo.URL"));
			 InputStream in=URL.openStream ();
		      BufferedReader dis = new BufferedReader (new InputStreamReader (in));
		      String line;
		      line = dis.readLine ();
		      while ( (line = dis.readLine ()) != null ) {
		        String[] partes =  line.split(";");
		        if (partes[0].split(" ")[1].equals(cod))
		        {	
		        	p =  new Producto(partes[0].split(" ")[1],partes[1],Float.valueOf(partes[2]));
		        	break;
		        }
		      }
		      in.close();
		      
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		return p;
	}

}
