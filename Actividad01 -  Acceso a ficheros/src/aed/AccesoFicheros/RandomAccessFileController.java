package aed.AccesoFicheros;

import java.io.IOException;

import javafx.beans.binding.Bindings;

public class RandomAccessFileController {
	private RandomAccessFileView randomView;
	private AccesoFicherosModelo modelo;
	
	public RandomAccessFileController() {
		this.randomView = new RandomAccessFileView();
		this.modelo = new AccesoFicherosModelo();
		bind();
	}
	
	public void bind(){
		
		this.randomView.getCrearBtn().setOnAction(e->onCrearRandom());
		this.randomView.getMostrarBtn().setOnAction(e->OnMostrarRandom());
		this.randomView.getModificarBtn().setOnAction(e->onModificarRandom());
		this.modelo.idAutorProperty().bind(randomView.getCodigo().textProperty());
		//this.modelo.apellidoAutorProperty().bind(randomView.getApellidoText().textProperty());
		Bindings.bindBidirectional(
				randomView.getApellidoText().textProperty(),
				modelo.apellidoAutorProperty()
		);
		Bindings.bindBidirectional(
				randomView.getCantLibroText().textProperty(),
				modelo.cantidadLibrosProperty()
		);
	}
	private void onModificarRandom() {
		try {
			if(modelo.modificarPersonal()!="error"){
				System.out.println("archivo Modificado");
			}
			else{
				System.out.println("ERROR");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void OnMostrarRandom() {
		try {
			if(modelo.leerPersonal()!="error"){
				System.out.println("archivo Correcto");
			}
			else{
				System.out.println("ERROR");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void onCrearRandom() {
		try {
			if(modelo.CrearRandom()!="error"){
				System.out.println("archivo Correcto");
			}
			else{
				System.out.println("ERROR");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RandomAccessFileView getRoot(){
		return randomView;
	}
}
