package utilidades;

public interface CalculoImpuestos {

	//DEVUELVE UN ENTERO QUE REPRESENTA EL PORCENTAJE AL QUE HAY QUE APLICAR AL PVP DEL PRODUCTO
	public abstract int calcularImpuestos(String cod);
}
