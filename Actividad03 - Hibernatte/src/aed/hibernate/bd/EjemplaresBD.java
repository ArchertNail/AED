package aed.hibernate.bd;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ejemplares")

public class EjemplaresBD implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codEjemplar;
	
//	@Column(name="libroEj")
//	private int libroEj;
	
	@Column(name="importe", length = 3)
	private int importe;
	
	@Column(name="tipoMoneda", length = 20)
	private String tipoMoneda;
	
	@ManyToOne
	@JoinColumn(name="codLibro")
	private LibrosBD codLibro;
	
	
	public EjemplaresBD() {
		// TODO Auto-generated constructor stub
	}

	public int getCodEjemplar() {
		return codEjemplar;
	}

	public void setCodEjemplar(int codEjemplar) {
		this.codEjemplar = codEjemplar;
	}

	
	
	public LibrosBD getCodLibro() {
		return codLibro;
	}

	public void setCodLibro(LibrosBD codLibro) {
		this.codLibro = codLibro;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	
	
}
