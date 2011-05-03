package principal;

import java.util.ArrayList;

/**
 * Se trata de la clase abstracta del constructor Builder de las Entradas. De momento las entradas serán de tipo XML, pero si en un futuro
 * se quisieran hacer de otro tipo HTML, hojas de texto, etc se podría extender el programa añadiendo tan sólo otra clase al sistema de clases.
 * @author HanHelsing
 *
 */
abstract class Entrada {
	protected Pedido pedido;
	
	public void crearNuevoPedido(){
		pedido = new Pedido();
		buildDiaDescuento();
		buildSetLinVentas();
		buildTarjeta();
		buildEmpleado();		
	}
	
	public Pedido getPedido(){
		return pedido;
	}
	
	public abstract void buildDiaDescuento();
	
	public abstract DiaDescuento getDiaDescuento();
	
	public abstract void buildSetLinVentas();
	
	public abstract void buildAddLinVentas();
	
	public abstract ArrayList<LinVenta> getLinVentas();
	
	public abstract void buildTarjeta();
	
	public abstract Tarjeta getTarjeta();
	
	public abstract void buildEmpleado();
	
	public abstract boolean getEmpleado();

}
