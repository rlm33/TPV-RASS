package principal;

import java.io.IOException;

public class PuenteSalida {

	private Salida salida;
	
	public PuenteSalida(String nombre) throws IOException
	{
		String[] partes = nombre.split("[.]");
		
		salida = null;
		
		if (partes[partes.length-1].toLowerCase().equals("xml"))
		{
			salida = new SalidaXML(nombre);
		}
	}
	
	public void generarTicket(Venta v)
	{
		salida.setVenta(v);
	}
	
}
