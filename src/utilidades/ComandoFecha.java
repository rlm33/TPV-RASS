package utilidades;

import principal.Sistema;

public class ComandoFecha implements Comando{

	private String dia;
	private String hora;
		
	@Override
	public void ejecutar() {
		
		Sistema.getInstancia().setFechaVenta(dia,hora);
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}

}
