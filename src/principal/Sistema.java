package principal;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Propiedades;

public class Sistema {

	private ArrayList<Venta> ventas;
	private Catalogo catalogo;
	private Propiedades propiedades;
	
	public Sistema()
	{
		ventas = new ArrayList<Venta>();
	}
	
	/**
	 * Este método es el encargado de cargar las propiedades y la lista de catálogos
	 * @throws IOException Posibles excepciones
	 */
	public void inicializar() throws IOException{
		propiedades = Propiedades.getInstancia();
		catalogo = Catalogo.getInstancia();
	}
	
	public void anyadirVenta(Venta v)
	{
		ventas.add(v);
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
	public String anyadirLinVenta(String cod, int cantidad, Venta v) throws IOException
	{
		if (catalogo.existeProducto(cod))
		{
			Producto p = catalogo.getProducto(cod);
			LinVenta lv = new LinVenta(p,cantidad);
			v.anyadirLinVenta(lv);
			
			return p.getDescripcion()+" \n "+subtotal(v);
		}
		
		return "ERROR: PRODUCTO NO CATALOGADO";
	}
	
	public void cerrarVenta(Venta v)
	{
		// TODO calcular descuentos e impuestos
	}
	
}
