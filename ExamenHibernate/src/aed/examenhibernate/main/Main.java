package aed.examenhibernate.main;

import java.util.Scanner;

import org.hibernate.Session;

import aed.examenhibernate.HibernateUtil;



public class Main {

	public static void main(String[] args) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Consultas.sesion = sesion;
		
		Scanner teclado = new Scanner(System.in);
		int eleccion =0;
		
		do{
			eleccion=0;
			System.out.println("\n¿Que desea hacer? \n1)Eliminar\n2)Modificar\n3)Mostrar\n4)Insertar\n5)Mostrar Aviones\n6)Salir");
			eleccion = teclado.nextInt();
			
			switch (eleccion) {
			case 1:
				Consultas.Eliminar();
				break;
			case 2:
				Consultas.Modificar();
				break;
			case 3:
				Consultas.Mostrar();
				break;
			case 4:
				Consultas.Insertar();
				break;
			case 5:
				Consultas.MostrarAviones();
				break;
			case 6:
				System.out.println("FINALIZADO!!");
				sesion.close();
				System.exit(0);
				break;
				
			default:
				System.out.println("No ha elegido una de las opciones\n");
				break;
			}
		}while(eleccion != 6);
	

	}

}
