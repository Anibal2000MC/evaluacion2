package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepository;
	
	public List<Pelicula> getAllProducts() {
		return peliculaRepository.findAll();
	}
	
	
	public Pelicula createPelicula(Pelicula pelicula) {
		return peliculaRepository.save(pelicula);
	}
	
	public Pelicula getPeliculaByID(Long idPelicula) {
		return peliculaRepository.findById(idPelicula).orElse(null);
	}
	
	public void deleteProduct(Long id) {
		peliculaRepository.deleteById(id);
	}
}