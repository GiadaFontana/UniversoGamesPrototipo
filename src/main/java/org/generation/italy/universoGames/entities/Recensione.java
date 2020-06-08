package org.generation.italy.universoGames.entities;



import org.generation.italy.universoGames.util.IMappable;

public class Recensione implements IMappable {
	
	private int id;
	private String titoloVideogioco;
	private String dataRecensione;
	private String recensione;
	private double punteggio;
	private String recensore;
	
	public Recensione() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recensione(int id, String titoloVideogioco, String dataRecensione, String recensione, double punteggio,
			String recensore) {
		super();
		this.id = id;
		this.titoloVideogioco = titoloVideogioco;
		this.dataRecensione = dataRecensione;
		this.recensione = recensione;
		this.punteggio = punteggio;
		this.recensore = recensore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitoloVideogioco() {
		return titoloVideogioco;
	}

	public void setTitoloVideogioco(String titoloVideogioco) {
		this.titoloVideogioco = titoloVideogioco;
	}

	public String getDataRecensione() {
		return dataRecensione;
	}

	public void setDataRecensione(String dataRecensione) {
		this.dataRecensione = dataRecensione;
	}

	public String getRecensione() {
		return recensione;
	}

	public void setRecensione(String recensione) {
		this.recensione = recensione;
	}

	public double getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(double punteggio) {
		this.punteggio = punteggio;
	}

	public String getRecensore() {
		return recensore;
	}

	public void setRecensore(String recensore) {
		this.recensore = recensore;
	}
	
	
}
