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
				
				System.out.print("\n"+p.getDescripcion()+" \n "+subtotal(ventas.get(ventas.size()-1))+"\n");
				//return p.getDescripcion()+" \n "+subtotal(ventas.get(ventas.size()-1));
			}
			else
			{
				System.out.print("\nERROR: PRODUCTO NO CATALOGADO\n");
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
		int descuentoFinal = 0;
		ArrayList<Descuento> descuentos = new ArrayList<Descuento>();

		//Descuentos
		try {
			
			
			Venta venta = Sistema.getInstancia().getLastVenta();
			int dia = venta.getFecha().get(Calendar.DAY_OF_WEEK);
			int hora = venta.getFecha().get(Calendar.HOUR_OF_DAY);
					
			
			if(factoria.getEmpleado())
			{
				descuentos.add(new Empleado());
			}
			if(factoria.getTarjetaFid())
			{
				descuentos.add(new Tarjeta());
			}
			
			
			switch(dia)
			{
			case Calendar.MONDAY:
				
				String res = Propiedades.getProperty("DctoLunes.porcentaje");
				if(res!=null)
				{
					int RES = Integer.valueOf(res);
					int horaIni = Integer.valueOf(Propiedades.getProperty("DctoLunes.horaInicio"));
					int horaFin = Integer.valueOf(Propiedades.getProperty("DctoLunes.horaFin"));
					
					if(horaIni<=hora && hora<horaFin)
					{
						descuentos.add(new DiaDescuento(RES,DiaSemana.LUNES,horaIni,horaFin));
					}
					
				}
				
				break;
				
			case Calendar.TUESDAY:
				
				res = Propiedades.getProperty("DctoMartes.porcentaje");
				if(res!=null)
				{
					int RES = Integer.valueOf(res);

					int horaIni = Integer.valueOf(Propiedades.getProperty("DctoMartes.horaInicio"));
					int horaFin = Integer.valueOf(Propiedades.getProperty("DctoMartes.horaFin"));
					
					if(horaIni<=hora && hora<horaFin)
					{
						descuentos.add(new DiaDescuento(RES,DiaSemana.MARTES,horaIni,horaFin));
					}
					
				}
				
				break;
				
		case Calendar.WEDNESDAY:
	
	 res = Propiedades.getProperty("DctoMiercoles.porcentaje");
	if(res!=null)
	{
		int RES = Integer.valueOf(res);

		int horaIni = Integer.valueOf(Propiedades.getProperty("DctoMiercoles.horaInicio"));
		int horaFin = Integer.valueOf(Propiedades.getProperty("DctoMiercoles.horaFin"));
		
		if(horaIni<=hora && hora<horaFin)
		{
			descuentos.add(new DiaDescuento(RES,DiaSemana.MIERCOLES,horaIni,horaFin));
		}
		
	}
	
	break;
		case Calendar.THURSDAY:
			
			 res = Propiedades.getProperty("DctoJueves.porcentaje");
			if(res!=null)
			{
				int RES = Integer.valueOf(res);

				int horaIni = Integer.valueOf(Propiedades.getProperty("DctoJueves.horaInicio"));
				int horaFin = Integer.valueOf(Propiedades.getProperty("DctoJueves.horaFin"));
				
				if(horaIni<=hora && hora<horaFin)
				{
					descuentos.add(new DiaDescuento(RES,DiaSemana.JUEVES,horaIni,horaFin));
				}
				
			}
			
			break;
		case Calendar.FRIDAY:
			
			 res = Propiedades.getProperty("DctoViernes.porcentaje");
			if(res!=null)
			{
				int RES = Integer.valueOf(res);

				int horaIni = Integer.valueOf(Propiedades.getProperty("DctoViernes.horaInicio"));
				int horaFin = Integer.valueOf(Propiedades.getProperty("DctoMViernes.horaFin"));
				
				if(horaIni<=hora && hora<horaFin)
				{
					descuentos.add(new DiaDescuento(RES,DiaSemana.VIERNES,horaIni,horaFin));
				}
				
			}
			
			break;
		case Calendar.SATURDAY:
			
			 res = Propiedades.getProperty("DctoSabado.porcentaje");
			if(res!=null)
			{
				int RES = Integer.valueOf(res);

				int horaIni = Integer.valueOf(Propiedades.getProperty("DctoSabado.horaInicio"));
				int horaFin = Integer.valueOf(Propiedades.getProperty("DctoSabado.horaFin"));
				
				if(horaIni<=hora && hora<horaFin)
				{
					descuentos.add(new DiaDescuento(RES,DiaSemana.SABADO,horaIni,horaFin));
				}
				
			}
			
			break;
		case Calendar.SUNDAY:
			
			 res = Propiedades.getProperty("DctoDomingo.porcentaje");
			if(res!=null)
			{
				int RES = Integer.valueOf(res);

				int horaIni = Integer.valueOf(Propiedades.getProperty("DctoDomingo.horaInicio"));
				int horaFin = Integer.valueOf(Propiedades.getProperty("DctoDomingo.horaFin"));
				
				if(horaIni<=hora && hora<horaFin)
				{
					descuentos.add(new DiaDescuento(RES,DiaSemana.DOMINGO,horaIni,horaFin));
				}
				
			}
			
			break;				
			}
					
			getLastVenta().setDescuentos(descuentos);
			
		} catch(IOException e){
			System.out.println(e);
		}
				
		//Impuestos
		CalculoImpuestos calc = new RESTCalculoImpuestos();
		getLastVenta().calcularTotalImpuestos(calc);
		
		//Salida
		getLastVenta().setDescuentoAcumulado(getLastVenta().obtenerDescuento());
		//salida.generarTicket(getLastVenta());
		this.crearTicket();
	}
	
	public void crearTicket()
	{
		salida.generarTicket(getLastVenta());
	}
	
	public void run()
	{
		int ultimo = this.comandos.size(); //Por si hubiesen comandos de antes.
		this.comandos.addAll(this.factoria.getComando());
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
