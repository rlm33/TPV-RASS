package pruebas;

import java.io.IOException;

import principal.*;

public class pruebaSalida {

	public static void main(String args[])
	{
		Venta v = new Venta(new CajaRegistradora("01"));
		v.anyadirLinVenta(new LinVenta(new Producto("C1","CocaCola Lata",0.30f),4));
		v.anyadirLinVenta(new LinVenta(new Producto("C2","Mejillon Lata",1.50f),2));
		v.anyadirLinVenta(new LinVenta(new Producto("C3","Paella Lata",3.10f),1));
		v.anyadirLinVenta(new LinVenta(new Producto("C4","Sucedaneo de carne Lata",8.70f),1));
		//El total son 1.20 + 3.00 + 3.10 + 8.70 = 16.00
		try
		{
			SalidaXML sal = new SalidaXML("pruebaSalida.xml");
			sal.setVenta(v);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.print("FINISH");
	}
	
}
