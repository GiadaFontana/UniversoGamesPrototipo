package org.generation.italy.universoGames.entities;

public class CommentoRecensione extends Commento{

	private int idRecensione;

	public CommentoRecensione() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentoRecensione(int id, String commento, int idUtente, int idRecensione) {
		super(id, commento, idUtente);
		this.idRecensione = idRecensione;
	}

	public int getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(int idRecensione) {
		this.idRecensione = idRecensione;
	}

	@Override
	public String toString() {
		return super.toString() + "CommentoRecensione [idRecensione=" + idRecensione + "]";
	}

	
	

	
}

