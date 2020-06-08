package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.entities.Recensione;

public interface IDaoRecensioni {

	public List<Recensione> recensioni();
	public Recensione recensione (int id);
	public void addRecensione(Recensione recensione);
	public void deleteRecensione(int id);
	public void updateRecensione(Recensione recensione);
}
