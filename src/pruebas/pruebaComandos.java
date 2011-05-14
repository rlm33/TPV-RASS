package pruebas;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import principal.*;
import utilidades.*;

public class pruebaComandos {
	
	public static void main(String args[])
	{
		
		try {
			
			//1
			Sistema s = Sistema.getInstancia();
			s.inicializar("COMANDANTE", "ficheroVenta.xml", "pruebaComandos.xml");
			if (s.getLastVenta()==null)
			{
				System.out.print("1-TRUE\n");
			}
			else
			{
				System.out.print("1-FALSE\n");
			}
			//2
			Comando c1 = new CrearNuevaVenta();
			s.run(c1);
			if (s.getLastVenta()!=null)
			{
				System.out.print("2-TRUE\n");
			}
			else
			{
				System.out.print("2-FALSE\n");
			}
			//3
			String cod3 = "001633";
			Comando c2 = new AnyadirLinVenta(cod3, 2);
			s.run(c2);
			if (s.getLastVenta().getLinventas().size()>0 &&
					s.getLastVenta().getLinventas().get(0).getCantidad()==2 &&
					s.getLastVenta().getLinventas().get(0).getProducto().getCodigo().equals(cod3))
			{
				System.out.print("\n3-TRUE\n");
			}
			else
			{
				System.out.print("3-FALSE\n");
			}
			
			
			System.out.print("FINISH pruebaComandos");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
