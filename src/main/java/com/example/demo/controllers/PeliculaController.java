package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pelicula;
import com.example.demo.model.Genero;
import com.example.demo.service.PeliculaService;
import com.example.demo.service.GeneroService;

@Controller
@RequestMapping("/pelicula")
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;

	@Autowired
	private GeneroService generoService;

	@GetMapping("/peliculas")
	public String getAllProduct(Model model) {

		List<Pelicula> listPeliculas = peliculaService.getAllProducts();

		model.addAttribute("peliculas", listPeliculas);

		return "peliculaList";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "peliculaRegister";
	}

	@PostMapping("/register")
	public String createProduct(@RequestParam("nombre") String nombre, @RequestParam("director") String director,
			@RequestParam("fechaEstreno") String fechaEstreno, @RequestParam("id") Long id, Model model) {

		Pelicula pelicula = new Pelicula();
		pelicula.nombre = nombre;
		pelicula.director = director;
		pelicula.fechaEstreno = fechaEstreno;

		Genero genero = generoService.getGeneroById(id);

		pelicula.genero = genero;

		peliculaService.createPelicula(pelicula);

		model.addAttribute("peliculas", peliculaService.getAllProducts());
		model.addAttribute("generos", generoService.getAllGeneros());

		return "peliculaList";
	}

	@GetMapping("/edit/{idPelicula}")
	public String edit(@PathVariable Long idPelicula, Model model) {

		Pelicula pelicula = peliculaService.getPeliculaByID(idPelicula);

		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generoService.getAllGeneros());

		return "peliculaEdit";
	}

	@PostMapping("/edit")
	public String createProduct(@RequestParam("idPelicula") Long idPelicula, @RequestParam("nombre") String nombre,
			@RequestParam("director") String director, @RequestParam("fecha_estreno") String fechaEstreno,
			@RequestParam("idGenero") Long idGenero, Model model) {

		Pelicula pelicula = peliculaService.getPeliculaByID(idPelicula);
		pelicula.nombre = nombre;
		pelicula.director = director;
		pelicula.fechaEstreno = fechaEstreno;

		Genero genero = generoService.getGeneroById(idGenero);

		pelicula.genero = genero;

		peliculaService.createPelicula(pelicula);

		model.addAttribute("peliculas", peliculaService.getAllProducts());
		model.addAttribute("generos", generoService.getAllGeneros());

		return "peliculaList";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		peliculaService.deleteProduct(id);

		model.addAttribute("peliculas", peliculaService.getAllProducts());
		model.addAttribute("generos", generoService.getAllGeneros());

		return "peliculaList";
	}

}
