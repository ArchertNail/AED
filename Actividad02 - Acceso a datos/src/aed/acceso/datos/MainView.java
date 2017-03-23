package aed.acceso.datos;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class MainView extends VBox{
	private ComboBox<String> tiposConexionCombo;
	private Tab librosTab,pLibrosAutoresTab,pListaEjemplaresTab;
	private Button actualizarBtn;
	public MainView(){
		
		tiposConexionCombo = new ComboBox<>();
		tiposConexionCombo.getItems().addAll("Mysql","Sql","Acces");
		tiposConexionCombo.setValue("Mysql");
		actualizarBtn = new Button("Actualizar");

		
		ToolBar conexionBar = new ToolBar(new Label("¿Que conexion deseas realizar?"),tiposConexionCombo,actualizarBtn);
		
	
		librosTab =  new Tab("Libros");
		librosTab.setClosable(false);
		
		pLibrosAutoresTab = new Tab("pLibrosAutores");
		pLibrosAutoresTab.setClosable(false);
		
		pListaEjemplaresTab = new Tab("pListaEjemplares");
		pListaEjemplaresTab.setClosable(false);
		
		TabPane panelTab = new TabPane();
		panelTab.getTabs().addAll(librosTab,pLibrosAutoresTab,pListaEjemplaresTab);
		setPadding(new Insets(5));
		
		getChildren().addAll(conexionBar,panelTab);
		
	}
	public ComboBox<String> getTiposConexionCombo() {
		return tiposConexionCombo;
	}
	public void setTiposConexionCombo(ComboBox<String> tiposConexionCombo) {
		this.tiposConexionCombo = tiposConexionCombo;
	}
	public Tab getLibrosTab() {
		return librosTab;
	}
	public void setLibrosTab(Tab librosTab) {
		this.librosTab = librosTab;
	}
	public Tab getpLibrosAutoresTab() {
		return pLibrosAutoresTab;
	}
	public void setpLibrosAutoresTab(Tab pLibrosAutoresTab) {
		this.pLibrosAutoresTab = pLibrosAutoresTab;
	}
	public Tab getpListaEjemplaresTab() {
		return pListaEjemplaresTab;
	}
	public void setpListaEjemplaresTab(Tab pListaEjemplaresTab) {
		this.pListaEjemplaresTab = pListaEjemplaresTab;
	}
	public Button getActualizarBtn() {
		return actualizarBtn;
	}
	public void setActualizarBtn(Button actualizarBtn) {
		this.actualizarBtn = actualizarBtn;
	}
	
	
}
