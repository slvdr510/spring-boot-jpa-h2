package com.fpdual2022.springjpah2.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APE1")
	private String primerApellido;

	@Column(name = "APE2")
	private String segundoApellido;

	@Column(name = "EDAD")
	private Integer edad;

	@Column(name = "MAYOREDAD")
	private boolean mayorDeEdad;

	public Usuario(String nombre, String primerApellido, String segundoApellido, Integer edad, Boolean mayorDeEdad) {
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.edad = edad;
		this.mayorDeEdad = mayorDeEdad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public boolean isMayorDeEdad() {
		return mayorDeEdad;
	}

	public void setMayorDeEdad(boolean mayorDeEdad) {
		this.mayorDeEdad = mayorDeEdad;
	}

}