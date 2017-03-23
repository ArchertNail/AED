package aed.acceso.datos;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Libros {
	private StringProperty idLibro,nombreLibro, isbn;
	private ObjectProperty<LocalDate>fechaNacimiento;
	
	public Libros(){
		idLibro = new SimpleStringProperty(this,"idLibro");
		nombreLibro = new SimpleStringProperty(this,"nombreLibro");
		isbn = new SimpleStringProperty(this,"isbn");
		fechaNacimiento = new SimpleObjectProperty<LocalDate>(this,"fechaNacimiento");
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
	
	
}
