package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utilidades.*;

public class Sistema {

	private static Sistema sistema=null;
	
	private ArrayList<Venta> ventas;
	private ArrayList<Comando> comandos;
	private ComandoFactory factoria;
	private PuenteSalida salida;
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
	 * Este m�todo es el encargado de cargar las propiedades y la lista de cat�logos
	 * @throws IOException Posibles excepciones
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public void inicializar(String idCaja,String ent,String sal) throws IOException, ParserConfigurationException, SAXException
	{
		
		ventas = new ArrayList<Venta>();
		comandos = new ArrayList<Comando>();
		caja = new CajaRegistradora(idCaja);
		factoria =  new ComandoFactory(ent);
		salida = new PuenteSalida(sal);
		
	}
	
	/**
	 * Crea una nueva venta (asociada con su caja), la a�ade a su lista de ventas y 
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
	
	public void cancelarVenta()
	{
		ventas.remove(ventas.size()-1);
	}
	
	private float subtotal(Venta v)
	{
		return ventas.get(ventas.size()-1).subtotal();
	}
	
	/**
	 * He pensado que alguien que quiera a�adir una linea de venta querr� poner en una venta
	 * cierta cantidad de un producto que ha pasado por el lector de c�digos o ha puesto manualmente.
	 * En cualquier caso, devuelve la descripci�n de dicho producto y el subtotal que lleva la venta 
	 * hasta el momento o un mensaje de error si no reconoce el producto.
	 * 
	 * @param cod
	 * @param cantidad
	 * @param v
	 * @return
	 * @throws IOException
	 */
	public void anyadirLinVenta(String cod, int cantidad)
	{
		try {
			if (Catalogo.getInstancia().existeProducto(cod))
			{
				Producto p = Catalogo.getInstancia().getProducto(cod);
				LinVenta lv = new LinVenta(p,cantidad);
				ventas.get(ventas.size()-1).anyadirLinVenta(lv);
				
				System.out.print(p.getDescripcion()+" \n "+subtotal(ventas.get(ventas.size()-1)));
				//return p.getDescripcion()+" \n "+subtotal(ventas.get(ventas.size()-1));
			}
			else
			{
				System.out.print("ERROR: PRODUCTO NO CATALOGADO");
				//return "ERROR: PRODUCTO NO CATALOGADO";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deshacerLinVenta()
	{
		ventas.get(ventas.size()-1).deshacerLinVenta();
	}
	
	
	public Venta getLastVenta()
	{
		if (ventas.size()>0)
		{
			return ventas.get(ventas.size()-1);
		}
		else
		{
			return null;
		}
	}
	
	public void cerrarVenta(Venta v)
	{
		// TODO Descuentos
		
		//Impuestos
		CalculoImpuestos calc = new RESTCalculoImpuestos();
		getLastVenta().calcularTotalImpuestos(calc);
		//Salida
		salida.generarTicket(getLastVenta());
	}
	
	public void crearTicket()
	{
		salida.generarTicket(getLastVenta());
	}
	
	public void run()
	{
		int ultimo = comandos.size(); //Por si hubiesen comandos de antes.
		comandos.addAll(factoria.getComando());
		for (int i=ultimo; i<comandos.size(); i++)
		{
			comandos.get(i).ejecutar();
		}
	}
	
	public void run(Comando c)
	{
		c.ejecutar();
	}

	public void setFechaVenta(String dia, String hora) {
		// TODO Auto-generated method stub
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
		fecha.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora));
		this.ventas.get(this.ventas.size()-1).setFecha(fecha);
		
	}
	
}
