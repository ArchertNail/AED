package aed.procedimientos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PLibrosAutoresView extends BorderPane{

	private ComboBox<String> isbn;
	private TextField codAutor;
	private Button insertarBtn;
	
	public PLibrosAutoresView(){
		isbn = new ComboBox<>();
		codAutor = new TextField();
		insertarBtn = new Button("Insertar");
		
		GridPane panel1 = new GridPane();
		panel1.setPadding(new Insets(5));
		panel1.setHgap(5);
		panel1.setVgap(5);
		panel1.addRow(0, new Label("ISBN:"),isbn);
		panel1.addRow(1, new Label("Codigo autor:"), codAutor);
		panel1.addRow(2, insertarBtn);
		
		panel1.setAlignment(Pos.TOP_CENTER);
		
		setCenter(panel1);
	}

	public ComboBox<String> getIsbn() {
		return isbn;
	}

	public void setIsbn(ComboBox<String> isbn) {
		this.isbn = isbn;
	}

	public TextField getCodAutor() {
		return codAutor;
	}

	public void setCodAutor(TextField codAutor) {
		this.codAutor = codAutor;
	}

	public Button getInsertarBtn() {
		return insertarBtn;
	}

	public void setInsertarBtn(Button insertarBtn) {
		this.insertarBtn = insertarBtn;
	}
	
	
}
