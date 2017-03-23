package aed.AccesoFicheros;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class CrearFicheroView extends GridPane {
	private TextField seleccionarFicheroText,ficheroText,carpetaText, renombrarText, nuevaRutaText;
	private Button crearFicheroBtn,crearCarpetaBtn,renombrarBtn,nuevaRutaBtn;
	private Label textLabel;
	public CrearFicheroView() {
		seleccionarFicheroText = new TextField();
		ficheroText = new TextField();
		carpetaText = new TextField();
		renombrarText = new TextField();
		nuevaRutaText = new TextField();
		
		crearFicheroBtn = new Button("Fichero");
		crearFicheroBtn.setMaxWidth(Double.MAX_VALUE);
		
		crearCarpetaBtn = new Button("Carpeta");
		crearCarpetaBtn.setMaxWidth(Double.MAX_VALUE);
		
		renombrarBtn = new Button("Renombrar");
		renombrarBtn.setMaxWidth(Double.MAX_VALUE);
		nuevaRutaBtn = new Button("Cambiar");
		nuevaRutaBtn.setMaxWidth(Double.MAX_VALUE);
		textLabel = new Label("");
		
		setPadding(new Insets(15));
		setHgap(5);
		setVgap(5);
		addRow(0, new Label("Seleccionar archivo: "), seleccionarFicheroText);
		addRow(1, new Label("Crear archivo: "),crearFicheroBtn, ficheroText, crearCarpetaBtn,  carpetaText);
		addRow(2, new Label("Renombrar fichero:"), renombrarText);
		addRow(3, new Label("Nueva ruta:"), nuevaRutaText);
		setColumnSpan(seleccionarFicheroText, 4);
		setColumnSpan(renombrarText, 3);
		setColumnSpan(nuevaRutaText, 3);
		add(textLabel, 1, 4);
		setColumnSpan(textLabel, 4);
		add(renombrarBtn, 4, 2);
		add(nuevaRutaBtn, 4, 3);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.NEVER);
		
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHgrow(Priority.NEVER);
		
		getColumnConstraints().setAll(col1, col2, col3);
	}

	public TextField getRenombrarText() {
		return renombrarText;
	}

	public void setRenombrarText(TextField renombrarText) {
		this.renombrarText = renombrarText;
	}

	public TextField getNuevaRutaText() {
		return nuevaRutaText;
	}

	public void setNuevaRutaText(TextField nuevaRutaText) {
		this.nuevaRutaText = nuevaRutaText;
	}

	public Button getRenombrarBtn() {
		return renombrarBtn;
	}

	public void setRenombrarBtn(Button renombrarBtn) {
		this.renombrarBtn = renombrarBtn;
	}

	public Button getNuevaRutaBtn() {
		return nuevaRutaBtn;
	}

	public void setNuevaRutaBtn(Button nuevaRutaBtn) {
		this.nuevaRutaBtn = nuevaRutaBtn;
	}

	public Label getTextLabel() {
		return textLabel;
	}

	public void setTextLabel(Label textLabel) {
		this.textLabel = textLabel;
	}

	public TextField getSeleccionarFicheroText() {
		return seleccionarFicheroText;
	}

	public void setSeleccionarFicheroText(TextField seleccionarFicheroText) {
		this.seleccionarFicheroText = seleccionarFicheroText;
	}

	public TextField getFicheroText() {
		return ficheroText;
	}

	public void setFicheroText(TextField ficheroText) {
		this.ficheroText = ficheroText;
	}

	public TextField getCarpetaText() {
		return carpetaText;
	}

	public void setCarpetaText(TextField carpetaText) {
		this.carpetaText = carpetaText;
	}

	public Button getCrearFicheroBtn() {
		return crearFicheroBtn;
	}

	public void setCrearFicheroBtn(Button crearFicheroBtn) {
		this.crearFicheroBtn = crearFicheroBtn;
	}

	public Button getCrearCarpetaBtn() {
		return crearCarpetaBtn;
	}

	public void setCrearCarpetaBtn(Button crearCarpetaBtn) {
		this.crearCarpetaBtn = crearCarpetaBtn;
	}
	
	
	
	
}
