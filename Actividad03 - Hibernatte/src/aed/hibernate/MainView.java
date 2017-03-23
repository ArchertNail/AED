package aed.hibernate;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class MainView extends VBox{
	private Tab librosTab;
	private Button actualizarBtn;
	public MainView(){

		actualizarBtn = new Button("HIBERNATE");
	
		ToolBar conexionBar = new ToolBar(actualizarBtn);
	
		librosTab =  new Tab("Libros");
		librosTab.setClosable(false);
		
		TabPane panelTab = new TabPane();
		panelTab.getTabs().addAll(librosTab);
		setPadding(new Insets(5));
		
		getChildren().addAll(conexionBar,panelTab);
		
	}

	public Tab getLibrosTab() {
		return librosTab;
	}
	public void setLibrosTab(Tab librosTab) {
		this.librosTab = librosTab;
	}
	public Button getActualizarBtn() {
		return actualizarBtn;
	}
	public void setActualizarBtn(Button actualizarBtn) {
		this.actualizarBtn = actualizarBtn;
	}
	
	
}
