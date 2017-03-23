package aed.AccesoFicheros;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


import javafx.beans.binding.Bindings;

public class AccesoXMLController {
	private AccesoXMLView xmlView;
	private modeloXml modelo;
	
	private SAXBuilder builder;
	private Document documentJDOM;
	
	public AccesoXMLController() {
		xmlView = new AccesoXMLView();
		modelo = new modeloXml();
		
		bind();
		llenarCombo();
	}
	
	public void bind(){
		modelo.ejemplarProperty().bind(xmlView.getEjemplarText().textProperty());
		modelo.nuevoEjemplarProperty().bind(xmlView.getNuevoEjemplarText().textProperty());
		
		//Bindings.bindBidirectional(xmlView.getLibros().itemsProperty(), modelo.itemsProperty());
		
		xmlView.getMostrarArea().textProperty().bind(modelo.mostraArbolProperty());
		xmlView.getInsertarBtn().setOnAction(e->onInsertarElement());	
		xmlView.getMostrarBtn().setOnAction(e->onLeerXml());
		xmlView.getBorrarBtn().setOnAction(e->onBorrarElement());
		xmlView.getModificarBtn().setOnAction(e->onModificarElement());
		builder = new SAXBuilder();
		try {
			documentJDOM = builder.build(new FileInputStream("libros.xml"));
		} catch (JDOMException | IOException e) {
			
		}
		
	}
	
	private void onModificarElement() {
		modelo.modificarEjemplar();
	}

	private void onBorrarElement() {
		
		modelo.borrarXml();
	}

	public void llenarCombo(){
		Element raiz = documentJDOM.getRootElement();
		List<Element> hijosRaiz = raiz.getChildren();
		
		for(Element hijo: hijosRaiz){
			String titulo = hijo.getAttributeValue("Titulo");
			xmlView.getLibros().getItems().addAll(titulo);
			
		}
		xmlView.getLibros().setValue(hijosRaiz.get(0).getAttributeValue("Titulo"));
	}
	
	
	private void onInsertarElement() {
		modelo.insertarElementXml((xmlView.getLibros().getValue().toString()));
	}

	private void onLeerXml() {
		modelo.leerXml();
	}

	public AccesoXMLView getRoot(){
		return xmlView;
	}
}
