package org.generation.italy.universoGames.controller;

import java.util.List;

import org.generation.italy.universoGames.auth.Utente;
import org.generation.italy.universoGames.dao.DaoCommentiRecensioneMySQL;
import org.generation.italy.universoGames.dao.IDaoCommentiRecensione;
import org.generation.italy.universoGames.entities.CommentoRecensione;
import org.generation.italy.universoGames.entities.Recensione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commenti-recensione")
public class CommentiRecensioniController {
	
	@Autowired
	private IDaoCommentiRecensione dao;
	
	@GetMapping("/{idRecensione}")
	public List<CommentoRecensione> commentiRecensioni(@PathVariable int idRecensione){
		return dao.commentiRecensione(idRecensione);
	}
	
	@GetMapping("/commento-id-{idCommento}")
	public CommentoRecensione commentoRecensione(@PathVariable int idCommento) {
		return dao.commentoRecensione(idCommento);
	}
	
	@GetMapping("/nomi-utenti-{idRecensione}")
	public List<String> utentiRecensioni(@PathVariable int idRecensione){
		return dao.nomiUtentiCommenti(idRecensione);
	}
	
	
	@PostMapping
	public void addCommentoRecensione(@RequestBody CommentoRecensione commento) {
		dao.addCommentoRecensione(commento);
	}
	
	@DeleteMapping("/{idCommento}")
	public void deleteCommentoRecensione(@PathVariable int idCommento) {
		dao.deleteCommentoRecensione(idCommento);
	}
	
	@PutMapping
	public void updateMapping(@RequestBody CommentoRecensione commento) {
		dao.updateCommentoRecensione(commento);
	}
	@DeleteMapping("/{idRecensione}")
    public void deleteCommentiRecensioni(@PathVariable int idRecensione) {
        dao.deleteCommentiRecensione(idRecensione);
    }
}
