package principal;

import java.io.IOException;

import utilidades.Propiedades;

public class Tarjeta extends Descuento{

	public Tarjeta()
	{
		try {
			descuento = Integer.valueOf(Propiedades.getProperty("DctoTarjetaFid.porcentaje","0"));
			
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
