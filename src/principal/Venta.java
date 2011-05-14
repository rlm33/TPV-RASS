package principal;

import java.util.ArrayList;
import java.util.Calendar;

import utilidades.CalculoImpuestos;

public class Venta {

	private Calendar fecha;
	private ArrayList<LinVenta> linventas;
	private CajaRegistradora caja;
	private float totalImpuestos;

	public Venta(CajaRegistradora caja)
	{
		fecha = Calendar.getInstance();
		linventas = new ArrayList<LinVenta>();
		this.caja = caja;
	}

	public void anyadirLinVenta(Producto p,int cantidad)
	{
		linventas.add(new LinVenta(p,cantidad));
	}

	public void anyadirLinVenta(LinVenta lv)
	{
		linventas.add(lv);
	}

	public void deshacerLinVenta()
	{
		if (linventas.size()>0)
		{
			linventas.remove(linventas.size()-1);
		}
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
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
	
	public float calcularTotalImpuestos(CalculoImpuestos calc)
	{
		float impuestos = 0.0f;
		
		for (int i=0; i<linventas.size(); i++)
		{
			impuestos += linventas.get(i).getCantidad()*
				calc.calcularImpuestos(linventas.get(i).getProducto().getCodigo())*
				linventas.get(i).getProducto().getPvp();
		}
		
		this.totalImpuestos = impuestos;
		return impuestos;
	}
	
	public float getImpuestos()
	{
		return this.totalImpuestos;
	}
	
}
