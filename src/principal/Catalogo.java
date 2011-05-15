package principal;


import java.io.IOException;
import java.util.ArrayList;
//import utilidades.Propiedades;

import utilidades.Propiedades;

/**
 * Catalogo
 * @author RASS
 * Se trata de una clase Singleton. Esto es así ya que únicamente tenemos que cargar el catálogo una vez, al principio del programa.
 */
public class Catalogo {

	//private int numItemsMemoria;
	private ArrayList<Producto> productos;
	private int older;
	private EntradaCatalogo ec;
	private static Catalogo instancia = null;
	
	private Catalogo()
	{
		//numItemsMemoria = Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria"));
		this.ec = new ProductoCSV();		
		productos = this.ec.obtenerEntrada();
		
		older=0;
	}
	
	/**
	 * Si en algún momento queremos obtener el catálogo de otro modo que no sea con "ProductoCSV" 
	 * habrá que instanciarlo así.
	 */
	private Catalogo(EntradaCatalogo ec)
	{
		//numItemsMemoria = Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria"));
		this.ec = ec;		
		productos = this.ec.obtenerEntrada();
		
		older=0;
		
		Catalogo.instancia = this;
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
	
	public boolean existeProducto(String cod) throws IOException
	{
		System.out.println("CODIGO " +cod);
		
		boolean ret=false;
		for(int i=0;!ret && i<productos.size();i++)
		{
			if(productos.get(i).getCodigo().equals(cod))
			{
				ret=true;
				break;
			}
		}
		if (!ret)
		{	
			Producto p = ec.obtenerProducto(cod);
			if (p!=null)
			{
				productos.set(older,p);
				older++;
				if (older>=Integer.valueOf(Propiedades.getProperty("Catalogo.numItemsMemoria")))
				{
					older=0;
				}
				ret = true;
			}
		}
		
		return ret;
	}
	
	public Producto getProducto(String cod) throws IOException
	{
		Producto p = null;
		if (existeProducto(cod))
		{
			for(int i=0;i<productos.size();i++)
			{
				if(productos.get(i).getCodigo().equals(cod))
				{
					p = productos.get(i);
					break;
				}
			}
		}
		return p;
	}
	
	//El metodo "clone" es sobreescrito por el siguiente que arroja una excepción:
	public Object clone() throws CloneNotSupportedException {
	        throw new CloneNotSupportedException(); 
	}

}
