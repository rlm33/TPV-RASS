package principal;

public abstract class Descuento {

	int descuento;

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	public abstract float aplicarDescuento(float desc);
	
	
}
