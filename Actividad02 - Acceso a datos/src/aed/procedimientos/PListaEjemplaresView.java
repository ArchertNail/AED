package aed.procedimientos;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PListaEjemplaresView extends VBox{
	
	private TextField nomAutor;
	private Button actualizarAutorBtn, cantidadBtn;
	private TableView<PListaEjemplaresModelo> ejemplaresTable;
	
	public PListaEjemplaresView(){
		
		nomAutor = new TextField();
		actualizarAutorBtn = new Button("Actualizar");
		cantidadBtn = new Button("Cantidad");
		ToolBar cabecera = new ToolBar(new Label("Introduce Autor:"),nomAutor,actualizarAutorBtn,cantidadBtn);
		
		ejemplaresTable = new TableView<>();
		TableColumn<PListaEjemplaresModelo, String> codLibro = new TableColumn<>("CodLibro");
		codLibro.setCellValueFactory(CellData -> CellData.getValue().codLibroProperty());
		TableColumn<PListaEjemplaresModelo, String> nombreLibro = new TableColumn<>("Libro");
		nombreLibro.setCellValueFactory(CellData -> CellData.getValue().nombreLibroProperty());
		TableColumn<PListaEjemplaresModelo, String> isbn = new TableColumn<>("ISBN");
		isbn.setCellValueFactory(CellData -> CellData.getValue().isbnProperty());
		TableColumn<PListaEjemplaresModelo, LocalDate> fechaIntro = new TableColumn<>("Fecha");
		fechaIntro.setCellValueFactory(CellData -> CellData.getValue().fechaProperty());
		
		ejemplaresTable.getColumns().add(codLibro);
		ejemplaresTable.getColumns().add(nombreLibro);
		ejemplaresTable.getColumns().add(isbn);
		ejemplaresTable.getColumns().add(fechaIntro);
		
		getChildren().addAll(cabecera,ejemplaresTable);
		
	}

	public TextField getNomAutor() {
		return nomAutor;
	}

	public void setNomAutor(TextField nomAutor) {
		this.nomAutor = nomAutor;
	}

	public Button getActualizarAutorBtn() {
		return actualizarAutorBtn;
	}

	public void setActualizarAutorBtn(Button actualizarAutorBtn) {
		this.actualizarAutorBtn = actualizarAutorBtn;
	}

	public TableView<PListaEjemplaresModelo> getEjemplaresTable() {
		return ejemplaresTable;
	}

	public void setEjemplaresTable(TableView<PListaEjemplaresModelo> ejemplaresTable) {
		this.ejemplaresTable = ejemplaresTable;
	}

	public Button getCantidadBtn() {
		return cantidadBtn;
	}

	public void setCantidadBtn(Button cantidadBtn) {
		this.cantidadBtn = cantidadBtn;
	}

	
	
}
