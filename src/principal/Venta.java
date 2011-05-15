package principal;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;

import utilidades.CalculoImpuestos;
import utilidades.Propiedades;

public class Venta {

	private Calendar fecha;
	private ArrayList<LinVenta> linventas;
	private CajaRegistradora caja;
	private int descuentoAcumulado;
	private float totalImpuestos;
	private ArrayList<Descuento> descuentos;

	public Venta(CajaRegistradora caja)
	{
		fecha = Calendar.getInstance();
		linventas = new ArrayList<LinVenta>();
		this.caja = caja;
		this.descuentoAcumulado = 0;
		this.totalImpuestos = 0;
		descuentos = new ArrayList<Descuento>();
		
	}
	
	public void setDescuentos(ArrayList<Descuento> d)
	{
		descuentos = d;
	}
	
	public int obtenerDescuento()
	{
		int RES = 0;
		
		try {
			String politica= Propiedades.getProperty("PoliticaDctos.tipo");
			
			if(politica == "NO_ACUMULABLE")
			{
				int mayor = 0;
				for(int i=0;i<descuentos.size();i++)
				{
					if(descuentos.get(i).getDescuento()>mayor)
					{
						mayor = descuentos.get(i).getDescuento();
					}
				}
				
				RES=mayor;
			}
			else
			{
				int suma = 0;
				for(int i=0;i<descuentos.size();i++)
				{
						suma = descuentos.get(i).getDescuento();
				}
				
				RES = Math.min(100,suma);
			}
		}catch(Exception e){e.printStackTrace();}
		
		return RES;
		
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
		//Le sumamos los impuestos
		ret += this.totalImpuestos;
		//Aplicamos el descuento acumulado al resultado final y aplicamos el redondeo
		ret = ret - (ret * this.descuentoAcumulado);
	    BigDecimal big = new BigDecimal(ret);
	    big = big.setScale(2, RoundingMode.HALF_UP);
		return big.floatValue();
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
	
	public void setDescuentoAcumulado(int desc){
		this.descuentoAcumulado = desc;
	}
	
	public int getDescuentoAcumulado(){
		return this.descuentoAcumulado;
	}

	public float getDescuentoLin(float pvp) {
		pvp = pvp - (pvp * this.descuentoAcumulado);
		BigDecimal big = new BigDecimal(pvp);
		big = big.setScale(2, RoundingMode.HALF_UP);
		return big.floatValue();
	}
	
}
