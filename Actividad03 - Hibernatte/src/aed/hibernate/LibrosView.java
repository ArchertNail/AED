package aed.hibernate;

import java.time.LocalDate;

import aed.hibernate.bd.AutoresBD;
import aed.hibernate.bd.EjemplaresBD;
import aed.hibernate.bd.LibrosBD;
import aed.hibernate.modelo.Autores;
import aed.hibernate.modelo.DepositoLegal;
import aed.hibernate.modelo.Ejemplares;
import aed.hibernate.modelo.Libro;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LibrosView extends SplitPane{
	
	private Button anadirLibroBtn, borrarLibroBtn, modificarLibroBtn;
	private TableView <Libro> libroTable;
	private TableView <Ejemplares> ejemplarTable;
	private TableView <Autores> autoresTable;
	public LibrosView() {
		Label titulo = new Label("Datos Libros:");
		
		anadirLibroBtn = new Button("Añadir");
		anadirLibroBtn.setDisable(true);
		borrarLibroBtn = new Button("Borrar");
		borrarLibroBtn.setDisable(true);
		modificarLibroBtn = new Button("Modificar");
		modificarLibroBtn.setDisable(true);

		
		HBox botonesTabla = new HBox(5,anadirLibroBtn,borrarLibroBtn,modificarLibroBtn);
		AnchorPane posBotones = new AnchorPane(botonesTabla);
		AnchorPane.setBottomAnchor(botonesTabla, 0.0);
		AnchorPane.setRightAnchor(botonesTabla, 0.0);
		
		libroTable = new TableView<Libro>();
		TableColumn<Libro, String> codColum = new TableColumn<>("CodLibro");
		codColum.setCellValueFactory(cellData -> cellData.getValue().idLibroProperty());
		TableColumn<Libro, String>	tituloColum = new TableColumn<>("Titulo");
		tituloColum.setCellValueFactory(cellData -> cellData.getValue().nombreLibroProperty());
		TableColumn<Libro, String> isbnColum = new TableColumn<>("ISBN");
		isbnColum.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
		TableColumn<Libro, LocalDate> fechaColum = new TableColumn<>("Fecha");
		fechaColum.setCellValueFactory(cellData -> cellData.getValue().fechaNacimientoProperty());
		TableColumn<Libro, DepositoLegal> depositoLegalColum = new TableColumn<>("DepositoLegal");
		depositoLegalColum.setCellValueFactory(cellData -> cellData.getValue().depositoProperty());
		
		libroTable.getColumns().add(codColum);
		libroTable.getColumns().add(tituloColum);
		libroTable.getColumns().add(isbnColum);
		libroTable.getColumns().add(fechaColum);
		libroTable.getColumns().add(depositoLegalColum);
		
		VBox panel1 = new VBox(5,libroTable,posBotones);
		
		ejemplarTable = new TableView<Ejemplares>();
		TableColumn<Ejemplares, String> codEjemplar = new TableColumn<>("CodEjemplar");
		codEjemplar.setCellValueFactory(cellData -> cellData.getValue().codEjemplarProperty());
		TableColumn<Ejemplares, String> importe = new TableColumn<>("Importe");
		importe.setCellValueFactory(cellData -> cellData.getValue().importeProperty());
		TableColumn<Ejemplares, String> tipoMoneda = new TableColumn<>("Tipo Moneda");
		tipoMoneda.setCellValueFactory(cellData -> cellData.getValue().tipoMonedaProperty());
		
		ejemplarTable.getColumns().add(codEjemplar);
		ejemplarTable.getColumns().add(importe);
		ejemplarTable.getColumns().add(tipoMoneda);
	
		
		autoresTable = new TableView<Autores>();
		TableColumn<Autores, String> codAutor= new TableColumn<>("CodAutor");
		codAutor.setCellValueFactory(cellData -> cellData.getValue().codAutorProperty());
		TableColumn<Autores, String> nombreAutor = new TableColumn<>("Autor");
		nombreAutor.setCellValueFactory(cellData -> cellData.getValue().nombreAutorProperty());
		
		autoresTable.getColumns().add(codAutor);
		autoresTable.getColumns().add(nombreAutor);
		
		
		SplitPane panel2 = new SplitPane();
		panel2.setOrientation(Orientation.VERTICAL);
		panel2.setDividerPositions(0.5);
		panel2.getItems().addAll(ejemplarTable,autoresTable);
		setOrientation(Orientation.HORIZONTAL);
		setDividerPositions(0.5);
		getItems().addAll(panel1,panel2);
		
	}

	public Button getAnadirLibroBtn() {
		return anadirLibroBtn;
	}

	public void setAnadirLibroBtn(Button anadirLibroBtn) {
		this.anadirLibroBtn = anadirLibroBtn;
	}

	public Button getBorrarLibroBtn() {
		return borrarLibroBtn;
	}

	public void setBorrarLibroBtn(Button borrarLibroBtn) {
		this.borrarLibroBtn = borrarLibroBtn;
	}

	public Button getModificarLibroBtn() {
		return modificarLibroBtn;
	}

	public void setModificarLibroBtn(Button modificarLibroBtn) {
		this.modificarLibroBtn = modificarLibroBtn;
	}

	public TableView<Libro> getLibroTable() {
		return libroTable;
	}

	public void setLibroTable(TableView<Libro> libroTable) {
		this.libroTable = libroTable;
	}

	public TableView<Ejemplares> getEjemplarTable() {
		return ejemplarTable;
	}

	public void setEjemplarTable(TableView<Ejemplares> ejemplarTable) {
		this.ejemplarTable = ejemplarTable;
	}

	public TableView<Autores> getAutoresTable() {
		return autoresTable;
	}

	public void setAutoresTable(TableView<Autores> autoresTable) {
		this.autoresTable = autoresTable;
	}
	
	
}
