package utilidades;

import java.util.Calendar;

import principal.Sistema;

public class CrearNuevaVenta implements Comando {
	
	private Calendar fecha;
	
	public CrearNuevaVenta(Calendar fe){
		fecha = fe;
	}

	@Override
	public void ejecutar() {

		
		Sistema.getInstancia().crearNuevaVenta(fecha);
	
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}

	
}
