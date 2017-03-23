package aed.AccesoFicheros;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RandomAccessFileView extends VBox {
	
	private TextField codigo,apellidoText, cantLibroText;
	private Button mostrarBtn, crearBtn, modificarBtn;
	
	public RandomAccessFileView(){
		
		codigo = new TextField();
		apellidoText = new TextField();
		cantLibroText = new TextField("0");
		
		crearBtn = new Button("Crear");
		mostrarBtn = new Button("Mostrar");
		modificarBtn = new Button("Modificar");
		
		GridPane panel1 = new GridPane();
		panel1.setPadding(new Insets(5));
		panel1.setHgap(5);
		panel1.setVgap(5);
		panel1.addRow(0, new Label("ID: "), codigo,crearBtn);
		panel1.addRow(1, new Label("Apellido: "), apellidoText);
		panel1.addRow(2, new Label("Cantidad libros: "),cantLibroText);
		panel1.addRow(3, mostrarBtn, modificarBtn);
		GridPane.setRowSpan(crearBtn, 3);
		
		//add(nuevaRutaBtn, 4, 3);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.NEVER);
		
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHgrow(Priority.NEVER);
		
		panel1.getColumnConstraints().setAll(col1, col2, col3);
		
		/*HBox fila1 = new HBox(5,mostrarBtn);
		fila1.setAlignment(Pos.BASELINE_LEFT);*/
	
		setPadding(new Insets(5));
		setSpacing(5);
		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(panel1);
	}
	
	public Button getMostrarBtn() {
		return mostrarBtn;
	}
	public void setMostrarBtn(Button mostrarBtn) {
		this.mostrarBtn = mostrarBtn;
	}

	public TextField getApellidoText() {
		return apellidoText;
	}

	public void setApellidoText(TextField apellidoText) {
		this.apellidoText = apellidoText;
	}

	public TextField getCantLibroText() {
		return cantLibroText;
	}

	public void setCantLibroText(TextField cantLibroText) {
		this.cantLibroText = cantLibroText;
	}

	public Button getCrearBtn() {
		return crearBtn;
	}

	public void setCrearBtn(Button crearBtn) {
		this.crearBtn = crearBtn;
	}

	public TextField getCodigo() {
		return codigo;
	}

	public void setCodigo(TextField codigo) {
		this.codigo = codigo;
	}

	public Button getModificarBtn() {
		return modificarBtn;
	}

	public void setModificarBtn(Button modificarBtn) {
		this.modificarBtn = modificarBtn;
	}

	
	
}
