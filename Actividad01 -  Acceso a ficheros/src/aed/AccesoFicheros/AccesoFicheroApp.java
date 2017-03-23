package aed.AccesoFicheros;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class AccesoFicheroApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Tab crearTab = new Tab("Crear");
		crearTab.setClosable(false);
		crearTab.setContent(new CrearFicheroController().getRootCrear());
		
		Tab editarTab = new Tab("Editar");
		editarTab.setClosable(false);
		editarTab.setContent(new EditarController().getRootEditar());
		
		Tab mostrarTab = new Tab("Mostrar");
		mostrarTab.setClosable(false);
		mostrarTab.setContent(new MostrarController().getRootMostrar());
		
		Tab randomFile = new Tab("RandomFile");
		randomFile.setClosable(false);
		randomFile.setContent(new RandomAccessFileController().getRoot());
		
		Tab XML = new Tab("XML");
		XML.setClosable(false);
		XML.setContent(new AccesoXMLController().getRoot());
		
		TabPane root = new TabPane();
		root.getTabs().addAll(crearTab,editarTab,mostrarTab,randomFile,XML);
		
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
