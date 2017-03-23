package aed.hibernate.modelo;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Libro {
	private StringProperty idLibro,nombreLibro, isbn,depositoLegal;
	private ObjectProperty<LocalDate>fechaNacimiento;
	private ListProperty<Ejemplares> ejemplares;
	private ListProperty<Autores> autores;
	private ObjectProperty<DepositoLegal> deposito;
	public Libro(){
		idLibro = new SimpleStringProperty(this,"idLibro");
		nombreLibro = new SimpleStringProperty(this,"nombreLibro");
		isbn = new SimpleStringProperty(this,"isbn");
		depositoLegal = new SimpleStringProperty(this,"depositoLegal");
		fechaNacimiento = new SimpleObjectProperty<LocalDate>(this,"fechaNacimiento");
		deposito = new SimpleObjectProperty<>(this,"deposito", new DepositoLegal());
		ejemplares = new SimpleListProperty<>(this,"ejemplares",FXCollections.observableArrayList());
		autores = new SimpleListProperty<>(this,"autores", FXCollections.observableArrayList());
	}
	
	public final StringProperty idLibroProperty() {
		return this.idLibro;
	}
	

	public final String getIdLibro() {
		return this.idLibroProperty().get();
	}
	

	public final void setIdLibro(final String idLibro) {
		this.idLibroProperty().set(idLibro);
	}
	

	public final StringProperty nombreLibroProperty() {
		return this.nombreLibro;
	}
	

	public final String getNombreLibro() {
		return this.nombreLibroProperty().get();
	}
	

	public final void setNombreLibro(final String nombreLibro) {
		this.nombreLibroProperty().set(nombreLibro);
	}
	

	public final StringProperty isbnProperty() {
		return this.isbn;
	}
	

	public final String getIsbn() {
		return this.isbnProperty().get();
	}
	

	public final void setIsbn(final String isbn) {
		this.isbnProperty().set(isbn);
	}
	

	public final ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}
	

	public final LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}
	

	public final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public final ListProperty<Ejemplares> ejemplaresProperty() {
		return this.ejemplares;
	}
	

	public final ObservableList<Ejemplares> getEjemplares() {
	return this.ejemplaresProperty().get();
	}
	

	public final  void setEjemplares(final ObservableList<Ejemplares> ejemplares) {
	this.ejemplaresProperty().set(ejemplares);
	}

	public final ListProperty<Autores> autoresProperty() {
		return this.autores;
	}
	

	public final ObservableList<Autores> getAutores() {
	return this.autoresProperty().get();
	}
	

	public final  void setAutores(final ObservableList<Autores> autores) {
	this.autoresProperty().set(autores);
	}

	public final StringProperty depositoLegalProperty() {
		return this.depositoLegal;
	}
	

	public final String getDepositoLegal() {
		return this.depositoLegalProperty().get();
	}
	

	public final void setDepositoLegal(final String depositoLegal) {
		this.depositoLegalProperty().set(depositoLegal);
	}

	public final ObjectProperty<DepositoLegal> depositoProperty() {
		return this.deposito;
	}
	

	public final DepositoLegal getDeposito() {
		return this.depositoProperty().get();
	}
	

	public final void setDeposito(final DepositoLegal deposito) {
		this.depositoProperty().set(deposito);
	}
	
	
	
	
	
	
}
