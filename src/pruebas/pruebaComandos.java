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
			
			//1-Creación con Venta null
			Sistema s = Sistema.getInstancia();
			s.inicializar("COMANDANTE", "ficheroVenta.xml", "pruebaComandos.xml");
			if (s.getLastVenta()==null)
			{
				System.out.print("\n1-TRUE\n");
			}
			else
			{
				System.out.print("\n1-FALSE\n");
			}
			//2-CrearNuevaVenta
			Comando c1 = new CrearNuevaVenta();
			s.run(c1);
			if (s.getLastVenta()!=null)
			{
				System.out.print("\n2-TRUE\n");
			}
			else
			{
				System.out.print("\n2-FALSE\n");
			}
			//3-AnyadirLinVenta Producto existe.
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
				System.out.print("\n3-FALSE\n");
			}
			//4-AnyadirLinVenta Producto NO existe.
			String codFalse = "Yonovalgocomocodigo";
			Comando c3 = new AnyadirLinVenta(codFalse,800);
			s.run(c3);
			if (s.getLastVenta().getLinventas().size()==1)
			{
				System.out.print("\n4-TRUE\n");
			}
			else
			{
				System.out.print("\n4-FALSE\n");
			}
			//Un par de linventas más.
			Comando add1 = new AnyadirLinVenta("003057",4);
			Comando add2 = new AnyadirLinVenta("001668",15);
			s.run(add1);
			s.run(add2);
			if (s.getLastVenta().getLinventas().size()==3)
			{
				System.out.print("\nADD-TRUE\n");
			}
			else
			{
				System.out.print("\nADD-FALSE\n");
			}
			//FINISH
			System.out.print("\nFINISH pruebaComandos");
			
			
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
