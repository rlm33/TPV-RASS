package utilidades;

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
