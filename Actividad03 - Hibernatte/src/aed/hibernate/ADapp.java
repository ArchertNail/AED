package aed.hibernate;

import aed.hibernate.modelo.Biblioteca;
import aed.hibernate.modelo.Libro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ADapp extends Application{

	private MainController mainController;
	private Stage stage;
	private Biblioteca biblioteca;
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		
		biblioteca = new Biblioteca();
		mainController = new MainController();
		mainController.bind(biblioteca);
		Scene scene = new Scene(mainController.getRoot(),820,440);

		primaryStage.setTitle("Acceso a Datos");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	
	public static void main(String[] args) {
		launch(args);

	}
	
}
