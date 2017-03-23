package aed.hibernate.secondaryStage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import aed.hibernate.CargarDatos;
import aed.hibernate.modelo.Autores;
import aed.hibernate.modelo.Libro;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AnadirLibroController {
	
	private AnadirLibroView view;
	private ListProperty<Libro> listlibros;
	private ListProperty<Autores> listaAutoresPrincipal,listaAutoresAnadir;
	private StringProperty nombreLibro,isbnLibro, depositoLibro;
	private ObjectProperty<LocalDate> fechaLibro;
	
	
	public AnadirLibroController(){
		listaAutoresPrincipal = new SimpleListProperty<>(this,"listaAutoresPrincipal",FXCollections.observableArrayList());
		listaAutoresPrincipal.addAll(CargarDatos.listaAutores());
		listaAutoresAnadir = new SimpleListProperty<>(this,"listaAutoresAnadir",FXCollections.observableArrayList());
		
		nombreLibro = new SimpleStringProperty();
		isbnLibro = new SimpleStringProperty();
		fechaLibro = new SimpleObjectProperty<>();
		depositoLibro = new SimpleStringProperty();
		
		view = new AnadirLibroView();
		view.getMasBtn().setOnAction(e->onAnadirAutorSelect());
		view.getMenosBtn().setOnAction(e->onQuitarAutorSelect());
		
	}
	
	
	private void onAnadirAutorSelect() {
	
		listaAutoresAnadir.add(view.getAutoresList().getSelectionModel().getSelectedItem());
		listaAutoresPrincipal.remove(view.getAutoresList().getSelectionModel().getSelectedItem());
		
	}
	
	private void onQuitarAutorSelect() {
		
		listaAutoresPrincipal.add(view.getNuevoAutorList().getSelectionModel().getSelectedItem());
		listaAutoresAnadir.remove(view.getNuevoAutorList().getSelectionModel().getSelectedItem());
		
	}
	
	public void onBotonesAction(Stage nuevoStage) {
		view.getCerrarBtn().setOnAction(e->nuevoStage.close());
		view.getAnadirBtn().setOnAction(e->onAnadirLibro());
	}

	private void onAnadirLibro() {
		
		String nomLibro = nombreLibro.get();
		String isbn = isbnLibro.get();
		LocalDate fecha= fechaLibro.get();
		String deposito = depositoLibro.get();
		
		CargarDatos.insertarLibros(nomLibro,isbn,fecha,deposito, listaAutoresAnadir);
		
		CargarDatos.libros(listlibros);
	}

	public AnadirLibroView getRoot(){
		return view;
	}

	public void bind(ListProperty<Libro> listLibros) {
		this.listlibros = listLibros;
		view.getAutoresList().itemsProperty().bindBidirectional(listaAutoresPrincipal);
		view.getNuevoAutorList().itemsProperty().bindBidirectional(listaAutoresAnadir);
		view.getLibroText().textProperty().bindBidirectional(nombreLibro);
		view.getIsbnText().textProperty().bindBidirectional(isbnLibro);
		view.getFechaDate().valueProperty().bindBidirectional(fechaLibro);
		view.getDepositoText().textProperty().bindBidirectional(depositoLibro);

	}	
}
