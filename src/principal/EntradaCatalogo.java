package principal;

import java.util.ArrayList;

/**
 * Interface para obtener la lista de productos y obtener un determinado producto a partir del código
 * @author RASS
 *
 */
public interface EntradaCatalogo {

	public abstract ArrayList<Producto> obtenerEntrada();
	
	public abstract Producto obtenerProducto(String cod);
}
