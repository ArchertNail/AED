package aed.acceso.datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import aed.procedimientos.PListaEjemplaresModelo;
import javafx.beans.property.ListProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class CargarDatos {
	
	
	
	public static Connection con;
	
	
	/*                __________________________
	 * 				 |						    |
	 *-------------- |     INTERFAZ PRINCIPAL	|------------------
	 * 				 |__________________________|
	 * 		
	 */
	
	public static void libros(Main main){
		
		main.librosProperty().clear();
		main.autoresProperty().clear();
		main.ejemplaresProperty().clear();
		
		Statement stmt = null;
		String query = "SELECT * FROM Libros";
		try {
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String idLibro = rs.getString(1);
				String nombreLibro = rs.getString(2);
				String isbnLibro = rs.getString(3);
				LocalDate fecha =rs.getDate(4).toLocalDate();

				Libros libro = new Libros();
				libro.setIdLibro(idLibro);
				libro.setNombreLibro(nombreLibro);
				libro.setIsbn(isbnLibro);
				libro.setFechaNacimiento(fecha);
				main.getLibros().add(libro);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void ejemplaresyautores(Main main, Libros newSelection) {
		if(newSelection == null){
			System.out.println("Error Controlado");
		}
		else{
			ejemplares(main, newSelection);
			autores(main, newSelection);
		}
	}
	
	public static void ejemplares(Main main, Libros newSelection){
		
		main.ejemplaresProperty().clear();
		
		int codLibro = Integer.parseInt(newSelection.getIdLibro());
		
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("select * FROM ejemplares where ejemplares.codLibro = ?");
			stmt.setInt(1, codLibro); 
			stmt.executeQuery();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String codEjemplar = rs.getString(1);
				String idLibro = rs.getString(2);
				String importe = rs.getString(3);
				String tipoMoneda = rs.getString(4);

				Ejemplares ejemplar = new Ejemplares();
				ejemplar.setCodEjemplar(codEjemplar);
				ejemplar.setCodLibro(idLibro);
				ejemplar.setImporte(importe);
				ejemplar.setTipoMoneda(tipoMoneda);
				
				main.getEjemplares().add(ejemplar);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	private static void autores(Main main, Libros newSelection) {
		
	
		main.autoresProperty().clear();
		int codLibro = Integer.parseInt(newSelection.getIdLibro());
		
		PreparedStatement stmt = null;
		
		try{
			stmt = con.prepareStatement("SELECT l.codAutor,(SELECT autores.nombreAutor from autores WHERE autores.codAutor = l.codAutor) as autor FROM librosautores as l WHERE l.codLibro = ?");
			stmt.setInt(1, codLibro); 
			stmt.executeQuery();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				String codAutor= rs.getString(1);
				String nomAutor= rs.getString(2);

				Autores autor = new Autores();
				autor.setCodAutor(codAutor);
				autor.setNombreAutor(nomAutor);
				
				main.getAutores().add(autor);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void borraLibro(String codLibro, Main main){
		boolean borrar = true;
		int contadorEjemplares =0;
		int contadorAutores=0;
		
		//COMPROBAR EJEMPLARES
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM ejemplares WHERE codEjemplar = ?");
			stmt.setString(1, codLibro); 
			stmt.executeQuery();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				contadorEjemplares++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		//COMPROBAR AUTORES
		PreparedStatement stmt2 = null;
		try{
			stmt2 = con.prepareStatement("SELECT * FROM librosautores WHERE codLibro = ?");
			stmt2.setString(1, codLibro); 
			stmt2.executeQuery();
			ResultSet rs=stmt2.executeQuery();
			while(rs.next()){
				contadorAutores++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(contadorEjemplares!=0 || contadorAutores!=0){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CUIDAO!!");
			alert.setHeaderText("El libro tiene EJEMPLARES o AUTORES!!");
			alert.setContentText("¿Estas seguro de querer borrar?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				
			} else {
			   borrar=false;
			}
		}
		
		if(borrar){
			
			PreparedStatement stmt3 = null;
			try{
				stmt3 = con.prepareStatement("DELETE FROM libros WHERE codLibro = ?");
				stmt3.setString(1, codLibro); 
				stmt3.executeUpdate();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			System.out.println("BORRAO!!");
			libros(main);
		}
		else{
			System.out.println("BORRADO ABORTADO");
		}
		
	}
	
	
	
	/*                ________________________
	 * 				 |						  |
	 *-------------- |     CARGAR COMBOBOX	  |------------------------
	 * 				 |________________________|
	 * 		
	 */
	
	
	public static ArrayList<String> ISBNS(){
		
		ArrayList<String> isbns = new ArrayList<>();
		
		Statement stmt = null;
		String query = "SELECT ISBN FROM Libros";
		try {
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String isbn = rs.getString(1);
				
				isbns.add(isbn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isbns;
	}
	
	public static ArrayList<String> comboAutores(){
		
		ArrayList<String> autores = new ArrayList<>();
		
		Statement stmt = null;
		String query = "SELECT * FROM autores";

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String codAutor = rs.getString(1);
				String nombreAutor = rs.getString(2);
				
				autores.add(codAutor +"->" + nombreAutor);
				
				//view.getAutoresList().getItems().addAll(codAutor +"->" + nombreAutor);	
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return autores;
	}
	
	
	/*                ________________________
	 * 				 |						  |
	 *-------------- |     AÑADIR LIBROS	  |------------------------
	 * 				 |________________________|
	 * 		
	 */
	
	
	public static void insertarLibros(String nomLibro, String isbn, String fecha, int tamanio, ArrayList<String> autoresNuevo){
		
		String codLibro="";
		
		PreparedStatement stmt = null;
		
		Date date = Date.valueOf(fecha);
		
		System.out.println(date);
		
		try{
			stmt = con.prepareStatement("INSERT INTO libros (nombreLibro,ISBN,FechaIntro) VALUES (?,?,?)");
			stmt.setString(1, nomLibro); 
			stmt.setString(2, isbn); 
			stmt.setDate(3, date); 
			
			stmt.executeUpdate();
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//RECUPERA ID LIBRO
		
		stmt = null;
		try{
			stmt = con.prepareStatement("SELECT libros.codLibro FROM libros WHERE libros.ISBN = ?");
			stmt.setString(1, isbn); 
			stmt.executeQuery();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				codLibro = rs.getString(1);
				System.out.println(codLibro);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		//INSERTAR AUTORES
		
		for(int i=0;i<tamanio;i++){
			
			stmt = null;
			String cadena = autoresNuevo.get(i);
			String[] codigoAutor = cadena.split("-");
			
			try{
				stmt = con.prepareStatement("INSERT INTO librosautores VALUES (?,?)");
				stmt.setString(1, codigoAutor[0]); 
				stmt.setString(2, codLibro); 
				
				stmt.executeUpdate();
				
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	/*                ________________________
	 * 				 |						  |
	 *-------------- |     MODIFICAR LIBROS	  |------------------------
	 * 				 |________________________|
	 * 		
	 */
	
	public static void modificarLibro(String idLibro,String nombreLibro, String isbnLibro, String fecha, int tamanio, ArrayList<String> nuevosAutores){
		Date date = Date.valueOf(fecha);
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE libros SET nombreLibro=?,ISBN=?,FechaIntro=? WHERE codLibro=?");
			stmt.setString(1, nombreLibro); 
			stmt.setString(2, isbnLibro); 
			stmt.setDate(3, date);
			stmt.setString(4, idLibro);
			
			stmt.executeUpdate();
			
			System.out.println("ACTUALIZAO!!");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		/*ACTUALIZAR AUTORES DEL LIBRO*/
		
		stmt = null;
		try{
			stmt = con.prepareStatement("DELETE FROM librosautores WHERE codLibro=?");
			stmt.setString(1, idLibro);
			
			stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		/*INSERTAR NUEVOS AUTORES*/
		
		for(int i=0;i<tamanio;i++){
			
			stmt = null;
			String cadena = nuevosAutores.get(i);
			String[] codigoAutor = cadena.split("-");
			
			try{
				stmt = con.prepareStatement("INSERT INTO librosautores VALUES (?,?)");
				stmt.setString(1, codigoAutor[0]); 
				stmt.setString(2, idLibro); 
				
				stmt.executeUpdate();
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	/*                ________________________
	 * 				 |						  |
	 *-------------- |     PROCEDIMIENTOS	  |------------------------
	 * 				 |________________________|
	 * 		
	 */
	
	public static void pLibrosAutores(String isbn, String codAutor){
		
		 try {
			 
			CallableStatement cst = con.prepareCall("{call pLibrosAutores (?,?,?)}");
			
			cst.registerOutParameter(1, java.sql.Types.INTEGER);
			cst.setString(2, isbn);
			cst.setString(3, codAutor);
			
			cst.execute();
			
			int codError = cst.getInt(1);
			
			if(codError == 0){
				//Alert alerta = new 
				System.out.println("Autor añadido");
			}
			
		} catch (SQLException e) {
			System.out.println("FAIL!!!!");
		}
		 
	}
	
	public static void pListaEjemplares(String autor, ListProperty<PListaEjemplaresModelo> modelo){
		
		modelo.clear();
		
		 try {
			CallableStatement cst = con.prepareCall("{CALL pListaEjemplares(?)}");
		
			cst.setString(1, autor);
			
			ResultSet resultado = cst.executeQuery();
			
			while(resultado.next()) {
				
				PListaEjemplaresModelo nuevoPLEM = new PListaEjemplaresModelo();
				nuevoPLEM.setCodLibro(resultado.getString(1));
				nuevoPLEM.setNombreLibro(resultado.getString(2));
				nuevoPLEM.setIsbn(resultado.getString(3));
				nuevoPLEM.setFecha(LocalDate.parse(resultado.getString(4)));
				
				modelo.add(nuevoPLEM);
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		 
	}
	
	public static void pCantidadEjemplares(String isbn){
		
		 try {
			CallableStatement cst = con.prepareCall("{CALL pCantidadEjemplares(?,?,?)}");
		
			cst.registerOutParameter(1, java.sql.Types.INTEGER);
			cst.registerOutParameter(2, java.sql.Types.NVARCHAR);
			cst.setString(3, isbn);
			
			cst.execute();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cantidad ejemplares");
			alert.setHeaderText(null);
			alert.setContentText("Cantidad: " + cst.getString(1) + "\nFecha: " + cst.getString(2));

			alert.showAndWait();
			
		
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		 
	}
	
	public static void fNumAutorLibro(String autor){
		
		try{
			
			CallableStatement consulta = con.prepareCall("{ ? =call fnumAutorLibro ( ? )}");
            consulta.setString(2, autor);
            consulta.registerOutParameter(1, java.sql.Types.INTEGER);
            consulta.execute();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cantidad Libros");
			alert.setHeaderText(null);
			alert.setContentText("Cantidad: " + consulta.getInt(1));
			alert.showAndWait();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
