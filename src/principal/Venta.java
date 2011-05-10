package principal;

import java.util.ArrayList;
import java.util.Date;

public class Venta {

	private Date fecha;
	private ArrayList<LinVenta> linventas;
	private CajaRegistradora caja;
	
	public Venta(CajaRegistradora caja)
	{
		fecha = new Date();
		linventas = new ArrayList<LinVenta>();
		this.caja = caja;
	}
	
	/*public void anyadirLinVenta(Producto p,int cantidad)
	{
		linventas.add(new LinVenta(p,cantidad));
	}*/
	
	public void anyadirLinVenta(LinVenta lv)
	{
		linventas.add(lv);
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
	
	public CajaRegistradora getCaja()
	{
		return this.caja;
	}
	
	public void setCaja(CajaRegistradora caja)
	{
		this.caja = caja;
	}
	
	public float subtotal()
	{
		float ret = 0;
		
		for (int i=0;i<linventas.size();i++)
		{
			ret += linventas.get(i).subtotal();
		}
		
		return ret;
	}
}
