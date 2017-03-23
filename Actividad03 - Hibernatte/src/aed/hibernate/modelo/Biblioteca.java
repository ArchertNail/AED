package aed.hibernate.modelo;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Biblioteca {
	private ListProperty<Libro> libro;
	
	public Biblioteca() {
		libro = new SimpleListProperty<>(this, "libros", FXCollections.observableArrayList());
	}

	public final ListProperty<Libro> libroProperty() {
		return this.libro;
	}
	

	public final ObservableList<Libro> getLibro() {
	return this.libroProperty().get();
	}
	

	public final  void setLibro(final ObservableList<Libro> libro) {
	this.libroProperty().set(libro);
	}
	
	
	
}
