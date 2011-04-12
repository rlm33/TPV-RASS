package principal;

import java.util.ArrayList;
import java.util.Date;

public class Venta {

	Date fecha;
	ArrayList<LinVenta> linventas;
	
	public Venta()
	{
		fecha = new Date();
		linventas = new ArrayList<LinVenta>();
	}
	
	public void anyadirLinVenta(Producto p,int cantidad)
	{
		linventas.add(new LinVenta(p,cantidad));
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<LinVenta> getLinventas() {
		return linventas;
	}

	public void setLinventas(ArrayList<LinVenta> linventas) {
		this.linventas = linventas;
	}
	
	
}
