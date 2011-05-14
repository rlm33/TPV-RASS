package principal;

import java.util.Properties;

import utilidades.Propiedades;



/**
 * Esta clase implementa un descuento segun la ubicacion temporal que contiene un dia de la semana y un horario.
 * @author RASS
 *
 * Cada Descuento de un dia diferente contarlo como otro objeto de tipo DiaDescuento.
 * De este modo si queremos que haya descuento los lunes de 10 a 12 y los martes de 9 a 11 simplemente
 * meteremos dos DiaDescuento en el array.
 */
public class DiaDescuento extends Descuento {

	
	private DiaSemana dia;
	private int horaInicio;
	private int horaFin;
	
	/*
	 * CONSTRUCTORES
	 */
	public DiaDescuento(){
		this.dia = DiaSemana.NULO;
		this.horaInicio = -1;
		this.horaFin = -1;
	}
	
	public DiaDescuento(DiaSemana dia, int horaInicio, int horaFin) {
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public DiaDescuento(DiaDescuento diaDescuento) {
		this.dia =  diaDescuento.dia;
		this.horaInicio = diaDescuento.getHoraInicio();
		this.horaFin = diaDescuento.getHoraFin();
	}

	/*
	 * GETTERS AND SETTERS
	 */

	public void setDia(DiaSemana dia)
	{
		this.dia = dia;
	}
	
	public DiaSemana getDia() {
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
	
	@Override
	public float aplicarDescuento(float desc)
	{
		Properties p = Propiedades.getInstancia().getPropiedades();
		p.getProperty("dtoMiercoles");
		// TODO Mirar si estamos en dia y hora de descuento
		if(this.dia.equals())
		return desc;
	}
	
}
