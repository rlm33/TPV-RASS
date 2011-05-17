package principal;

/**
 * Interface que controla los distintos tipos de Entrada.
 * @author RASS
 *
 */
public interface Entrada {
	
	public abstract String getLinVenta();
	
	public abstract boolean getTarjetaFid();
	
	public abstract boolean getEmpleado();
	
	public abstract int getDia();
	
	public abstract String getHora();
	
	public abstract boolean isFinalFichero();
}
