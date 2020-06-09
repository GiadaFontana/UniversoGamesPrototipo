package org.generation.italy.universoGames.entities;

import org.generation.italy.universoGames.util.IMappable;

public class News implements IMappable {
	private int id;
	private String titolo;
	
	private String categoria;
	private String contenuto;
	private String dataPubblicazione;
	private String autore;
	
	public News(int id, String titolo, String categoria, String contenuto, String dataPubblicazione, String autore) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.categoria = categoria;
		this.contenuto = contenuto;
		this.dataPubblicazione = dataPubblicazione;
		this.autore = autore;
	}
	public News() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	public String getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(String dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
}
