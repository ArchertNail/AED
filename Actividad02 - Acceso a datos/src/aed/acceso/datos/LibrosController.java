package aed.acceso.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import aed.sub.scenes.AnadirLibroController;
import aed.sub.scenes.ModificarController;
import aed.sub.scenes.ModificarLibroView;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class LibrosController {
	private LibrosView view;
	private Main main;
	private AnadirLibroController anadirLibroController;
	private ModificarController modificarController;
	private Stage nuevoStage;
	
	public LibrosController(){
		view = new LibrosView();
	}
	
	public void bind(Main main){
		this.main = main;
		view.getLibroTable().itemsProperty().bindBidirectional(main.librosProperty());
		view.getEjemplarTable().itemsProperty().bindBidirectional(main.ejemplaresProperty());
		view.getAutoresTable().itemsProperty().bindBidirectional(main.autoresProperty());
		view.getLibroTable().getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->CargarDatos.ejemplaresyautores(main,newSelection));
		view.getAnadirLibroBtn().setOnAction(e->onAnadirLibro());
		view.getBorrarLibroBtn().setOnAction(e->onBorrarLibro());
		view.getModificarLibroBtn().setOnAction(e->onModificarLibro());
		view.getCantidadBtn().setOnAction(e->onCantidadEjemplares());
	}

	private void onCantidadEjemplares() {
		String isbn =view.getLibroTable().getSelectionModel().getSelectedItem().getIsbn();
		CargarDatos.pCantidadEjemplares(isbn);
	}

	private void onModificarLibro() {
		
		ArrayList<String> autores = new ArrayList<>();
		for(int i=0;i<main.autoresProperty().size();i++){
			autores.add(main.getAutores().get(i).getCodAutor() +"->"+main.getAutores().get(i).getNombreAutor());
		}
		String idLibroSelect ="";
		String nombreLibroSelect = "";
		String isbnLibroSelect="";
		LocalDate fechaLibroSelect = LocalDate.now();
		
		try{
			
		idLibroSelect = view.getLibroTable().getSelectionModel().getSelectedItem().getIdLibro();
		nombreLibroSelect = view.getLibroTable().getSelectionModel().getSelectedItem().getNombreLibro();
		isbnLibroSelect = view.getLibroTable().getSelectionModel().getSelectedItem().getIsbn();
		fechaLibroSelect = view.getLibroTable().getSelectionModel().getSelectedItem().getFechaNacimiento();
		
		modificarController = new ModificarController();
		modificarController.setMain(main);
		modificarController.cargarDatos(idLibroSelect,nombreLibroSelect,isbnLibroSelect,fechaLibroSelect,autores);
		
		
		Scene scene = new Scene(modificarController.getRoot());
	
		nuevoStage = new Stage(StageStyle.DECORATED);
		nuevoStage.setHeight(320);
		nuevoStage.setTitle("Modificar Libro");
		nuevoStage.setScene(scene);
		
		nuevoStage.show();
		
		modificarController.onBotonesAction(nuevoStage);
		
		
		}catch(Exception e){
			System.out.println("SELECCIONA UN LIBRO");
		}
	}

	private void onBorrarLibro() {
		
		String codLibro = view.getLibroTable().getSelectionModel().getSelectedItem().getIdLibro();
		CargarDatos.borraLibro(codLibro, main);
	}

	private void onAnadirLibro() {
		
		anadirLibroController = new AnadirLibroController();
		anadirLibroController.setMain(main);
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
