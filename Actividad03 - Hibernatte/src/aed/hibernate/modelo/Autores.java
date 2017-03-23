package aed.hibernate.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Autores {
	private StringProperty codAutor,nombreAutor;
	public Autores(){
		codAutor = new SimpleStringProperty(this,"codAutor");
		nombreAutor= new SimpleStringProperty(this,"nombreAutor");
	}
	public final StringProperty codAutorProperty() {
		return this.codAutor;
	}
	
	public final String getCodAutor() {
		return this.codAutorProperty().get();
	}
	
	public final void setCodAutor(final String codAutor) {
		this.codAutorProperty().set(codAutor);
	}
	
	public final StringProperty nombreAutorProperty() {
		return this.nombreAutor;
	}
	
	public final String getNombreAutor() {
		return this.nombreAutorProperty().get();
	}
	
	public final void setNombreAutor(final String nombreAutor) {
		this.nombreAutorProperty().set(nombreAutor);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getCodAutor() + "->" + getNombreAutor();
	}
	
}
