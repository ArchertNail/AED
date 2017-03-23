package aed.procedimientos;

import aed.acceso.datos.CargarDatos;
import aed.acceso.datos.Main;
import javafx.beans.property.ListProperty;

public class PlistaEjemplaresController{

	private PListaEjemplaresView view;
	private ListProperty<PListaEjemplaresModelo> modelo;
	public PlistaEjemplaresController() {
		view = new PListaEjemplaresView();
		view.getActualizarAutorBtn().setOnAction(e->onEjecutarProcedure());
		view.getCantidadBtn().setOnAction(e->onEjecutarCantidadFunction());
	}
	
	private void onEjecutarCantidadFunction() {
		String autor = view.getNomAutor().getText();
		CargarDatos.fNumAutorLibro(autor);
	}

	private void onEjecutarProcedure() {
		
		String autor = view.getNomAutor().getText();
		CargarDatos.pListaEjemplares(autor,modelo);
	}


	public void bind(ListProperty<PListaEjemplaresModelo> modelo) {
		this.modelo = modelo ;
		
		view.getEjemplaresTable().itemsProperty().bindBidirectional(modelo);
	}
	
	
	
	public PListaEjemplaresView getRoot(){
		return view;
	}

}
