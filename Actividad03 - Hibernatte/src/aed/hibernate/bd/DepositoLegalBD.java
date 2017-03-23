package aed.hibernate.bd;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="depositoLegal")
public class DepositoLegalBD implements Serializable{

	@Id
	@GeneratedValue(generator = "myForeign")
	@GenericGenerator( name = "myForeign", strategy = "foreign",
	parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "libros")})
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE}) 
	@JoinColumn  (name="codLibro")
	private LibrosBD libro;
	
	
	@Column(name="depositoLegal", length = 20)
	private String depositoLegal;


	public LibrosBD getLibro() {
		return libro;
	}


	public void setLibro(LibrosBD libro) {
		this.libro = libro;
	}


	public String getDepositoLegal() {
		return depositoLegal;
	}


	public void setDepositoLegal(String depositoLegal) {
		this.depositoLegal = depositoLegal;
	}
	
	
	
}
