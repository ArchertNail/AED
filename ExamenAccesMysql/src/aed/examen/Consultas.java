package aed.examen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Consultas {

	private Connection con;
	private PreparedStatement stmtPrepare = null;
	private Scanner teclado;
	public Consultas(Connection con) {
		this.con = con;	
		teclado = new Scanner(System.in);
	}
	
	public void pregunta1(){
		
		System.out.println("-----PREGUNTA 1-----");
		System.out.println("Introduce un precio:");
		String precio = teclado.nextLine();
		
		stmtPrepare = null;
		try {
			stmtPrepare = con.prepareStatement(" SELECT discos.Nombre, ventas.FechaVenta, discoventas.Cantidad"
											   +" FROM discos"
											   +" LEFT JOIN discoventas ON discoventas.CodDiscos = discos.CodDiscos"
											   +" LEFT JOIN ventas ON ventas.CodVentas = discoventas.CodVentas" 
											   +" WHERE discos.Precio < ?");
			stmtPrepare.setString(1, precio);
			
			stmtPrepare.executeQuery();
			ResultSet rs=stmtPrepare.executeQuery();
			int contador=0;
			while(rs.next()){
				contador++;
				String nombre = rs.getString(1);
				Date fecha = rs.getDate(2);
				String cantidad = rs.getString(3);
				
				System.out.println("Fila"+contador + " --> " + nombre + " | " + fecha + " | " + cantidad);
			}
			if(contador == 0){
				System.out.println("NO HAY REGISTROS");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void pregunta2() {
		
		System.out.println("----- PREGUNTA 2 -------");
		System.out.println("Introduce el Codigo del disco:");
		String codDisco = teclado.nextLine();
		System.out.println("Introduce el nombre del proveedor");
		String nombreProveedor = teclado.nextLine();

		stmtPrepare = null;
		try {
			stmtPrepare = con.prepareStatement("SELECT CodProveedor FROM proveedores WHERE Nombre = ?");
			stmtPrepare.setString(1, nombreProveedor);
			
			stmtPrepare.executeQuery();
			ResultSet rs=stmtPrepare.executeQuery();
			rs.next();
			String codProveedor=rs.getString(1);
			
			stmtPrepare = null;
			try{
				stmtPrepare = con.prepareStatement("UPDATE Discos SET CodProveedor=? WHERE CodDiscos=?");
				stmtPrepare.setString(1,codProveedor); 
				stmtPrepare.setString(2,codDisco); 
				stmtPrepare.executeUpdate();
				
				System.out.println("*Registro actualizado*");
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
		} catch (SQLException e) {
			System.out.println("El codDisco o el nombre del Proveedor no son validos");
		}
	}
	
	public void pregunta3() {
		
		System.out.println("-------PREGUNTA 3 --------");
		System.out.println("Introduce el nombre del Disco:");
		String nomDisco = teclado.nextLine();
		stmtPrepare = null;
		try {
			stmtPrepare = con.prepareStatement("SELECT CodDiscos FROM discos WHERE Nombre = ?");
			stmtPrepare.setString(1, nomDisco);
			
			stmtPrepare.executeQuery();
			ResultSet rs=stmtPrepare.executeQuery();
			rs.next();
			String codDisco = rs.getString(1);
			System.out.println(codDisco);
			
			System.out.println("Introduce el Codigo de Venta");
			String codVenta = teclado.nextLine();
			
			try {
				
				CallableStatement cst = con.prepareCall("{CALL pr_eliminarDiscoVenta(?,?,?)}");
				cst.setInt(1, Integer.parseInt(codDisco));
				cst.setInt(2, Integer.parseInt(codVenta));
				cst.registerOutParameter(3, java.sql.Types.INTEGER);
				cst.execute();
				
				System.out.println("Cantidad eliminada: " + cst.getInt(3));
				
			} catch (SQLException e){
				System.out.println("FALLO EN EL PROCEDIMIENTO");
			}
			
		} catch (SQLException e) {
			System.out.println("EL nombre del libro es incorrecto");
		}
	
	}
	
	public void pregunta4(){
		System.out.println("-------PREGUNTA 4 --------");
		System.out.println("Introduce el codigo del proveedor:");
		String codProv = teclado.nextLine();
		
		try{
			
			CallableStatement consulta = con.prepareCall("{ ? =call f_cuentadiscoproveedor ( ? )}");
            consulta.setString(2,codProv);
            consulta.registerOutParameter(1, java.sql.Types.INTEGER);
            consulta.execute();
			
            System.out.println("Cantidad: " + consulta.getInt(1));
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
