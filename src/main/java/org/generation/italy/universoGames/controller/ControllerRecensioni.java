package org.generation.italy.universoGames.controller;

import java.util.List;

import org.generation.italy.universoGames.dao.IDaoRecensioni;
import org.generation.italy.universoGames.entities.Recensione;
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
@RequestMapping("/recensioni")
public class ControllerRecensioni {

	@Autowired
	public IDaoRecensioni dao;
	
	@GetMapping
	public List<Recensione> recensioni(){
		return dao.recensioni();
	}
	
	@GetMapping("/{id}")
	public Recensione recensione(@PathVariable int id) {
		return dao.recensione(id);
	}
	
	@PostMapping
	public void addRecensione(@RequestBody Recensione recensione) {
		dao.addRecensione(recensione);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMapping(@PathVariable int id) {
		dao.deleteRecensione(id);
	}
	
	@PutMapping
	public void updateMapping(@RequestBody Recensione recensione) {
		dao.updateRecensione(recensione);
	}
	@DeleteMapping("/{idRecensione}")
    public void deleteCommentiRecensioni(@PathVariable int idRecensione) {
        dao.deleteCommentiRecensione(idRecensione);
    }
}
