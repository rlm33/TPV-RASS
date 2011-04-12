package principal;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			ArrayList<Producto> op = Catalogo.getInstancia().getProductos();
			for(int i=0;i<op.size();i++)
			{
				
				System.out.println(op.get(i));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
