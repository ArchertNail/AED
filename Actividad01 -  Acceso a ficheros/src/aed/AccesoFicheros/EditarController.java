package aed.AccesoFicheros;

import javafx.event.ActionEvent;

public class EditarController {
	
	private EditarFicheroView eFV;
	private AccesoFicherosModelo aFM;
	
	public EditarController() {
		
		this.aFM = new AccesoFicherosModelo();
		this.eFV = new EditarFicheroView();
		
		bind();
	}
	
	public void bind(){
		this.aFM.getRutaFicheroEditar().bind(eFV.getRutaText().textProperty());
		
		this.eFV.getEditarBtn().setOnAction(e->onEditarArchivo());
		this.eFV.getGuardarBtn().setOnAction(e->onGuardarLectura());
		this.eFV.getBorrarBtn().setOnAction(e->onBorrarArchivo());
	}
	
	private void onBorrarArchivo() {
		if(aFM.BorrarArchivo() != "error"){
			eFV.getTextLabel().textProperty().set("Se ha borrado el Archivo");
			eFV.getTextArea().textProperty().set("");
		}
		else{
			eFV.getTextLabel().textProperty().set("No se ha podido borrar el archivo");
		}
	}

	private void onGuardarLectura() {
		String cadena= eFV.getTextArea().textProperty().get();
		if(aFM.EscribirFichero(cadena)!="error"){
			eFV.getTextLabel().textProperty().set("Archivo Guardado");
		}
		else{
			eFV.getTextLabel().textProperty().set("No se ha podido guardar los cambios");
		}
	}

	private void onEditarArchivo() {
		if(aFM.LeerFichero()!="error"){
			eFV.getTextArea().textProperty().set(aFM.LeerFichero());
			eFV.getTextLabel().textProperty().set("Archivo Encontrado");
		}
		else{
			eFV.getTextLabel().textProperty().set("No se ha encontrado el archivo");
		}
	}

	public EditarFicheroView getRootEditar(){
		return eFV;
	}
}
