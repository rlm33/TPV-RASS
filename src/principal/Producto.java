package principal;

public class Producto {

	private String codigo; //DEBERIA SER STRING PARA CONSERVAR LOS CEROS A LA IZQUIERDA ? 
						//MAS BIEN DEBE SER STRING PORQUE ALGUNOS PRODUCTOS TIENEN LETRAS EN LA REFERENCIA!!
	private String descripcion;
	private float pvp;
	
	public Producto(String codigo, String descripcion, float pvp) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.pvp = pvp;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
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
		return "Producto nº: "+codigo+" => "+descripcion+" | Precio="+pvp;
	}
	
	
	
}
