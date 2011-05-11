package utilidades;

public interface Comando {

	public abstract void ejecutar(Object o);
	public abstract void deshacer();
}
