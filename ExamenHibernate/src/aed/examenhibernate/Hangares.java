package aed.examenhibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Hangares")
public class Hangares implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codHangar;
	
	@Column(name="ubicacion", length = 30)
	private String ubicacion;
}
