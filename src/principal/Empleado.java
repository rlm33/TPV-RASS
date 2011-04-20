package principal;

import java.io.IOException;

import utilidades.Propiedades;

public class Empleado extends Descuento {

	public Empleado()
	{
		try {
			descuento = Integer.valueOf(Propiedades.getProperty("DctoEmpleado.porcentaje"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public float aplicarDescuento(float desc) {
		
		return desc*descuento/100;
	}

	
}
