package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.entities.News;

public interface IDaoNews {
	
	List<News>news();

	News n(int id);

	void aggiungi(News n);

	void elimina(int id);

	void modifica(News n);
}


