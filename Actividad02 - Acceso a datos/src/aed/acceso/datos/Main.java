package aed.acceso.datos;

import aed.procedimientos.PListaEjemplaresModelo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main {
	private ListProperty<Libros> libros;
	private ListProperty<Ejemplares>ejemplares;
	private ListProperty<Autores> autores;
	private ListProperty<PListaEjemplaresModelo> pListaEjemplaresModelo;
	public Main(){
	
		libros = new SimpleListProperty<Libros>(this,"libros",FXCollections.observableArrayList());
		ejemplares = new SimpleListProperty<Ejemplares>(this,"ejemplares",FXCollections.observableArrayList());
		autores = new SimpleListProperty<Autores>(this,"autores",FXCollections.observableArrayList());
		pListaEjemplaresModelo = new SimpleListProperty<PListaEjemplaresModelo>(this,"procedure",FXCollections.observableArrayList());
	}
	
	public final ListProperty<Libros> librosProperty() {
		return this.libros;
	}
	public final ObservableList<Libros> getLibros() {
		return this.librosProperty().get();
	}
	public final  void setLibros(final ObservableList<Libros> libros) {
		this.librosProperty().set(libros);
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

	public final ListProperty<PListaEjemplaresModelo> pListaEjemplaresModeloProperty() {
		return this.pListaEjemplaresModelo;
	}
	public final ObservableList<PListaEjemplaresModelo> getPListaEjemplaresModelo() {
	return this.pListaEjemplaresModeloProperty().get();
	}
	public final  void setPListaEjemplaresModelo(final ObservableList<PListaEjemplaresModelo> pListaEjemplaresModelo) {
	this.pListaEjemplaresModeloProperty().set(pListaEjemplaresModelo);
	}
	
	
	

	
}
