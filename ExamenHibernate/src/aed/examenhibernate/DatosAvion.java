package aed.examenhibernate;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="DatosAvion")
public class DatosAvion implements Serializable {

	@Id
	@GeneratedValue(generator = "myForeign")
	@GenericGenerator( name = "myForeign", strategy = "foreign",
	parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "avion")})
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE}) 
	@JoinColumn  (name="Matricula")
	private Avion avion;
	
	
	@Column(name="HorasVueloAvion")
	private double horasVuelvoAvion;


	public Avion getAvion() {
		return avion;
	}


	public void setAvion(Avion avion) {
		this.avion = avion;
	}


	public double getHorasVuelvoAvion() {
		return horasVuelvoAvion;
	}


	public void setHorasVuelvoAvion(double horasVuelvoAvion) {
		this.horasVuelvoAvion = horasVuelvoAvion;
	}
	
	
}
