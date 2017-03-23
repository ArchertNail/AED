package aed.examenhibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="Avion")

public class Avion implements Serializable{

	@Id
	@Column(name="matricula", length=6)
	private String matricula;
	
	@Column(name="Compania", length = 30)
	private String compania;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Parte", joinColumns = {@JoinColumn(name="avion")},inverseJoinColumns={@JoinColumn(name="hangar")})
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<Hangares> ventas = new HashSet<Hangares>();
	
	@OneToOne (cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn 
	private DatosAvion datosAvion;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public Set<Hangares> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Hangares> ventas) {
		this.ventas = ventas;
	}

	public DatosAvion getDatosAvion() {
		return datosAvion;
	}

	public void setDatosAvion(DatosAvion datosAvion) {
		this.datosAvion = datosAvion;
	}
	
	
	
}
