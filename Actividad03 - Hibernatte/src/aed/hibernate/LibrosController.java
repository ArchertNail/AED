package aed.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import aed.hibernate.modelo.Biblioteca;
import aed.hibernate.modelo.Libro;
import aed.hibernate.secondaryStage.AnadirLibroController;
import aed.hibernate.secondaryStage.ModificarLibroController;
import javafx.beans.property.ListProperty;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class LibrosController {
	
	private LibrosView view;
	private ListProperty<Libro> listLibros;
	private AnadirLibroController anadirLibroController;
	//private ModificarController modificarController;
	private Stage nuevoStage;
	
	public LibrosController(){
		view = new LibrosView();
	}
	
	
	public void bind(ListProperty<Libro> listLibros){
		this.listLibros = listLibros;
		view.getLibroTable().itemsProperty().bindBidirectional(listLibros);
		view.getLibroTable().getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->CargarEjemplaresAutores(newSelection));
		
		view.getAnadirLibroBtn().setOnAction(e->onAnadirLibro());
		view.getModificarLibroBtn().setOnAction(e->onModificarLibro());
		view.getBorrarLibroBtn().setOnAction(e->onBorrarLibro());
		view.getModificarLibroBtn().disableProperty().bind(view.getLibroTable().getSelectionModel().selectedItemProperty().isNull());
		view.getBorrarLibroBtn().disableProperty().bind(view.getLibroTable().getSelectionModel().selectedItemProperty().isNull());
		
	}
	private void CargarEjemplaresAutores(Libro libroSeleccionado) {
		
		if(libroSeleccionado != null){
			
			view.getEjemplarTable().itemsProperty().bindBidirectional(libroSeleccionado.ejemplaresProperty());
			view.getAutoresTable().itemsProperty().bindBidirectional(libroSeleccionado.autoresProperty());
			
			view.getEjemplarTable().itemsProperty().unbindBidirectional(libroSeleccionado.ejemplaresProperty());
			view.getAutoresTable().itemsProperty().unbindBidirectional(libroSeleccionado.autoresProperty());
		}
	}


	private void onModificarLibro() {
		
		Libro libroSelect = view.getLibroTable().getSelectionModel().getSelectedItem();
		ModificarLibroController modificarLibroController = new ModificarLibroController();
		modificarLibroController.setLibroSelect(libroSelect);
		modificarLibroController.bind(listLibros);
		
		
		Scene scene = new Scene(modificarLibroController.getRoot());
		
		nuevoStage = new Stage(StageStyle.DECORATED);
		nuevoStage.setHeight(320);
		nuevoStage.setTitle("Modificar Libro");
		nuevoStage.setScene(scene);
		
		nuevoStage.show();
		
		modificarLibroController.onBotonesAction(nuevoStage);
	
	}

	private void onBorrarLibro() {
		Libro libroSelect = view.getLibroTable().getSelectionModel().getSelectedItem();
		CargarDatos.borrarLibro(libroSelect);
		CargarDatos.libros(listLibros);
	
	}

	private void onAnadirLibro() {
		
		anadirLibroController = new AnadirLibroController();
		anadirLibroController.bind(listLibros);
		Scene scene = new Scene(anadirLibroController.getRoot());
	
		nuevoStage = new Stage(StageStyle.DECORATED);
		nuevoStage.setHeight(320);
		nuevoStage.setTitle("Añadir Libro");
		nuevoStage.setScene(scene);
		
		nuevoStage.show();
		
		anadirLibroController.onBotonesAction(nuevoStage);
	}
	
	public LibrosView getRoot(){
		return view;
	}
}
