package aed.AccesoFicheros;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AccesoXMLView extends GridPane {
	
	private Button mostrarBtn, insertarBtn, modificarBtn, borrarBtn;
	private TextField ejemplarText, nuevoEjemplarText;
	private TextArea mostrarArea;
	private ComboBox libros; 
        
	
	public AccesoXMLView(){
		
		modificarBtn = new Button("Modificar");
		modificarBtn.setMaxWidth(Double.MAX_VALUE);
		mostrarBtn = new Button("Mostrar xml");
		mostrarBtn.setMaxWidth(Double.MAX_VALUE);
		insertarBtn = new Button("Insertar");
		insertarBtn.setMaxWidth(Double.MAX_VALUE);
		borrarBtn = new Button("Borrar");
		borrarBtn.setMaxWidth(Double.MAX_VALUE);
		ejemplarText = new TextField("");
		nuevoEjemplarText = new TextField("");
		mostrarArea = new TextArea();
		libros = new ComboBox();
		
		
		setPadding(new Insets(5));
		setHgap(5);
		setVgap(5);
		addRow(0, new Label("Libro:") , libros);
		addRow(1, new Label("Ejemplar"),ejemplarText,insertarBtn,borrarBtn);
		addRow(2, new Label("Modificar Ejemplar"),nuevoEjemplarText, modificarBtn);
		addRow(3, mostrarBtn, mostrarArea);
	
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.NEVER);
		col1.setHalignment(HPos.RIGHT);
		col1.setPercentWidth(20);
		
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		col2.setPercentWidth(60);
		
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHgrow(Priority.NEVER);
		col3.setPercentWidth(10);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHgrow(Priority.NEVER);
		col4.setPercentWidth(10);
		getColumnConstraints().setAll(col1, col2, col3,col4);
	}


	public TextArea getMostrarArea() {
		return mostrarArea;
	}


	public void setMostrarArea(TextArea mostrarArea) {
		this.mostrarArea = mostrarArea;
	}


	public Button getMostrarBtn() {
		return mostrarBtn;
	}

	public Button getInsertarBtn() {
		return insertarBtn;
	}

	public Button getModificarBtn() {
		return modificarBtn;
	}

	public Button getBorrarBtn() {
		return borrarBtn;
	}

	public TextField getEjemplarText() {
		return ejemplarText;
	}

	public TextField getNuevoEjemplarText() {
		return nuevoEjemplarText;
	}


	public ComboBox getLibros() {
		return libros;
	}


	public void setLibros(ComboBox libros) {
		this.libros = libros;
	}
	
	
}
