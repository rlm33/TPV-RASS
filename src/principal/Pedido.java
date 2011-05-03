/**
 * 
 */
package principal;

import java.util.ArrayList;

/**
 * Pedido es lo que obtenemos como bloque de pedidos tras la lectura del fichero.
 * Es el PRODUCTO del patrón builder
 * @author RASS
 */
public class Pedido {
	private DiaDescuento diaDescuento;
	private ArrayList<LinVenta> linVentas;
	private Tarjeta tarjeta;
	private boolean empleado;
	
	public void setDiaDescuento(DiaDescuento diaDescuento) {
		this.diaDescuento = new DiaDescuento(diaDescuento);
	}
	
	public DiaDescuento getDiaDescuento() {
		return diaDescuento;
	}
	
	public void setLinVentas(ArrayList<LinVenta> linVentas) {
		if(this.linVentas.isEmpty()){
			this.linVentas = new ArrayList<LinVenta>();
		} else {
			this.linVentas.clear();
		}
		this.linVentas.addAll(linVentas);
	}
	
	public void addLinVentas(LinVenta linVentas) {
		if(this.linVentas.isEmpty()){
			this.linVentas = new ArrayList<LinVenta>();
		}
		this.linVentas.add(linVentas);
	}
	
	public ArrayList<LinVenta> getLinVentas() {
		return linVentas;
	}
	
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = new Tarjeta();
		this.tarjeta.aplicarDescuento(tarjeta.getDescuento());
	}
	
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	
	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}
	
	public boolean getEmpleado() {
		return empleado;
	}

}
