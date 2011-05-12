package principal;

import java.util.ArrayList;

/**
 * Se trata de la clase abstracta del constructor de las Entradas.
 * @author RASS
 *
 */
abstract class Entrada {
	public Entrada(){
		
	}
	
	public abstract String getLinVenta();
	
	public abstract String getTarjetaFid();
	
	public abstract String getEmpleado();
	
	public abstract String getDia();
	
	public abstract String getHora();
}
