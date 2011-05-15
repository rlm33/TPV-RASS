package utilidades;

import principal.Sistema;

public class AnyadirLinVenta implements Comando{

	private String codigo;
	private int cantidad;
	
	public AnyadirLinVenta(String cod,int cant)
	{
		codigo=cod;
		cantidad=cant;
	}
	@Override
	public void ejecutar() {
		
		Sistema.getInstancia().anyadirLinVenta(codigo,cantidad);
		
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}
	
	public String getCodigo()
	{
		return codigo;
	}

}
