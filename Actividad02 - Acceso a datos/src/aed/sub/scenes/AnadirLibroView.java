package aed.sub.scenes;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AnadirLibroView extends VBox{
	private ListView<String> autoresList, nuevoAutorList;
	private TextField libroText, isbnText;
	private DatePicker fechaDate;
	private Button anadirBtn, cerrarBtn, masBtn,menosBtn;
	
	public AnadirLibroView(){
		libroText = new TextField();
		isbnText = new TextField();
		fechaDate = new DatePicker();
		autoresList = new ListView<>();
		nuevoAutorList =  new ListView<>();
		anadirBtn = new Button("Añadir");
		cerrarBtn = new Button("Cerrar");
		masBtn = new Button("+");
		masBtn.setMaxWidth(Double.MAX_VALUE);
		menosBtn = new Button("-");
		menosBtn.setMaxWidth(Double.MAX_VALUE);
		VBox botonesLibro = new VBox(5,masBtn,menosBtn);
		HBox botones = new HBox(5,anadirBtn,cerrarBtn);
		
		
		GridPane panel1 = new GridPane();
		panel1.setPadding(new Insets(5));
		panel1.setHgap(5);
		panel1.setVgap(5);
		panel1.addRow(0, new Label("Libro:"),libroText);
		panel1.addRow(1, new Label("ISBN:"),isbnText);
		panel1.addRow(2, new Label("Fecha:"),fechaDate);
		
		panel1.setAlignment(Pos.CENTER);
		
		GridPane panel2 = new GridPane();
		panel2.setPadding(new Insets(5));
		panel2.setHgap(5);
		panel2.setVgap(5);
		panel2.addRow(0, autoresList,botonesLibro,nuevoAutorList);
		panel2.addRow(1, botones);
		
		getChildren().addAll(panel1,panel2);
		
	}

	public ListView<String> getAutoresList() {
		return autoresList;
	}

	public void setAutoresList(ListView<String> autoresList) {
		this.autoresList = autoresList;
	}

	public ListView<String> getNuevoAutorList() {
		return nuevoAutorList;
	}

	public void setNuevoAutorList(ListView<String> nuevoAutorList) {
		this.nuevoAutorList = nuevoAutorList;
	}

	public TextField getLibroText() {
		return libroText;
	}

	public void setLibroText(TextField libroText) {
		this.libroText = libroText;
	}

	public TextField getIsbnText() {
		return isbnText;
	}

	public void setIsbnText(TextField isbnText) {
		this.isbnText = isbnText;
	}

	public DatePicker getFechaDate() {
		return fechaDate;
	}

	public void setFechaDate(DatePicker fechaDate) {
		this.fechaDate = fechaDate;
	}

	public Button getAnadirBtn() {
		return anadirBtn;
	}

	public void setAnadirBtn(Button anadirBtn) {
		this.anadirBtn = anadirBtn;
	}

	public Button getCerrarBtn() {
		return cerrarBtn;
	}

	public void setCerrarBtn(Button cerrarBtn) {
		this.cerrarBtn = cerrarBtn;
	}

	public Button getMasBtn() {
		return masBtn;
	}

	public void setMasBtn(Button masBtn) {
		this.masBtn = masBtn;
	}

	public Button getMenosBtn() {
		return menosBtn;
	}

	public void setMenosBtn(Button menosBtn) {
		this.menosBtn = menosBtn;
	}
	
	
}
