package utilidades;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import principal.Entrada;
import principal.EntradaXML;


public class ComandoFactory {
	private Entrada entrada;
	private ArrayList<Comando> listaComandos;
	
	public ComandoFactory(String fichero) throws ParserConfigurationException, SAXException, IOException{
		this.listaComandos = new ArrayList<Comando>();
		String[] campos = fichero.split(".");
		
		if(campos[campos.length-1].equals("xml")){
			this.entrada = new EntradaXML(fichero);
		} else {
			this.entrada = null;
		}
	}
	
	public ArrayList<Comando> getComando(){
		
		if(this.entrada != null){
			while(!this.entrada.isFinalFichero()){
				String linea = this.entrada.getLinVenta();
				if(linea.equals(ComandosList.deshacerLinVenta)){
					Comando c = new DeshacerLinVenta();
					this.listaComandos.add(c);
					
				} else if(linea.equals(ComandosList.cancelarVenta)){
					Comando c = new CancelarVenta();
					this.listaComandos.add(c);
				} else {
					String [] parte = linea.split("&&");
					Comando c = new AnyadirLinVenta(parte[0],Integer.parseInt(parte[1]));
					this.listaComandos.add(c);
				}
			}
		}
		return this.listaComandos;
	}
	
	/*
	 * Estos m�todos hacen falta, pero no se si esto est� bien aqu� o no
	 */
	public String getTarjetaFid(){
		return this.entrada.getTarjetaFid();
	}
	
	public String getEmpleado(){
		return this.entrada.getEmpleado();
	}
	
	public String getDia(){
		return this.entrada.getDia();
	}
	
	public String getHora(){
		return this.entrada.getHora();
		
	}
	
	
	
}
