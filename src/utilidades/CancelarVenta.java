package utilidades;

import principal.Sistema;

public class CancelarVenta implements Comando {

	@Override
	public void ejecutar() {
		
		Sistema.getInstancia().cancelarVenta();
		
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}

}
