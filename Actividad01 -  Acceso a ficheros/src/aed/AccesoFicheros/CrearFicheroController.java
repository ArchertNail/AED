package aed.AccesoFicheros;

public class CrearFicheroController {
	
	private CrearFicheroView cFV;
	private AccesoFicherosModelo aFM;
	
	public CrearFicheroController() {
		
		this.cFV = new CrearFicheroView();
		this.aFM = new AccesoFicherosModelo();
	
		bind();
	}
	
	public void bind(){
		this.aFM.rutaFicheroCrearProperty().bind(cFV.getSeleccionarFicheroText().textProperty());
		cFV.getRenombrarBtn().setOnAction(e->onRenombrarArchivo());
		cFV.getCrearFicheroBtn().setOnAction(e->onCrearFichero());
		cFV.getCrearCarpetaBtn().setOnAction(e->onCrearCarpeta());
		cFV.getNuevaRutaBtn().setOnAction(e->onMoverFichero());
		
	}
	
	private void onCrearCarpeta() {
		String nombreCarpeta = cFV.getCarpetaText().textProperty().get();
		if(aFM.CrearCarpeta(nombreCarpeta)!="error"){
			cFV.getTextLabel().textProperty().set("El archivo se ha creado correctamente");
		}
		else{
			cFV.getTextLabel().textProperty().set("Selecciona una ruta valida");
		}
	}

	private void onMoverFichero() {
		String NuevaRuta = cFV.getNuevaRutaText().getText();
		if(aFM.MoverFichero(NuevaRuta) !="error"){
			cFV.getTextLabel().textProperty().set("El Archivo se ha movido correctamente!");
		}
		else{
			cFV.getTextLabel().textProperty().set("No se ha podido mover el archivo!");
		}
	}

	private void onRenombrarArchivo() {
		String nuevoNombre = cFV.getRenombrarText().getText();
		
		if(aFM.RenombrarFichero(nuevoNombre) !="error"){
			cFV.getTextLabel().textProperty().set("Archivo Renombrado!");
		}
		else{
			cFV.getTextLabel().textProperty().set("No se ha podido llevar a cabo la operacion");
		}
	}

	private void onCrearFichero() {
		String nombreFichero = cFV.getFicheroText().textProperty().get();
		if(aFM.CrearFichero(nombreFichero)!="error"){
			cFV.getTextLabel().textProperty().set("El archivo se ha creado correctamente");
		}
		else{
			cFV.getTextLabel().textProperty().set("Selecciona una ruta valida");
		}
	}

	public CrearFicheroView getRootCrear(){
		return cFV;
	}
}
