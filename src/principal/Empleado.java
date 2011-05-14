package principal;

import java.io.IOException;

import utilidades.Propiedades;

/**
 * Esta clase nos permite inicializar a los empleados, a partir de las propiedades
 * @author RASS
 *
 */
public class Empleado extends Descuento {

	public Empleado()
	{
		try {
			descuento = Integer.valueOf(Propiedades.getProperty("DctoEmpleado.porcentaje","0"));
			
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
