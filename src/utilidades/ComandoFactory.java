package utilidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import principal.Entrada;
import principal.EntradaXML;


public class ComandoFactory {
	private Entrada entrada;
	private ArrayList<Comando> listaComandos;
	
	public ComandoFactory(String fichero) throws ParserConfigurationException, SAXException, IOException{
		this.listaComandos = new ArrayList<Comando>();
		
		String[] campos = fichero.split("[.]");
		if(campos[campos.length-1].equals("xml")){
			this.entrada = new EntradaXML(fichero);
		} else {
			this.entrada = null;
		}
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.DAY_OF_WEEK, getDia());
		String [] hora = getHora().split(":");
		fecha.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
		fecha.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
		
		this.listaComandos.add(new CrearNuevaVenta(fecha));
	}
	
	public ArrayList<Comando> getComando(){
		if(entrada != null){
			while(!entrada.isFinalFichero()){
				String linea = entrada.getLinVenta();
				if(linea.equalsIgnoreCase("deshacerLinVenta")){
					Comando c = new DeshacerLinVenta();
					this.listaComandos.add(c);
					
				} else if(linea.equals("cancelarVenta")){
					Comando c = new CancelarVenta();
					this.listaComandos.add(c);
				} else {
					String [] parte = linea.split("&&");
					int cant = Integer.parseInt(parte[1]);
					if(cant < 0) cant = 0;
					Comando c = new AnyadirLinVenta(parte[0],cant);
					this.listaComandos.add(c);
				}
			}
		}
		return this.listaComandos;
	}
	
	public Entrada getEntrada(){
		return this.entrada;
	}
	
	/*
	 * Estos m�todos hacen falta, pero no se si esto est� bien aqu� o no
	 */
	public boolean getTarjetaFid(){
		return this.entrada.getTarjetaFid();
	}
	
	public boolean getEmpleado(){
		return this.entrada.getEmpleado();
	}
	
	public int getDia(){
		return this.entrada.getDia();
	}
	
	public String getHora(){
		return this.entrada.getHora();
		
	}
	
	
	
}
