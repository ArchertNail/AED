package aed.hibernate.bd;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="libros")
public class LibrosBD implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codLibro;
	
	@Column(name="nombreLibro", length = 40)
	private String nombreLibro;
	
	@Column(name="ISBN", length = 20)
	private String isbn;
	
	@Column(name="fechaIntro")
	private LocalDate fechaIntro;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<AutoresBD> autores = new HashSet<AutoresBD>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="codLibro")
	//@JoinColumn (name="codLibro")
	private Set<EjemplaresBD> ejemplares = new HashSet<EjemplaresBD>();
	 
	@OneToOne (cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn 
	private DepositoLegalBD depositoLegal;
	
	public LibrosBD() {

	}

	public int getCodLibro() {
		return codLibro;
	}

	public void setCodLibro(int codLibro) {
		this.codLibro = codLibro;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getFechaIntro() {
		return fechaIntro;
	}

	public void setFechaIntro(LocalDate fechaIntro) {
		this.fechaIntro = fechaIntro;
	}

	public Set<AutoresBD> getAutores() {
		return autores;
	}

	public void setAutores(Set<AutoresBD> autores) {
		this.autores = autores;
	}

	public Set<EjemplaresBD> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Set<EjemplaresBD> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public DepositoLegalBD getDepositoLegal() {
		return depositoLegal;
	}

	public void setDepositoLegal(DepositoLegalBD depositoLegal) {
		this.depositoLegal = depositoLegal;
	}
	
	
}
