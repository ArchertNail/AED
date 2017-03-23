package aed.examenhibernate.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.hibernate.Session;
import aed.examenhibernate.Avion;
import aed.examenhibernate.DatosAvion;
import aed.examenhibernate.Hangares;
import aed.examenhibernate.Parte;

public class Consultas {

	public static Session sesion;
	
	
	public static void Eliminar(){
		Scanner teclado = new Scanner(System.in);
		
		try{
			System.out.println("Introduce la matricula del avion");
			String matricula = teclado.nextLine();
			
			System.out.println("Introduce el codigo del hangar");
			int codHangar = teclado.nextInt();
			
			System.out.println("Introduce la fecha del parte(YYYY-mm-dd)");
			LocalDate fecha = LocalDate.parse(teclado.next());
	
			
			Query query = sesion.createQuery("FROM Avion a WHERE a.matricula=:matricula");
			query.setParameter("matricula", matricula);
			
			Avion avion = (Avion) query.getResultList().get(0);
			
			Hangares hangar = (Hangares)sesion.get(Hangares.class, codHangar);
			
			Parte parte = new Parte();
			parte.setAvion(avion);
			parte.setHangar(hangar);
			parte.setFecha(fecha);
		
		
			sesion.beginTransaction();
	
		
			sesion.delete(parte);
			System.out.println("PARTE BORRADO");
			
			sesion.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("PARTE NO BORRRADO");
		}
		
	
		
	}
	
	public static void Modificar(){
		Scanner teclado = new Scanner(System.in);
		
		try{
			System.out.println("Introduce la matricula del avion");
			String matricula = teclado.nextLine();
			
			System.out.println("Introduce el codigo del hangar");
			int codHangar = teclado.nextInt();
			
			System.out.println("Introduce la fecha del parte(YYYY-mm-dd)");
			LocalDate fecha = LocalDate.parse(teclado.next());
	
			
			Query query = sesion.createQuery("FROM Avion a WHERE a.matricula=:matricula");
			query.setParameter("matricula", matricula);
			
			Avion avion = (Avion) query.getResultList().get(0);
	
			Hangares hangar = (Hangares)sesion.get(Hangares.class, codHangar);
		
			Parte parte = new Parte();
			parte.setAvion(avion);
			parte.setHangar(hangar);
			parte.setFecha(fecha);
			
			try{
				System.out.println("Introduce un nuevo Codigo de Hangar:");
				int nuevoCodHangar = teclado.nextInt();
				Hangares hangarNuevo = (Hangares)sesion.get(Hangares.class, nuevoCodHangar);	
				
			
				Parte nuevoParte = new Parte();
				nuevoParte.setAvion(avion);
				nuevoParte.setHangar(hangarNuevo);
				nuevoParte.setFecha(fecha);
			
				
				sesion.beginTransaction();
				sesion.delete(parte);
				sesion.save(nuevoParte);
				
				
				System.out.println("Hangar del parte ACTUALIZADO!!!");
				sesion.getTransaction().commit();
			
			}catch (Exception e) {
				System.out.println("HANGAR INEXISTENTE");
			}
			
			
			
		}catch (Exception e) {
			System.out.println("Hangar del parte NO Actualizado");
		}
	}
	
	
	public static void Mostrar(){
		
		sesion.beginTransaction();
		Query consulta = sesion.createQuery("SELECT a.compania, h.ubicacion, p.Fecha "
										  + "FROM Avion a, Hangares h, Parte p "
										  + "WHERE a.matricula = p.avion "
										  	+ "AND h.codHangar = p.hangar");
		
		
		List<Object[]> l = consulta.getResultList();
		
		for (Object[] objects : l) {
			System.out.println("ITEM --> " + objects[0] + "||" + objects[1] + "||" + objects[2]);
		}
		
		sesion.getTransaction().commit();
	}
	
	public static void Insertar(){
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce la matricula del avion");
		String matricula = teclado.nextLine();
		
		System.out.println("Introduce su compañia");
		String compania = teclado.next();
		
		System.out.println("¿Desea introducir datos al Avion? s/n");
		String sn = teclado.next();
		
		Avion avion = new Avion();
		avion.setMatricula(matricula);
		avion.setCompania(compania);
		
		if(sn.equals("s")){
			
			System.out.println("¿Horas de vuelvo que tendra el avion?");
			double horas = teclado.nextInt();
			DatosAvion datos = new DatosAvion();
			datos.setAvion(avion);
			datos.setHorasVuelvoAvion(horas);
			avion.setDatosAvion(datos);
		}
		
		sesion.beginTransaction();
		sesion.save(avion);
		System.out.println("NUEVO AVION INSERTADO");
		sesion.getTransaction().commit();
	}
	
	public static void MostrarAviones(){
		
		Query query = sesion.createQuery("FROM Avion");
		List<Avion> listaAviones = query.getResultList();
		for (Avion a : listaAviones) {
			if(a.getDatosAvion()==null){
				System.out.println(a.getMatricula() + " || " + a.getCompania());
			}
			else{
				System.out.println(a.getMatricula() + " || " + a.getCompania() + " || " + a.getDatosAvion().getHorasVuelvoAvion());
			}
		}
		
	}
}
