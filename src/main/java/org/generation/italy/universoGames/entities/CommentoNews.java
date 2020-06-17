package org.generation.italy.universoGames.entities;

public class CommentoNews extends Commento {
	
	private int idNews;

	public CommentoNews() {
		super();
	}

	public CommentoNews(int id, String commento, int idUtente, int idNews) {
		super(id, commento, idUtente);
		this.idNews = idNews;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}
	
	
}
