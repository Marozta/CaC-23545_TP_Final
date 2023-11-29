package ar.com.codoacodo.entity;

import java.time.LocalDate;
// https://github.com/krloss86/23545-backend.git
// Atributos de la clase que se corresponden con los campos de la BD

public class Orador {
	private Long id;
	private String nombre;
	private String apellido;
	private String mail;
	private String tema;
	private LocalDate fechaAlta;
	
// Constructor de la clase sin id (para enviar orador a la BD)
	
	public Orador(String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.tema = tema;
		this.fechaAlta = fechaAlta;
		}

//Constructor de la clase con id (para traer orador de la BD)
	public Orador(Long id, String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.tema = tema;
		this.fechaAlta = fechaAlta;
		}
	
// Agregado de los getters y setters
	public void setNombre(String nombre) {
		if(nombre!= null) {
			this.nombre = nombre;
		} else {
			this.nombre ="N/D";
		}
		
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	// Agregado del metord toString
	@Override
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	

}
