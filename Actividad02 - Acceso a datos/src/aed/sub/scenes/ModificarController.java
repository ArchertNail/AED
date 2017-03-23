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

public class ModificarController {
	
	private ModificarLibroView view;
	private ArrayList<String> autoresBD;
	private Main main;
	private String idLibro;
	
	public ModificarController(){
		
		view = new ModificarLibroView();
		view.getMasBtn().setOnAction(e->onAnadirAutorSelect());
		view.getMenosBtn().setOnAction(e->onQuitarAutorSelect());
	
		autoresBD = new ArrayList<>();
		
	}
	
	public void cargarDatos(String idLibroSelect,String nombreLibroSelect, String isbnLibroSelect, LocalDate fechaLibroSelect, ArrayList<String> autores) {
		this.idLibro = idLibroSelect;
		view.getLibroText().setText(nombreLibroSelect);
		view.getIsbnText().setText(isbnLibroSelect);
		view.getFechaDate().setValue(fechaLibroSelect);
		
		for(int i=0;i<autores.size();i++){
			view.getNuevoAutorList().getItems().add(autores.get(i));
		}
	
		cargarAutores(autores);
	}

	
	private void cargarAutores(ArrayList<String> autores) {
		
		autoresBD = CargarDatos.comboAutores();
		
		boolean encontrado = false;
		for(int i=0;i<autoresBD.size();i++){
			for(int j=0;j<autores.size();j++){
				if(autores.get(j).equals(autoresBD.get(i))){
					encontrado=true;
				}
			}
			if(!encontrado){
				view.getAutoresList().getItems().addAll(autoresBD.get(i));
			}
			encontrado=false;
		}
		
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
	
	public void onBotonesAction(Stage nuevoStage) {
		view.getCerrarBtn().setOnAction(e->nuevoStage.close());
		view.getGuardarBtn().setOnAction(e->onModificarLibro());
	}

	private void onModificarLibro() {
		
		String nombreLibro = view.getLibroText().getText();
		String isbnLibro = view.getIsbnText().getText();
		String fecha ="";
		
		try{
			fecha = view.getFechaDate().getValue().toString();
		}catch(Exception e){
			
		}
		int tamanio = view.getNuevoAutorList().getItems().size();
		
		ArrayList<String> nuevosAutores = new ArrayList<>();
		nuevosAutores.addAll(view.getNuevoAutorList().getItems());
		
		CargarDatos.modificarLibro(idLibro,nombreLibro,isbnLibro,fecha,tamanio,nuevosAutores);
		
		CargarDatos.libros(main);
	}

	public ModificarLibroView getRoot(){
		return view;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	
}
