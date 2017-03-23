package aed.AccesoFicheros;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditarFicheroView extends VBox {
	
	private TextField rutaText;
	private Button editarBtn,borrarBtn,guardarBtn;
	private Label textLabel;
	private TextArea textArea;
	public EditarFicheroView() {
		
		rutaText = new TextField();
		rutaText.setMinWidth(420);
		editarBtn = new Button("Editar");
		borrarBtn = new Button("Borrar");
		guardarBtn = new Button("Guardar");
		textLabel = new Label("Selecciona un archivo");
		textArea = new TextArea();
		
		HBox fila1 = new HBox(5,rutaText,editarBtn,borrarBtn,guardarBtn);
		fila1.setAlignment(Pos.BASELINE_LEFT);
		
		setPadding(new Insets(5));
		setSpacing(5);
		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(fila1,textArea,textLabel);
	}
	
	public TextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}

	public TextField getRutaText() {
		return rutaText;
	}
	public void setRutaText(TextField rutaText) {
		this.rutaText = rutaText;
	}
	public Button getEditarBtn() {
		return editarBtn;
	}
	public void setEditarBtn(Button editarBtn) {
		this.editarBtn = editarBtn;
	}
	public Button getBorrarBtn() {
		return borrarBtn;
	}
	public void setBorrarBtn(Button borrarBtn) {
		this.borrarBtn = borrarBtn;
	}
	public Label getTextLabel() {
		return textLabel;
	}
	public void setTextLabel(Label textLabel) {
		this.textLabel = textLabel;
	}

	public Button getGuardarBtn() {
		return guardarBtn;
	}

	public void setGuardarBtn(Button guardarBtn) {
		this.guardarBtn = guardarBtn;
	}
	
}
