package org.generation.italy.universoGames.controller;

import java.util.List;

import org.generation.italy.universoGames.dao.IDaoCommentiNews;
import org.generation.italy.universoGames.entities.CommentoNews;
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
@RequestMapping("/commenti-news")
public class CommentiNewsController {
	@Autowired
	private IDaoCommentiNews dao;
	
	@GetMapping("/{idNews}")
	public List<CommentoNews> commentiNews(@PathVariable int idNews){
		return dao.commentiNews(idNews);
	}
	@GetMapping("/commento-id-{idCommento}")
	public CommentoNews commentoNews(@PathVariable int idNews) {
		return dao.commentoNews(idNews);
	}
	@PostMapping
	public void addCommentoNews(@RequestBody CommentoNews commento) {
		dao.addCommentoNews(commento);
	}
	@DeleteMapping("/{idCommento}")
	public void deleteCommentoNews(@PathVariable int idCommento) {
		dao.deleteCommentoNews(idCommento);
	}
	
	@PutMapping
	public void updateMapping(@RequestBody CommentoNews commento) {
		dao.updateCommentoNews(commento);
	}
	@GetMapping("/nomi-utenti-{idNews}")
	public List<String> utentiNews(@PathVariable int idNews){
		return dao.nomiUtentiCommenti(idNews);
	}
	@DeleteMapping("/{idNews}")
    public void deleteCommentiNews(@PathVariable int idNews) {
        dao.deleteCommentiNews(idNews);
    }

}
