package aed.examenhibernate;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Parte")
public class Parte implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="Avion")
	private Avion avion;
	
	@Id
	@ManyToOne
	@JoinColumn(name="Hangar")
	private Hangares hangar;
	
	@Id
	@Column(name="fecha")
	private LocalDate Fecha;

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Hangares getHangar() {
		return hangar;
	}

	public void setHangar(Hangares hangar) {
		this.hangar = hangar;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}
	
	
}
