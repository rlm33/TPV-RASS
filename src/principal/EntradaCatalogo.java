package principal;

import java.util.ArrayList;

public interface EntradaCatalogo {

	public abstract ArrayList<Producto> obtenerEntrada();
	
	public abstract Producto obtenerProducto(String cod);
}
