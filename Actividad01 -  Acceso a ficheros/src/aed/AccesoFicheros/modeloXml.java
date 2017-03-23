package aed.AccesoFicheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class modeloXml {
	public StringProperty ejemplar;
	public StringProperty nuevoEjemplar;
	public StringProperty mostraArbol;
	public SAXBuilder builder;
	public Document documentJDOM;
	public StringProperty items;
	
	public modeloXml(){
		items = new SimpleStringProperty(this,"items");
		ejemplar = new SimpleStringProperty(this,"ejemplar");
		nuevoEjemplar = new SimpleStringProperty(this,"nuevoEjemplar");
		mostraArbol = new SimpleStringProperty(this,"mostraArbol",""); 
		builder = new SAXBuilder();
		try {
			documentJDOM = builder.build(new FileInputStream("libros.xml"));
		} catch (JDOMException | IOException e) {
			
		}
	}
	public void leerXml(){
		
		Element raiz = documentJDOM.getRootElement();
		List<Element> hijosRaiz = raiz.getChildren();
			
		for(Element hijo: hijosRaiz){
			String titulo = hijo.getAttributeValue("Titulo");
			String ISBN = hijo.getAttributeValue("ISBN");
			mostraArbol.set(mostraArbol.get() + hijo.getName() + " -> titulo: "+titulo + " ISBN:" + ISBN +"\n");
			List<Element> hijosLibro = hijo.getChildren();
			for (Element element : hijosLibro) {
				String valor = element.getValue();
				mostraArbol.set(mostraArbol.get() + " " +element.getName() + ":" + valor);
			}
		}
	}
	
	public void borrarXml(){
		Element raiz = documentJDOM.getRootElement();
		List<Element> hijosRaiz = raiz.getChildren();
		boolean encontrado=false;
		
		for(Element hijo: hijosRaiz){
			List<Element> hijosLibro = hijo.getChildren();
			for (Element hijoLibro : hijosLibro) {
				if(hijoLibro.getName().equals("Ejemplares")){
					List<Element> ejemplares = hijoLibro.getChildren();
					Iterator iter = ejemplares.iterator();
					while(iter.hasNext()){
						Element ejemplare = (Element)iter.next();
						if(ejemplar.get().equals(ejemplare.getValue())){
							iter.remove();
							guardarLibro();
							encontrado=true;
						}
					}
				}
			}
			
		}
		
		if(encontrado){
			System.out.println("Se ha Borrado correctamente");
		}
		else{
			System.out.println("No se ha encontrado el ejemplar");
		}
	}
	
	public void insertarElementXml(String libro){
		
		if(!ejemplar.get().isEmpty()){
			Element raiz = documentJDOM.getRootElement();
			List<Element> hijosRaiz = raiz.getChildren();
			
			for(Element hijo: hijosRaiz){
				String titulo = hijo.getAttributeValue("Titulo");
				
				if(titulo.equals(libro)){
					List<Element> hijosLibro = hijo.getChildren();
					for (Element hijoLibro : hijosLibro) {
						if(hijoLibro.getName().equals("Ejemplares")){
							Element hijoEjemplar = new Element("Ejemplar");
							hijoEjemplar.setText(ejemplar.get());
							hijoLibro.addContent(hijoEjemplar);
							
						}
					}
					guardarLibro();
				}
			}
			
			System.out.println("se ha añadido el ejemplar:" + ejemplar.get() + " al libro: " + libro);
		}
		else{
			System.out.println("EL campo esta vacio");
		}
	}
	
	public void modificarEjemplar(){
		
		boolean encuentra=false;
		if(!ejemplar.get().isEmpty() && !nuevoEjemplar.get().isEmpty()){
			Element raiz = documentJDOM.getRootElement();
			List<Element> hijosRaiz = raiz.getChildren();
			
			for(Element hijo: hijosRaiz){
	
				List<Element> hijosLibro = hijo.getChildren();
				for (Element hijoLibro : hijosLibro) {
					if(hijoLibro.getName().equals("Ejemplares")){
						
						List<Element> ejemplares = hijoLibro.getChildren();
						
						for (Element ejemplare : ejemplares) {
							if(ejemplar.get().equals(ejemplare.getValue())){
								ejemplare.setText(nuevoEjemplar.get());
								guardarLibro();
								encuentra= true;
							}
						}
					}
				}
				
			}
			if(encuentra){
				System.out.println("se ha modificado el ejemplar correctamente");
			}
			else{
				System.out.println("No se ha encontrado el ejemplar");
			}
			
		}
		else{
			System.out.println("Uno de los campos esta vacio");
		}
	}
	
	public void guardarLibro(){
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		try{
		FileOutputStream file = new FileOutputStream("libros.xml");
		out.output(documentJDOM, file);
		}catch(IOException e){
			System.out.println("no se realizo correctamente");
		}
	}
	
	public final StringProperty ejemplarProperty() {
		return this.ejemplar;
	}

	public final String getEjemplar() {
		return this.ejemplarProperty().get();
	}

	public final void setEjemplar(final String ejemplar) {
		this.ejemplarProperty().set(ejemplar);
	}
	
	public final StringProperty nuevoEjemplarProperty() {
		return this.nuevoEjemplar;
	}

	public final String getNuevoEjemplar() {
		return this.nuevoEjemplarProperty().get();
	}

	public final void setNuevoEjemplar(final String nuevoEjemplar) {
		this.nuevoEjemplarProperty().set(nuevoEjemplar);
	}

	public final StringProperty mostraArbolProperty() {
		return this.mostraArbol;
	}

	public final String getMostraArbol() {
		return this.mostraArbolProperty().get();
	}

	public final void setMostraArbol(final String mostraArbol) {
		this.mostraArbolProperty().set(mostraArbol);
	}

	public final StringProperty itemsProperty() {
		return this.items;
	}
	

	public final String getItems() {
		return this.itemsProperty().get();
	}

	public final void setItems(final String items) {
		this.itemsProperty().set(items);
	}
	
	
	
	
}
