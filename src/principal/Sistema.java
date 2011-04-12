package principal;

import java.util.ArrayList;

public class Sistema {

	ArrayList<Venta> ventas;
	
	public Sistema()
	{
		ventas = new ArrayList<Venta>();
	}
	
	public void anyadirVenta(Venta v)
	{
		ventas.add(v);
	}
}
