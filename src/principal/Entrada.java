package principal;

import java.util.ArrayList;

/**
 * Se trata de la clase abstracta del constructor Builder de las Entradas. De momento las entradas ser�n de tipo XML, pero si en un futuro
 * se quisieran hacer de otro tipo HTML, hojas de texto, etc se podr�a extender el programa a�adiendo tan s�lo otra clase al sistema de clases.
 * @author RASS
 *
 */
abstract class Entrada {
	protected Pedido pedido;
	
	public void crearNuevoPedido(){
		pedido = new Pedido();		
	}
	
	public Pedido getPedido(){
		return pedido;
	}
	
	public DiaDescuento getDiaDescuento(){
		return pedido.getDiaDescuento();
	}
	
	public ArrayList<LinVenta> getLinVentas(){
		return pedido.getLinVentas();
	}
	
	public Tarjeta getTarjeta(){
		return pedido.getTarjeta();
	}
	
	public boolean getEmpleado(){
		return pedido.getEmpleado();
	}
	
	public abstract void buildDiaDescuento();
	
	public abstract void buildAddLinVentas();
	
	public abstract void buildTarjeta();
	
	public abstract void buildEmpleado();
}
