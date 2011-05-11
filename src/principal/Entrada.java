package principal;

import java.util.ArrayList;

/**
 * Se trata de la clase abstracta del constructor Builder de las Entradas. De momento las entradas ser�n de tipo XML, pero si en un futuro
 * se quisieran hacer de otro tipo HTML, hojas de texto, etc se podr�a extender el programa a�adiendo tan s�lo otra clase al sistema de clases.
 * @author RASS
 *
 */
abstract class Entrada {
	public Entrada(){
		
	}
	
	public abstract ArrayList<String> getLinVenta();
}
