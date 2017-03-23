package aed.acceso.datos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ejemplares {
	private StringProperty codEjemplar,codLibro, importe, tipoMoneda; 
	
	public Ejemplares(){
		codEjemplar = new SimpleStringProperty(this,"codEjemplar");
		codLibro = new SimpleStringProperty(this,"codLibro");
		importe = new SimpleStringProperty(this,"importe");
		tipoMoneda = new SimpleStringProperty(this,"tipoMoneda");
	}

	
	
	public final StringProperty codEjemplarProperty() {
		return this.codEjemplar;
	}
	

	public final String getCodEjemplar() {
		return this.codEjemplarProperty().get();
	}
	

	public final void setCodEjemplar(final String codEjemplar) {
		this.codEjemplarProperty().set(codEjemplar);
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
	

	public final StringProperty importeProperty() {
		return this.importe;
	}
	

	public final String getImporte() {
		return this.importeProperty().get();
	}
	

	public final void setImporte(final String importe) {
		this.importeProperty().set(importe);
	}
	

	public final StringProperty tipoMonedaProperty() {
		return this.tipoMoneda;
	}
	

	public final String getTipoMoneda() {
		return this.tipoMonedaProperty().get();
	}
	

	public final void setTipoMoneda(final String tipoMoneda) {
		this.tipoMonedaProperty().set(tipoMoneda);
	}
	
}
