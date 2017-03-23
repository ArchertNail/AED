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
import aed.hibernate.modelo.DepositoLegal;
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

public class ModificarLibroController {
	
	private ModificarLibroView view;
	private ListProperty<Libro> listlibros;
	private ListProperty<Autores> listaAutoresPrincipal,listaAutoresAnadir;
	private StringProperty nombreLibro,isbnLibro;
	private ObjectProperty<LocalDate> fechaLibro;
	private DepositoLegal deposito;
	private Libro libroSelect;
	
	
	public ModificarLibroController(){
		listaAutoresPrincipal = new SimpleListProperty<>(this,"listaAutoresPrincipal",FXCollections.observableArrayList());
		listaAutoresAnadir = new SimpleListProperty<>(this,"listaAutoresAnadir",FXCollections.observableArrayList());
		deposito = new DepositoLegal();
		
		nombreLibro = new SimpleStringProperty();
		isbnLibro = new SimpleStringProperty();
		fechaLibro = new SimpleObjectProperty<>();
		
		
		view = new ModificarLibroView();
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
		view.getGuardarBtn().setOnAction(e->onGuardarLibro());
	}

	private void onGuardarLibro() {
		Libro libroNewVersion = new Libro();
		libroNewVersion.setIdLibro(libroSelect.getIdLibro());
		libroNewVersion.setNombreLibro(nombreLibro.get());
		libroNewVersion.setIsbn(isbnLibro.get());
		libroNewVersion.setFechaNacimiento(fechaLibro.get());
		DepositoLegal dep = new DepositoLegal();
		dep.setCodLibro(libroSelect.getIdLibro());
		dep.setDeposito(deposito.getDeposito());
		libroNewVersion.setDeposito(dep);
		
		libroNewVersion.setAutores(listaAutoresAnadir);
		
		CargarDatos.actualizarLibro(libroNewVersion);
		
		CargarDatos.libros(listlibros);
	}

	public ModificarLibroView getRoot(){
		return view;
	}

	public void setLibroSelect(Libro libroSelect) {
		this.libroSelect = libroSelect;
		ArrayList<Autores> autoresNoSelect = new ArrayList<>();
		
		ArrayList<Autores> listaAutores = new ArrayList<>(CargarDatos.listaAutores());
		
		outerloop:
		for(int i=0; i<listaAutores.size();i++){
			
			for(int j=0;j<libroSelect.getAutores().size(); j++){
				if(libroSelect.getAutores().get(j).getCodAutor().equals(listaAutores.get(i).getCodAutor())){
					continue outerloop;
				}
			}
			
			Autores autor = new Autores();
			autor.setCodAutor(listaAutores.get(i).getCodAutor());
			autor.setNombreAutor(listaAutores.get(i).getNombreAutor());
			autoresNoSelect.add(autor);
			
		
		}
		
		listaAutoresPrincipal.addAll(autoresNoSelect);
		
	}	
	
	public void bind(ListProperty<Libro> listLibros) {
		this.listlibros = listLibros;
		view.getAutoresList().itemsProperty().bindBidirectional(listaAutoresPrincipal);
		view.getNuevoAutorList().itemsProperty().bindBidirectional(listaAutoresAnadir);
		view.getLibroText().textProperty().bindBidirectional(nombreLibro);
		view.getIsbnText().textProperty().bindBidirectional(isbnLibro);
		view.getFechaDate().valueProperty().bindBidirectional(fechaLibro);
		view.getDepositoText().textProperty().bindBidirectional(deposito.depositoProperty());

		nombreLibro.bindBidirectional(libroSelect.nombreLibroProperty());
		isbnLibro.bindBidirectional(libroSelect.isbnProperty());
		fechaLibro.bindBidirectional(libroSelect.fechaNacimientoProperty());
		deposito.depositoProperty().bindBidirectional(libroSelect.getDeposito().depositoProperty());
		listaAutoresAnadir.bind(libroSelect.autoresProperty());
		
	}

	
}
