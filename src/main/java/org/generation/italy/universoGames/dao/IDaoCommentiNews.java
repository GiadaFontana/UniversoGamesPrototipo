package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.entities.CommentoNews;

public interface IDaoCommentiNews {
	
	public List<CommentoNews> commentiNews(int idNews);
	public CommentoNews commentoNews(int id);
	public void addCommentoNews(CommentoNews commento);
	public void deleteCommentoNews(int id);
	public void updateCommentoNews(CommentoNews commento);
}
