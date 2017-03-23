package aed.acceso.datos;

import aed.procedimientos.PLibrosAutoresController;
import aed.procedimientos.PlistaEjemplaresController;

public class MainController {
	private MainView view;
	private LibrosController librosController;
	private PLibrosAutoresController pLibrosAutoresController;
	private PlistaEjemplaresController pListaEjemplaresController;
	private Main main;
	public MainController() {
		view = new MainView();
		librosController = new LibrosController();
		//pLibrosAutoresController = new PLibrosAutoresController();
		pListaEjemplaresController = new PlistaEjemplaresController();
		//view.getpLibrosAutoresTab().setContent(pLibrosAutoresController.getView());
		view.getLibrosTab().setContent(librosController.getRoot());
		view.getpListaEjemplaresTab().setContent(pListaEjemplaresController.getRoot());
		view.getActualizarBtn().setOnAction(e->onActualizarAction());
		
	}
	private void onActualizarAction() {
		String tipoCon = view.getTiposConexionCombo().getValue();
		
		if(tipoCon.equals("Mysql")){
			CargarDatos.con = Conexiones.mysqlCon();
			System.out.println("Conectado con: MYSQL");
		}
		else if(tipoCon.equals("Acces")){
			CargarDatos.con = Conexiones.accesCon();
			System.out.println("Conectado con: ACCES");
		}
		else if(tipoCon.equals("Sql")){
			CargarDatos.con = Conexiones.SqlCon();
			System.out.println("Conectado con: SQL");
		}
		
		CargarDatos.libros(main);
		pLibrosAutoresController = new PLibrosAutoresController();
		view.getpLibrosAutoresTab().setContent(pLibrosAutoresController.getView());
	}
	public void bind(Main main){
		this.main = main;
		librosController.bind(main);
		pListaEjemplaresController.bind(main.pListaEjemplaresModeloProperty());
	}
	public MainView getRoot(){
		return view;
	}
	
	
}
