package aed.AccesoFicheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccesoFicherosModelo {
	public StringProperty rutaFicheroCrear;
	public StringProperty rutaFicheroMostrar;
	public StringProperty rutaFicheroEditar;
	
	public StringProperty idAutor;
	public StringProperty apellidoAutor;
	public StringProperty cantidadLibros;
	public String apellidoBiteado;
	
	public AccesoFicherosModelo() {
		rutaFicheroCrear = new SimpleStringProperty();
		rutaFicheroMostrar = new SimpleStringProperty();
		rutaFicheroEditar = new SimpleStringProperty();
		
		idAutor = new SimpleStringProperty(this,"id","0");
		apellidoAutor = new SimpleStringProperty(this,"apellidoAutor");
		cantidadLibros = new SimpleStringProperty(this,"cantidadLibros","0");
	}
	
	public boolean Comprobar(){
		
		int tamanioId = idAutor.get().length();
		int tamanioAutor = apellidoAutor.get().length();
		int tamanioCantidad = cantidadLibros.get().length();
		
		
		if(tamanioId<=4 || tamanioAutor<=10 || tamanioCantidad<=4){
			while(apellidoAutor.get().length()<10){
				apellidoAutor.set(apellidoAutor.get().concat(" "));
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public String CrearRandom() throws IOException{
		
		if(Comprobar()){
			File f = new File ("personal");
			if(f.exists()){
				try {
					RandomAccessFile fichero = new RandomAccessFile("personal", "rw");				
					fichero.seek(fichero.length());
					fichero.writeInt(Integer.parseInt(idAutor.get()));
					fichero.writeChars(apellidoAutor.get().concat(","));
					fichero.writeInt(Integer.parseInt(cantidadLibros.get()));
					fichero.close();
					return "correcto";
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return "error";
				}
			}
			else{
				
				return "error";
			}
		}
		else{
			return "error";
		}
		
	}
	public String modificarPersonal() throws IOException{
		if(Comprobar()){
			try {
				
				int posicion = Integer.parseInt(idAutor.get()) * 30;
				String apellido="";
				int cantidad =0;
				RandomAccessFile fichero = new RandomAccessFile("personal", "rw");		
				
				fichero.seek(posicion+4);
				fichero.writeChars(apellidoAutor.get().concat(","));
				fichero.writeInt(Integer.parseInt(cantidadLibros.get()));
				/*System.out.println(cantidad);
				apellidoAutor.set(apellido);
				cantidadLibros.set(cantidad+"");*/
				
				fichero.close();
				return "correcto";
						
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "error";
			}
		}
		else{
			return "error";
		}
			
	}
	
	public String leerPersonal() throws IOException{
		
		try {
			
			int posicion = Integer.parseInt(idAutor.get()) * 30;
			String apellido="";
			int cantidad =0;
			RandomAccessFile fichero = new RandomAccessFile("personal", "rw");		
			
			fichero.seek(posicion+4);
			for (int i = 0; i < 10; i++) {
				apellido += fichero.readChar();
			}
			fichero.readChar();
			cantidad = fichero.readInt();
			System.out.println(cantidad);
			apellidoAutor.set(apellido);
			cantidadLibros.set(cantidad+"");
			
			fichero.close();
			return "correcto";
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "error";
		}
			
	}

	public String ListarFicherosRuta() {
		String mostrarFicheros = "";
		File ruta = new File(rutaFicheroMostrar.get());

		if (ruta.exists()) {
			String[] nombresArchivos = ruta.list();
			for (int i = 0; i < nombresArchivos.length; i++) {
				mostrarFicheros += nombresArchivos[i] + "\n";
				File f = new File(ruta.getAbsolutePath(), nombresArchivos[i]);
				if (f.isDirectory()) {
					String[] archivosSubcarpeta = f.list();
					for (int j = 0; j < archivosSubcarpeta.length; j++) {
						mostrarFicheros += "--" +archivosSubcarpeta[j] + "\n";
					}
				}
			}
		} else {
			mostrarFicheros = "No se ha encontrado ninguna archivo";
		}

		return mostrarFicheros;
	}

	public String LeerFichero() {
		String linea;
		String texto = "";
		try {
			FileReader f = new FileReader(rutaFicheroEditar.get());
			BufferedReader b = new BufferedReader(f);

			while ((linea = b.readLine()) != null) {
				texto += linea + "\n";
			}

			b.close();
			f.close();
		} catch (IOException ex) {
			return "error";
		}
		return texto;
	}

	public String EscribirFichero(String contenido) {
		String frase = contenido;
		try {
			FileWriter escritura = new FileWriter(rutaFicheroEditar.get());
			for (int i = 0; i < frase.length(); i++) {
				escritura.write(frase.charAt(i));
			}
			escritura.close();
		} catch (IOException e) {

			return "error";
		}
		return "Correcto";
	}

	public String BorrarArchivo() {
		File fichero = new File(rutaFicheroEditar.get());
		if (fichero.delete()) {
			return "Correcto";
		} else {
			return "Error";
		}

	}

	public String CrearFichero(String nombreFichero) {
		File fichero = new File(rutaFicheroCrear.get() + "\\" + nombreFichero);
		if (!fichero.isDirectory()) {
			if (!fichero.exists()) {
				try {
					fichero.createNewFile();
				} catch (IOException e) {

					return "error";
				}
			}
		} else {
			return "error";
		}
		return "Correcto";
	}

	public String CrearCarpeta(String nombreCarpeta) {
		File ruta = new File(rutaFicheroCrear.get());
		File carpeta = new File(rutaFicheroCrear.get() + "\\" + nombreCarpeta);
		System.out.println(carpeta);
		if(ruta.isDirectory()){
			carpeta.mkdir();
		}
		else{
			return "error";
		}
		
		return "Correcto";
	}
	
	public String RenombrarFichero(String NuevoNombre) {
		File f1 = new File(rutaFicheroCrear.get());
		File f2 = new File(f1.getParent() + "\\" + NuevoNombre);
		System.out.println(f2);
		;
		boolean correcto = f1.renameTo(f2);
		if (!correcto) {
			return "error";
		} else {
			return "correcto";
		}
	}

	public String MoverFichero(String NuevaRuta) {
		File f1 = new File(rutaFicheroCrear.get());
		File f2 = new File(NuevaRuta+"/"+f1.getName());
		
		if (!f1.exists()) {
			return "error";
		} else {
			boolean correcto = f1.renameTo(f2);
			if (!correcto) {
				return "error";
			}
			
			return "correcto";
		}
	}

	public StringProperty getRutaFicheroMostrar() {
		return rutaFicheroMostrar;
	}

	public void setRutaFicheroMostrar(StringProperty rutaFicheroMostrar) {
		this.rutaFicheroMostrar = rutaFicheroMostrar;
	}

	public StringProperty getRutaFicheroEditar() {
		return rutaFicheroEditar;
	}

	public void setRutaFicheroEditar(StringProperty rutaFicheroEditar) {
		this.rutaFicheroEditar = rutaFicheroEditar;
	}

	public final StringProperty rutaFicheroCrearProperty() {
		return this.rutaFicheroCrear;
	}

	public final String getRutaFicheroCrear() {
		return this.rutaFicheroCrearProperty().get();
	}

	public final void setRutaFicheroCrear(final String rutaFicheroCrear) {
		this.rutaFicheroCrearProperty().set(rutaFicheroCrear);
	}

	public final StringProperty idAutorProperty() {
		return this.idAutor;
	}
	

	public final String getIdAutor() {
		return this.idAutorProperty().get();
	}
	

	public final void setIdAutor(final String idAutor) {
		this.idAutorProperty().set(idAutor);
	}
	

	public final StringProperty apellidoAutorProperty() {
		return this.apellidoAutor;
	}
	

	public final String getApellidoAutor() {
		return this.apellidoAutorProperty().get();
	}
	

	public final void setApellidoAutor(final String apellidoAutor) {
		this.apellidoAutorProperty().set(apellidoAutor);
	}
	

	public final StringProperty cantidadLibrosProperty() {
		return this.cantidadLibros;
	}
	

	public final String getCantidadLibros() {
		return this.cantidadLibrosProperty().get();
	}
	

	public final void setCantidadLibros(final String cantidadLibros) {
		this.cantidadLibrosProperty().set(cantidadLibros);
	}
	
	
}
