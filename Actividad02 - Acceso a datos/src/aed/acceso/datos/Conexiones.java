package aed.acceso.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiones {
	
	public static Connection mysqlCon(){
		
		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception e) {

		    System.out.println(e.toString());
		}

		Connection con = null;
		try {
		    con = DriverManager.getConnection(
		            "jdbc:mysql://localhost/bdbiblioteca?"
		            + "user=root&password=");

		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		}
		
		return con;
	}
	
	public static Connection accesCon(){
		Connection con = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con=DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Archer/Documents/bdbibliotecaAcces.accdb");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static Connection SqlCon(){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String conectionSql = "jdbc:sqlserver://ARCHERT\\SQLEXPRESS:1433;"+"databaseName=bdbiblioteca;user=sa;password=1234;";
			con=DriverManager.getConnection(conectionSql);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
