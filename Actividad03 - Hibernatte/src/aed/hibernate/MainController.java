package aed.hibernate;

import aed.hibernate.modelo.Biblioteca;
import aed.hibernate.modelo.Libro;

public class MainController {
	private MainView view;
	private LibrosController librosController;
	private Biblioteca biblioteca;
	private boolean primeraVez;

	public MainController() {
		view = new MainView();
		librosController = new LibrosController();
		view.getLibrosTab().setContent(librosController.getRoot());
		view.getActualizarBtn().setOnAction(e -> onActualizarAction());
		primeraVez = true;

	}

	private void onActualizarAction() {
		if (primeraVez) {
			librosController.getRoot().getAnadirLibroBtn().setDisable(false);
			primeraVez = false;
		}
		System.out.println("Conectado con MySql");
		CargarDatos.libros(biblioteca.libroProperty());
	}

	public void bind(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
		librosController.bind(biblioteca.libroProperty());

	}

	public MainView getRoot() {
		return view;
	}

}
