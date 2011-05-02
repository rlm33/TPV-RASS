package principal;


import java.io.IOException;
import java.util.ArrayList;
//import utilidades.Propiedades;

/**
 * Catalogo
 * @author RASS
 * Se trata de una clase Singleton. Esto es así ya que únicamente tenemos que cargar el catálogo una vez, al principio del programa.
 */
public class Catalogo {

	//private int numItemsMemoria;
	private ArrayList<Producto> productos;
	private static Catalogo instancia = null;
	
	private Catalogo()
	{				
	}
	
	@SuppressWarnings("unused")
	private synchronized static void createInstance() throws IOException {
        if (instancia == null) { 
            instancia = new Catalogo();
        }
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
	
	public void inicializar(){
		//numItemsMemoria = Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria"));
		Entrada ec = new ProductoCSV();		
		productos = ec.obtenerEntrada();
	}
	
	//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
	public Object clone() throws CloneNotSupportedException {
	        throw new CloneNotSupportedException(); 
	}

}
