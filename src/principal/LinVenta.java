package principal;

public class LinVenta {

	int cantidad;
	Producto producto;
	
	
	public LinVenta(Producto producto,int cantidad) {
		this.cantidad = cantidad;
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
}
