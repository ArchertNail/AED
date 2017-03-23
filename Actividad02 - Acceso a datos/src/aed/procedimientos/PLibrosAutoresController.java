package aed.procedimientos;

import java.util.ArrayList;

import aed.acceso.datos.CargarDatos;

public class PLibrosAutoresController {
	private PLibrosAutoresView view;
	private ArrayList<String> isbnList;
	
	public PLibrosAutoresController() {
		view = new PLibrosAutoresView();
		isbnList = new ArrayList<>();
		view.getInsertarBtn().setOnAction(e->onProcedimientoAction());
		cargarCombo();
	}

	private void cargarCombo() {
		isbnList = CargarDatos.ISBNS();
		view.getIsbn().getItems().addAll(isbnList);
		view.getIsbn().getSelectionModel().selectFirst();
	}

	private void onProcedimientoAction() {
		String isbn = view.getIsbn().getValue();
		String codAutor = view.getCodAutor().getText();
		
		CargarDatos.pLibrosAutores(isbn,codAutor);
	}

	
	public PLibrosAutoresView getView(){
		return view;
	}
}
