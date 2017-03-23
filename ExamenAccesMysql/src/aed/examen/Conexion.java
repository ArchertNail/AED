package aed.examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection con;
	
	public static Connection mysql(){
		con = null;
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    
		    con = DriverManager.getConnection(
		            "jdbc:mysql://localhost/bddiscosventas?"
		            + "user=root&password=");
		    System.out.println("Conectado con: Mysql");
		    
		} catch (Exception e) {
		    System.out.println("NO SE HA PODIDO CONECTAR CON MYSQL");
		    System.exit(0);
		}
		
		return con;
	}
	
	public static Connection acces(){
		con = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con=DriverManager.getConnection("jdbc:ucanaccess://Ventas.accdb");
			System.out.println("Conectado con: Acces");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("NO SE HA PODIDO CONECTAR CON ACCES");
			System.exit(0);
		}
		return con;
	}
}
