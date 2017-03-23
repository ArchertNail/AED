package aed.hibernate.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DepositoLegal {

	private StringProperty codLibro, deposito;
	public DepositoLegal() {
		codLibro = new SimpleStringProperty(this,"codLibro");
		deposito = new SimpleStringProperty(this, "deposito");
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
	
	public final StringProperty depositoProperty() {
		return this.deposito;
	}
	
	public final String getDeposito() {
		return this.depositoProperty().get();
	}
	
	public final void setDeposito(final String deposito) {
		this.depositoProperty().set(deposito);
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return deposito.get();
	}
	
	
}
