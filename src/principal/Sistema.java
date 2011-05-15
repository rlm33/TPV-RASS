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
	 * Este método es el encargado de cargar las propiedades y la lista de catálogos
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
	
	public void cancelarVenta()
	{
		ventas.remove(ventas.size()-1);
	}
	
	private float subtotal(Venta v)
	{
		return ventas.get(ventas.size()-1).subtotal();
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
		//Descuentos
		try {
			int dia = Sistema.getInstancia().getLastVenta().getFecha().get(Calendar.DAY_OF_WEEK);
			int hora = Sistema.getInstancia().getLastVenta().getFecha().get(Calendar.HOUR_OF_DAY);
			int descFidEmpresa = Integer.parseInt(Propiedades.getProperty("DctoTarjetaFid.porcentaje","5"));
			int descEmpleado = Integer.parseInt(Propiedades.getProperty("DctoEmpleado.porcentaje","15"));
			int diaVenta = 0;
			int porcentajeDia = 0;			
			int horaInicio = 0;			
			int horaFin = 0;
			
			//Puede ser que dependiendo del día haya un descuento u otro
			if(!Propiedades.getProperty("DctoLunes.porcentaje").isEmpty()){
				diaVenta = 2;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoLunes.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoLunes.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoLunes.horaFin"));
			} else if(!Propiedades.getProperty("DctoMartes.porcentaje").isEmpty()){
				diaVenta = 3;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoMartes.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoMartes.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoMartes.horaFin"));
			} else if(!Propiedades.getProperty("DctoMiercoles.porcentaje").isEmpty()){
				diaVenta = 4;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoMiercoles.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoMiercoles.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoMiercoles.horaFin"));
			} else if(!Propiedades.getProperty("DctoJueves.porcentaje").isEmpty()){
				diaVenta = 5;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoJueves.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoJueves.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoJueves.horaFin"));
			} else if(!Propiedades.getProperty("DctoViernes.porcentaje").isEmpty()){
				diaVenta = 6;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoViernes.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoViernes.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoViernes.horaFin"));
			} else if(!Propiedades.getProperty("DctoSabado.porcentaje").isEmpty()){
				diaVenta = 7;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoSabado.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoSabado.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoSabado.horaFin"));
			} else if(!Propiedades.getProperty("DctoDomingo.porcentaje").isEmpty()){
				diaVenta = 1;
				porcentajeDia = Integer.parseInt(Propiedades.getProperty("DctoDomingo.porcentaje"));
				horaInicio = Integer.parseInt(Propiedades.getProperty("DctoDomingo.horaInicio"));
				horaFin = Integer.parseInt(Propiedades.getProperty("DctoDomingo.horaFin"));
			}
			
			//¿Los impuestos son acumulables?
			if(Propiedades.getProperty("DescuentosAplicables.politicaAplicacion") == "NO_ACUMULABLE"){
				if(!this.factoria.getEmpleado()){
					descuentoFinal = descEmpleado;
				} else if(!this.factoria.getTarjetaFid()){
					if(descuentoFinal < descFidEmpresa){
						descuentoFinal = descFidEmpresa;
					}
				} else if(diaVenta == dia && hora >= horaInicio && hora <= horaFin){
					if(descuentoFinal < porcentajeDia){
						descuentoFinal = porcentajeDia;
					}					
				}
			} else {
				if(!this.factoria.getEmpleado()){
					descuentoFinal += descEmpleado;
				}
				if(!this.factoria.getTarjetaFid()){
					descuentoFinal += descFidEmpresa;
				}
				if(diaVenta == dia && hora >= horaInicio && hora <= horaFin){
					descuentoFinal += porcentajeDia;
				}
			}
		} catch(IOException e){
			System.out.println(e);
		}
		
		//Impuestos
		CalculoImpuestos calc = new RESTCalculoImpuestos();
		getLastVenta().calcularTotalImpuestos(calc);
		
		//Salida
		getLastVenta().setDescuentoAcumulado(descuentoFinal);
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
