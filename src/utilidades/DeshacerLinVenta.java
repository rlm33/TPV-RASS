package utilidades;

import principal.Sistema;

public class DeshacerLinVenta implements Comando{

	@Override
	public void ejecutar() {
	
		Sistema.getInstancia().deshacerLinVenta();
		
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}

}
