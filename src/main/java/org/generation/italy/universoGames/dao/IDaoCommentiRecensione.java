package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.auth.Utente;
import org.generation.italy.universoGames.entities.CommentoRecensione;

public interface IDaoCommentiRecensione {
	
	public List<CommentoRecensione> commentiRecensione(int idRecensione);
	public List<String> nomiUtentiCommenti(int idRecensione);
	public CommentoRecensione commentoRecensione(int id);
	public void addCommentoRecensione(CommentoRecensione commento);
	public void deleteCommentoRecensione(int idCommento);
	public void updateCommentoRecensione(CommentoRecensione commento);
	public void deleteCommentiRecensione(int idRecensione);
}
