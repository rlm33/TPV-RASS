package principal;

import java.io.IOException;

public class Main {
	
	private static Sistema sistema;

	/**
	 * Punto de entrada al programa
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		sistema = new Sistema();
		sistema.inicializar();
		
	}

}
