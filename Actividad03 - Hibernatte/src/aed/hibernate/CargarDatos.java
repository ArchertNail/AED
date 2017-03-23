package aed.hibernate;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.type.IntegerType;

import aed.hibernate.bd.AutoresBD;
import aed.hibernate.bd.DepositoLegalBD;
import aed.hibernate.bd.EjemplaresBD;
import aed.hibernate.bd.LibrosBD;
import aed.hibernate.modelo.Autores;
import aed.hibernate.modelo.DepositoLegal;
import aed.hibernate.modelo.Ejemplares;
import aed.hibernate.modelo.Libro;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;


public class CargarDatos {
	
	
	
	public static Session sesion;
	
	
	/*                __________________________
	 * 				 |						    |
	 *-------------- |     INTERFAZ PRINCIPAL	|------------------
	 * 				 |__________________________|
	 * 		
	 */
	
	public static void libros(ListProperty<Libro> listaLibro){
		
		listaLibro.clear();
		
		sesion = HibernateUtil.getSessionFactory().openSession();
		
		Query cons = sesion.createQuery("from LibrosBD lib left join fetch lib.ejemplares left join fetch lib.autores");
		
		List<LibrosBD> librosLista = cons.getResultList();
		
		Integer idLibro=0;
		for(LibrosBD l : librosLista){
			if ( idLibro!= l.getCodLibro()) {//con esto consigo que no se repita persona y sus libros por cada libro que tenga
				
				Libro libro = new Libro();
				libro.setIdLibro(String.valueOf(l.getCodLibro()));
				libro.setNombreLibro(l.getNombreLibro());
				libro.setIsbn(l.getIsbn());
				libro.setFechaNacimiento(l.getFechaIntro());
				try{
					DepositoLegal dep = new DepositoLegal();
					dep.setCodLibro(String.valueOf(l.getCodLibro()));
					dep.setDeposito(l.getDepositoLegal().getDepositoLegal());
					libro.setDeposito(dep);
				}catch(Exception e){
					System.out.println("no hay deposito en este libro");
				}
				
				Set<EjemplaresBD> listaeEjemplares = l.getEjemplares();
				for (EjemplaresBD e: listaeEjemplares){
					
					Ejemplares ejemplar = new Ejemplares();
					ejemplar.setCodEjemplar(String.valueOf(e.getCodEjemplar()));
					ejemplar.setCodLibro(String.valueOf(e.getCodLibro()));
					ejemplar.setImporte(String.valueOf(e.getImporte()));
					ejemplar.setTipoMoneda(e.getTipoMoneda());
					libro.ejemplaresProperty().add(ejemplar);
					System.out.println("Importe : " + e.getImporte());
				}
				Set<AutoresBD> listaAutores = l.getAutores();
				for (AutoresBD a: listaAutores){
					Autores autor = new Autores();
					autor.setCodAutor(a.getCodAutor());
					autor.setNombreAutor(a.getNombreAutor());
					libro.autoresProperty().add(autor);
					System.out.println("autor : " + a.getNombreAutor());
				}
				
				listaLibro.add(libro);
				idLibro=(int) l.getCodLibro();
			}
		}
		sesion.close();
	}
	
	
	public static ArrayList<Autores> listaAutores(){
		
		ArrayList<Autores>listaAutores = new ArrayList<>();
		
		sesion = HibernateUtil.getSessionFactory().openSession();
		Query cons = sesion.createQuery("from AutoresBD");
		
		List<AutoresBD> autoresLista = cons.getResultList();
		for (AutoresBD a : autoresLista) {
			Autores autor = new Autores();
			autor.setCodAutor(a.getCodAutor());
			autor.setNombreAutor(a.getNombreAutor());
			listaAutores.add(autor);
		}
		
		sesion.close();
		
		return listaAutores;
	}

	public static void insertarLibros(String nomLibro, String isbn, LocalDate fecha,String deposito, ListProperty<Autores> listaAutoresAnadir) {
		
		sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
	        
	        LibrosBD lib = new LibrosBD();
	        lib.setNombreLibro(nomLibro);
	        lib.setIsbn(isbn);
	        lib.setFechaIntro(fecha);
	      
	        DepositoLegalBD depositoLegal = new DepositoLegalBD();
	        depositoLegal.setLibro(lib);
	        depositoLegal.setDepositoLegal(deposito);
	        
	        lib.setDepositoLegal(depositoLegal);
	        
	        
	        for(int i=0; i<listaAutoresAnadir.size();i++){

	        	AutoresBD a = new AutoresBD();
	        	a.setCodAutor(listaAutoresAnadir.get(i).getCodAutor());
	        	a.setNombreAutor(listaAutoresAnadir.get(i).getNombreAutor());
				lib.getAutores().add(a);
	        }
	        //Save the employee in database
	        sesion.save(lib);
	 
	        //Commit the transaction
	        sesion.getTransaction().commit();
	}


	public static void actualizarLibro(Libro libroNewVersion) {
		sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		
		LibrosBD l = new LibrosBD();
		l.setCodLibro(Integer.parseInt(libroNewVersion.getIdLibro()));
		l.setNombreLibro(libroNewVersion.getNombreLibro());
		l.setIsbn(libroNewVersion.getIsbn());
		l.setFechaIntro(libroNewVersion.getFechaNacimiento());
		
		DepositoLegalBD d = new DepositoLegalBD();
		d.setLibro(l);
		d.setDepositoLegal(libroNewVersion.getDeposito().getDeposito());
		l.setDepositoLegal(d);
		
		for(int i=0; i<libroNewVersion.autoresProperty().size();i++){
	        AutoresBD a = new AutoresBD();
	        a.setCodAutor(libroNewVersion.getAutores().get(i).getCodAutor());
	        a.setNombreAutor(libroNewVersion.getAutores().get(i).getNombreAutor());
			l.getAutores().add(a);
	    }

		sesion.update(l);
		
		sesion.getTransaction().commit();
	}


	public static void borrarLibro(Libro libroSelect) {
		
		sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		
		LibrosBD l = new LibrosBD();
		l.setCodLibro(Integer.parseInt(libroSelect.getIdLibro()));
		l.setNombreLibro(libroSelect.getNombreLibro());
		l.setIsbn(libroSelect.getIsbn());
		l.setFechaIntro(libroSelect.getFechaNacimiento());
		
		DepositoLegalBD d = new DepositoLegalBD();
		d.setLibro(l);
		d.setDepositoLegal(libroSelect.getDeposito().getDeposito());
		l.setDepositoLegal(d);
		
		for(int i=0; i<libroSelect.autoresProperty().size();i++){
	        AutoresBD a = new AutoresBD();
	        a.setCodAutor(libroSelect.getAutores().get(i).getCodAutor());
	        a.setNombreAutor(libroSelect.getAutores().get(i).getNombreAutor());
			l.getAutores().add(a);
	    }
		
		for(int i=0; i<libroSelect.ejemplaresProperty().size();i++){
			EjemplaresBD e = new EjemplaresBD();
			e.setCodEjemplar(Integer.parseInt(libroSelect.getEjemplares().get(i).getCodEjemplar()));
			e.setCodLibro(l);
			e.setImporte(Integer.parseInt(libroSelect.getEjemplares().get(i).getImporte()));
			e.setTipoMoneda(libroSelect.getEjemplares().get(i).getTipoMoneda());
			l.getEjemplares().add(e);
		}
		
		sesion.delete(l);
		sesion.getTransaction().commit();
		
	}
}
