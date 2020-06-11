package org.generation.italy.universoGames.controller;

import java.util.List;

import org.generation.italy.universoGames.dao.IDaoCommenti;
import org.generation.italy.universoGames.entities.Commento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commenti")
public class ControllerCommenti {
	@Autowired
	private IDaoCommenti dao;
	
	@GetMapping
	public List<Commento> commenti(){
		return dao.commenti();
	}
	
	@GetMapping("/{id}")
	public Commento c(@PathVariable int id) {
		return dao.c(id);
	}
	
	@PostMapping
	public void aggiungi(@RequestBody Commento c) {
		dao.aggiungi(c);
	}
	
	@DeleteMapping("/{id}")
	public void elimina(@PathVariable int id) {
		dao.elimina(id);
	}
	
	@PutMapping()
	public void modifica(@RequestBody Commento c) {
		dao.modifica(c);
	
	}
	
	

}
