package aed.sub.scenes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import aed.acceso.datos.CargarDatos;
import aed.acceso.datos.Conexiones;
import aed.acceso.datos.Ejemplares;
import aed.acceso.datos.Libros;
import aed.acceso.datos.Main;
import javafx.stage.Stage;

public class AnadirLibroController {
	
	private AnadirLibroView view;
	private Main main;
	
	public AnadirLibroController(){
		view = new AnadirLibroView();
		view.getMasBtn().setOnAction(e->onAnadirAutorSelect());
		view.getMenosBtn().setOnAction(e->onQuitarAutorSelect());
		cargarAutores();
	}
	
	private void onQuitarAutorSelect() {
		int pos = view.getNuevoAutorList().getSelectionModel().getSelectedIndex();
		view.getAutoresList().getItems().add(view.getNuevoAutorList().getItems().get(pos));
		view.getNuevoAutorList().getItems().remove(pos);
	}

	private void onAnadirAutorSelect() {
		
		int pos =view.getAutoresList().getSelectionModel().getSelectedIndex();
		
		view.getNuevoAutorList().getItems().add(view.getAutoresList().getItems().get(pos));
		
		view.getAutoresList().getItems().remove(pos);
			
	}

	private void cargarAutores() {
		
		ArrayList<String> autores = new ArrayList<>();
		autores = CargarDatos.comboAutores();
		
		for(int i=0; i<autores.size();i++){
			
			view.getAutoresList().getItems().add(autores.get(i));
		}
			
	}
	
	public void onBotonesAction(Stage nuevoStage) {
		view.getCerrarBtn().setOnAction(e->nuevoStage.close());
		view.getAnadirBtn().setOnAction(e->onAnadirLibro());
	}

	private void onAnadirLibro() {
		
		String nomLibro = view.getLibroText().getText();
		String isbn = view.getIsbnText().getText();
		String fecha="";
		try{
			fecha = view.getFechaDate().getValue().toString();
		}catch(Exception e){
			
		}
		int tamanio = view.getNuevoAutorList().getItems().size();
		
		ArrayList<String> autoresNuevo = new ArrayList<>();
		autoresNuevo.addAll(view.getNuevoAutorList().getItems());
		
		
		CargarDatos.insertarLibros(nomLibro,isbn,fecha,tamanio,autoresNuevo);
		
		CargarDatos.libros(main);
	}

	public AnadirLibroView getRoot(){
		return view;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	
}
