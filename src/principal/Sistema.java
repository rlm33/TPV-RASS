package principal;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utilidades.Comando;
import utilidades.Propiedades;

public class Sistema {

	private static Sistema sistema=null;
	
	private ArrayList<Venta> ventas;
	private Catalogo catalogo;
	private Propiedades propiedades;
	private ArrayList<Comando> comandos;
	private Salida salida;
	private CajaRegistradora caja;
	
	
	public static Sistema getInstancia()
	{
		if(sistema==null)
		{
			sistema = new Sistema();
		}
		
		return sistema;
	}

	private Sistema(){}
	
	/**
	 * Este método es el encargado de cargar las propiedades y la lista de catálogos
	 * @throws IOException Posibles excepciones
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public void inicializar(String ent,String sal) throws IOException, ParserConfigurationException, SAXException{
		ventas = new ArrayList<Venta>();
		propiedades = Propiedades.getInstancia();
		catalogo = Catalogo.getInstancia();
		caja = new CajaRegistradora("CAJA1");
		//factoria =  new ComandoFactory(ent);
		
		
		
	}
	
	/**
	 * Crea una nueva venta (asociada con su caja), la añade a su lista de ventas y 
	 * devuelve la venta para poder usarla en "anyadirLinVenta". 
	 * 
	 * @param c
	 * @return
	 */
	public Venta crearNuevaVenta()
	{

		Venta v = new Venta(caja);
		ventas.add(v);
		
		return v;
	}
	
	private float subtotal(Venta v)
	{
		return v.subtotal();
	}
	
	/**
	 * He pensado que alguien que quiera añadir una linea de venta querrá poner en una venta
	 * cierta cantidad de un producto que ha pasado por el lector de códigos o ha puesto manualmente.
	 * En cualquier caso, devuelve la descripción de dicho producto y el subtotal que lleva la venta 
	 * hasta el momento o un mensaje de error si no reconoce el producto.
	 * 
	 * @param cod
	 * @param cantidad
	 * @param v
	 * @return
	 * @throws IOException
	 */
	/*public String anyadirLinVenta(String cod, int cantidad, Venta v) throws IOException
	{
		if (catalogo.existeProducto(cod))
		{
			Producto p = catalogo.getProducto(cod);
			LinVenta lv = new LinVenta(p,cantidad);
			v.anyadirLinVenta(lv);
			
			return p.getDescripcion()+" \n "+subtotal(v);
		}
		
		return "ERROR: PRODUCTO NO CATALOGADO";
	}*/
	
	//ESTA FUNCION NO ESTA TERMINADA
	/**
	 * No se si es correcto o no, pero supongo que la venta siempre será una, es decir, que se inicializa antes
	 * de llegar aquí y aquí tan sólo debemos ocuparnos de añadir nuevas líneas a la venta actual, que será
	 * la última venta, ¿no?
	 */
	public boolean anyadirLinVenta() throws IOException
	{
		ventas.get(ventas.size() - 1);
		String linea = entrada.getLinVenta();
		boolean res=false;
		
		
		if(linea == "cancelarVenta"){
			//Deshacer la última venta
			ventas.remove(ventas.size() - 1);
		} else if(linea == "deshacerLinVenta"){
			//Se deshace la última línea
			if(ventas.get(ventas.size() - 1).getLinventas().size()>0){
				ventas.get(ventas.size() - 1).getLinventas().remove(ventas.get(ventas.size() - 1).getLinventas().size() - 1);
			}
		} else if(linea.length()>0){
			//Una línea normal
			String[] campos = linea.split("&&");
			if(catalogo.existeProducto(campos[0])){
				Producto p = catalogo.getProducto(campos[0]);
				ventas.get(ventas.size() - 1).anyadirLinVenta(new LinVenta(p, Integer.parseInt(campos[1])));
			}
		}
		/*if(lv!=null)
			{
				Venta v = ventas.get(ventas.size()-1);
			
				if(catalogo.existeProducto(lv.getProducto().getCodigo()))
					{
					v.anyadirLinVenta(lv);
					ventas.set(ventas.size()-1,v);
					res=true;
					}
			}*/
		
		 return res;
		
	}
	
	public void cerrarVenta(Venta v)
	{
		// TODO calcular descuentos e impuestos
	}
	
	public void crearTicket(Venta v, String fichero) throws IOException{
		salida = new SalidaXML(fichero);
		
		salida.setVenta(v);
	}
	
}
