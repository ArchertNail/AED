package aed.acceso.datos;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Dialogo {
	
	public static Dialog<Libros> libros(ArrayList<String> autoresList){
		
		Dialog<Libros> libroDialog = new Dialog<Libros>();
		libroDialog.setTitle("Nuevo Libro");
		libroDialog.setHeaderText("Crear nuevo Libro");
		
		ButtonType anadirButtonType = new ButtonType("Añadir", ButtonData.OK_DONE);
		libroDialog.getDialogPane().getButtonTypes().addAll(anadirButtonType, ButtonType.CANCEL);
		
		TextField nomLibro = new TextField();
		ComboBox<String> autores = new ComboBox<>();
		for(int i=0; i<autoresList.size();i++){
			autores.getItems().add(autoresList.get(i));
			autores.setValue(autoresList.get(0));
		}
		TextField isbn = new TextField();
		DatePicker fecha = new DatePicker();
		Button anadir = new Button("+");
		
		GridPane libroGrid = new GridPane();
		libroGrid.setPadding(new Insets(5));
		libroGrid.setHgap(5);
		libroGrid.setVgap(5);
		libroGrid.addRow(0, new Label("autor:"),autores, anadir);
		libroGrid.addRow(1, new Label("Libro:"),nomLibro);
		libroGrid.addRow(2, new Label("ISBN:"),isbn);
		libroGrid.addRow(3, new Label("Fecha"),fecha);
		libroDialog.getDialogPane().setContent(libroGrid);
		
		return libroDialog;
	}
}
