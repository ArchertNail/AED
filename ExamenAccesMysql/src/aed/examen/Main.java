package aed.examen;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		Scanner teclado = new Scanner(System.in);

		int eleccion =0;
		do{
			System.out.println("Elige Conexión:");
			System.out.println("1)Mysql\n2)Acces");
			eleccion = teclado.nextInt();
			
			switch (eleccion) {
			case 1:
				con = Conexion.mysql();
				break;
			case 2:
				con = Conexion.acces();
				break;
			default:
				System.out.println("No ha elegido una de las opciones\n");
				break;
			}
		}while(eleccion >2);
		
		
		Consultas consulta = new Consultas(con);
		
		if(eleccion == 1){
			do{
				eleccion=0;
				System.out.println("\n¿Que desea hacer? \n1)Pregunta 1\n2)Pregunta 2\n3)Pregunta 3\n4)Pregunta 4\n5)Salir");
				eleccion = teclado.nextInt();
				
				switch (eleccion) {
				case 1:
					consulta.pregunta1();
					break;
				case 2:
					consulta.pregunta2();
					break;
				case 3:
					consulta.pregunta3();
					break;
				case 4:
					consulta.pregunta4();
					break;
				case 5:
					System.out.println("FINALIZADO!!");
					System.exit(0);
					break;
					
				default:
					System.out.println("No ha elegido una de las opciones\n");
					break;
				}
			}while(eleccion != 5);
		}
		else{
			do{
				eleccion=0;
				System.out.println("\n¿Que desea hacer? \n1)Pregunta 1\n2)Pregunta 2\n3)Salir");
				eleccion = teclado.nextInt();
				
				switch (eleccion) {
				case 1:
					consulta.pregunta1();
					break;
				case 2:
					consulta.pregunta2();
					break;
				case 3:
					System.out.println("FINALIZADO!!");
					System.exit(0);
					break;
					
				default:
					System.out.println("No ha elegido una de las opciones\n");
					break;
				}
			}while(eleccion != 3);
		}

	}

}
