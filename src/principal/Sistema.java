package principal;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utilidades.Comando;
import utilidades.CrearNuevaVenta;
import utilidades.Propiedades;

public class Sistema {

	private ArrayList<Venta> ventas;
	private Catalogo catalogo;
	private Propiedades propiedades;
	private Entrada entrada;
	private ArrayList<Comando> comandos;
	private Salida salida;
	
	public Sistema()
	{
		ventas = null;
		catalogo = null;
		propiedades = null;
		entrada = null;
		salida = null;
	}
	
	/**
	 * Este método es el encargado de cargar las propiedades y la lista de catálogos
	 * @throws IOException Posibles excepciones
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public void inicializar(String s/*,Salida s*/) throws IOException, ParserConfigurationException, SAXException{
		ventas = new ArrayList<Venta>();
		propiedades = Propiedades.getInstancia();
		catalogo = Catalogo.getInstancia();
		entrada = new EntradaXML(s);
		
	}
	
	/**
	 * Crea una nueva venta (asociada con su caja), la añade a su lista de ventas y 
	 * devuelve la venta para poder usarla en "anyadirLinVenta". 
	 * 
	 * @param c
	 * @return
	 */
	public Venta crearNuevaVenta(CajaRegistradora c)
	{
		
		Comando com = new CrearNuevaVenta();
		com.ejecutar(c);
		comandos.add(com);
		Venta v = new Venta(c);
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
	public boolean anyadirLinVenta() throws IOException
	{
		Venta v = null;
		ArrayList<String> linea = entrada.getLinVenta();
		LinVenta lv = null;
		boolean res=false;
		
		System.out.println(linea.get(0));
		
		if(linea.isEmpty()){
			//Se cancela la venta entera
			ventas.clear();
		} else if(linea.size() == 1){
			if(ventas.size() >= 1){
				ventas.remove(ventas.size() - 1);
			}
		} else if(linea.size() == 2){
			Producto p = catalogo.getProducto(linea.get(0));
			lv = new LinVenta(p, Integer.parseInt(linea.get(1)));
			v.anyadirLinVenta(lv);
			ventas.add(v);
			
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
