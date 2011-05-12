package pruebas;

import utilidades.RESTCalculoImpuestos;

public class pruebaJersey {
	public static void main(String[] args) {
		
		RESTCalculoImpuestos e  = new RESTCalculoImpuestos();
		System.out.println("El porcentaje de 001730 es "+e.calcularImpuestos("001730") );
	
		
	}
}
