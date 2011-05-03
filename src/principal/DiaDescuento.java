package principal;

import java.util.ArrayList;

/**
 * Esta clase nos marca los días en los cuáles se les puede aplicar descuento a una determinada persona.
 * @author RASS
 *
 */
public class DiaDescuento {

	//Pueden ser varios días, por eso un ArrayList
	private ArrayList<DiaSemana> dia;
	private int horaInicio;
	private int horaFin;
	
	/*
	 * CONSTRUCTORES
	 */
	public DiaDescuento(ArrayList<DiaSemana> dia, int horaInicio, int horaFin) {
		if(this.dia.isEmpty()){
			this.dia = new ArrayList<DiaSemana>();
		} else {
			this.dia.clear();
		}
		this.dia.addAll(dia);
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public DiaDescuento(DiaDescuento diaDescuento) {
		if(this.dia.isEmpty()){
			this.dia = new ArrayList<DiaSemana>();
		} else {
			this.dia.clear();
		}
		this.dia.addAll(diaDescuento.getDias());
		this.horaInicio = diaDescuento.getHoraInicio();
		this.horaFin = diaDescuento.getHoraFin();
	}

	/*
	 * GETTERS AND SETTERS
	 */
	
	//Añade un único día
	public void addDia(DiaSemana dia) {
		this.dia.add(dia);
	}
	
	//Añade todos los días a la vez
	public void setDias(ArrayList<DiaSemana> dias){
		if(this.dia.isEmpty()){
			this.dia = new ArrayList<DiaSemana>();
		} else {
			this.dia.clear();
		}
		this.dia.addAll(dias);
	}

	public ArrayList<DiaSemana> getDias() {
		return dia;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public int getHoraFin() {
		return horaFin;
	}
	
	
}
