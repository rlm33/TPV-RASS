package principal;


import java.io.IOException;
import java.util.ArrayList;
//import utilidades.Propiedades;

public class Catalogo {

	//private int numItemsMemoria;
	private ArrayList<Producto> productos;
	private static Catalogo instancia=null;
	
	private Catalogo() throws IOException
	{
		//numItemsMemoria = Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria"));

		EntradaCatalogo ec = new ProductoCSV();
		
		productos = ec.obtenerEntrada();
		
		
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
	
	public boolean existeProducto(int cod)
	{
		boolean ret=false;
		for(int i=0;!ret && i<productos.size();i++)
			if(productos.get(i).getCodigo()==cod) ret=true;
		
		return ret;
	}
	
	public Producto getProducto(int cod)
	{
		Producto p = null;
		boolean ret=false;
		for(int i=0;!ret && i<productos.size();i++)
			if(productos.get(i).getCodigo()==cod){
				ret=true;
				p = productos.get(i);
			}
		return p;
	}
}
