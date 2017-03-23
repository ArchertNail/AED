package aed.acceso.datos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ADapp extends Application{

	private MainController mainController;
	private Stage stage;
	private Main main;
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		
		main = new Main();
		mainController = new MainController();
		mainController.bind(main);
		Scene scene = new Scene(mainController.getRoot(),680,440);

		primaryStage.setTitle("Acceso a Datos");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	
	public static void main(String[] args) {
		launch(args);

	}
	
}
