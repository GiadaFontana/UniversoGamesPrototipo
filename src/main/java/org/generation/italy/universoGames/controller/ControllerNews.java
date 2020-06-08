package org.generation.italy.universoGames.controller;

import java.util.List;

import org.generation.italy.universoGames.dao.IDaoNews;
import org.generation.italy.universoGames.entities.News;
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
@RequestMapping("/news")
public class ControllerNews {
	@Autowired
	private IDaoNews dao;
	 
	@GetMapping
	public List<News>news() {
	return dao.news();
	}
	
	@GetMapping("/{id}") 
	public News notizia(@PathVariable int id) {
		return dao.n(id);
	}
	
	@PostMapping
	public void aggiungi(@RequestBody News n) {
		dao.aggiungi(n);
	}
	
	@DeleteMapping("/{id}")
	public void elimina(@PathVariable int id) {
		dao.elimina(id);
	}
	
	@PutMapping("/{id}")
	public void modifica(@RequestBody News n, @PathVariable int id) {
		dao.modifica(n, id);
	}
	
}
