package utilidades;

import principal.CajaRegistradora;
import principal.Sistema;

public class CrearNuevaVenta implements Comando {

	@Override
	public void ejecutar() {

		
		Sistema.getInstancia().crearNuevaVenta();
	
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}

	
}
