package principal;

public class Producto {

	private int codigo; //DEBERIA SER STRING PARA CONSERVAR LOS CEROS A LA IZQUIERDA ? 
	private String descripcion;
	private float pvp;
	
	public Producto(int codigo, String descripcion, float pvp) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.pvp = pvp;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPvp() {
		return pvp;
	}
	public void setPvp(float pvp) {
		this.pvp = pvp;
	}
	
	public String toString()
	{
		return "Producto nº:"+codigo+" => "+descripcion+" | Precio="+pvp;
	}
	
	
	
}
