package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.entities.CommentoNews;

public interface IDaoCommentiNews {
	
	public List<CommentoNews> commentiNews(int idNews);
	public List<String> nomiUtentiCommenti(int idNews);
	public CommentoNews commentoNews(int id);
	public void addCommentoNews(CommentoNews commento);
	public void deleteCommentoNews(int idCommento);
	public void updateCommentoNews(CommentoNews commento);
	public void deleteCommentiNews(int idNews);
}
