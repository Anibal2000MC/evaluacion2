package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idPelicula;

	@Column(name = "nombre", nullable = false)
	public String nombre;

	@Column(name = "director", nullable = false)
	public String director;

	@Column(name = "fechaEstreno", nullable = false)
	public String fechaEstreno;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	public Genero genero;

}
