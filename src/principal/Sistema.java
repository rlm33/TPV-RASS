package principal;

import java.io.IOException;
import java.util.ArrayList;

import utilidades.Propiedades;

public class Sistema {

	ArrayList<Venta> ventas;
	Catalogo catalogo;
	Propiedades propiedades;
	
	public Sistema()
	{
		ventas = new ArrayList<Venta>();
	}
	
	public void anyadirVenta(Venta v)
	{
		ventas.add(v);
	}
	
	/**
	 * Este método es el encargado de cargar las propiedades y la lista de catálogos
	 * @throws IOException Posibles excepciones
	 */
	public void inicializar() throws IOException{
		propiedades = Propiedades.getInstancia();
		catalogo = Catalogo.getInstancia();
	}
	
}
