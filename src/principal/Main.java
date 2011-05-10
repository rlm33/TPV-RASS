package principal;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {
	
	private static Sistema sistema;

	/**
	 * Punto de entrada al programa
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static void main(String[] args) throws IOException{
		sistema = new Sistema();
		sistema.inicializar();
		
		//DirectorEntrada de = new DirectorEntrada(new EntradaXML("ficheroVenta.xml"));
		
		Catalogo c = Catalogo.getInstancia();
		/*for (int i=0;i<c.getProductos().size();i++)
		{
			System.out.print(c.getProductos().get(i).getCodigo()+"\n");
		}*/
		if (c.existeProducto("003057")) //Producto existente pero no en memoria.
		{
			System.out.print("TRUE\n");
		}
		else
		{
			System.out.print("FALSE\n");
		}
		
		System.out.print(c.getProductos().get(0).getCodigo()+"\n"); //Debería aparecer el nuevo aquí sustituyendo a 001633
		System.out.print(c.getProductos().get(1).getCodigo()+"\n");
		System.out.print(c.getProductos().get(2).getCodigo()+"\n");
		
		if (c.existeProducto("001633")) //Producto recien eliminado de memoria.
		{
			System.out.print("TRUE\n");
		}
		else
		{
			System.out.print("FALSE\n");
		}
		
		System.out.print(c.getProductos().get(0).getCodigo()+"\n");
		System.out.print(c.getProductos().get(1).getCodigo()+"\n"); //Debería aparecer aquí.
		System.out.print(c.getProductos().get(2).getCodigo()+"\n");
		
		System.out.print(c.getProducto("001668").getDescripcion()+" PVP: "+c.getProducto("001668").getPvp()+"\n");
		
		if (!c.existeProducto("RASS-TPV")) //Producto no existente
		{
			System.out.print("TRUE\n");
		}
		else
		{
			System.out.print("FALSE\n");
		}
		
		System.out.print(c.getProductos().get(0).getCodigo()+"\n");
		System.out.print(c.getProductos().get(1).getCodigo()+"\n");
		System.out.print(c.getProductos().get(2).getCodigo()+"\n"); //No debería irse el 001668.
		
	}
	
}
