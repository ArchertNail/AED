package aed.AccesoFicheros;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MostrarFicheroView extends VBox {
	private TextField rutaText;
	private Button mostrarFicheros;
	private TextField nomArchivoText;
	private TableView<String> tablaFicheros;
	private Label listarFicheros;
	
	public MostrarFicheroView() {
		
		listarFicheros = new Label("No hay archivos");
		rutaText = new TextField();
		rutaText.setMinWidth(450);
		nomArchivoText = new TextField();
		mostrarFicheros = new Button("MostrarFicheros");
		
		
		HBox fila1 = new HBox(5, rutaText, mostrarFicheros);
		fila1.setAlignment(Pos.BASELINE_LEFT);
		
		setPadding(new Insets(5));
		setSpacing(5);
		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(fila1,listarFicheros);
	}

	public Label getListarFicheros() {
		return listarFicheros;
	}

	public void setListarFicheros(Label listarFicheros) {
		this.listarFicheros = listarFicheros;
	}

	public TextField getRutaText() {
		return rutaText;
	}

	public void setRutaText(TextField rutaText) {
		this.rutaText = rutaText;
	}

	public Button getMostrarFicheros() {
		return mostrarFicheros;
	}

	public void setMostrarFicheros(Button mostrarFicheros) {
		this.mostrarFicheros = mostrarFicheros;
	}

	public TextField getNomArchivoText() {
		return nomArchivoText;
	}

	public void setNomArchivoText(TextField nomArchivoText) {
		this.nomArchivoText = nomArchivoText;
	}
}
