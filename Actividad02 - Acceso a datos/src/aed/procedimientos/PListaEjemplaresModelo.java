package aed.procedimientos;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PListaEjemplaresModelo {

	private StringProperty codLibro,nombreLibro,isbn;
	private ObjectProperty<LocalDate> fecha;
	
	public PListaEjemplaresModelo(){
		codLibro = new SimpleStringProperty();
		nombreLibro = new SimpleStringProperty();
		isbn = new SimpleStringProperty();
		fecha = new SimpleObjectProperty<LocalDate>(this,"fechaNacimiento");	
	}

	public final StringProperty codLibroProperty() {
		return this.codLibro;
	}
	

	public final String getCodLibro() {
		return this.codLibroProperty().get();
	}
	

	public final void setCodLibro(final String codLibro) {
		this.codLibroProperty().set(codLibro);
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
	

	public final ObjectProperty<LocalDate> fechaProperty() {
		return this.fecha;
	}
	

	public final LocalDate getFecha() {
		return this.fechaProperty().get();
	}
	

	public final void setFecha(final LocalDate fecha) {
		this.fechaProperty().set(fecha);
	}
	
	
	
}
