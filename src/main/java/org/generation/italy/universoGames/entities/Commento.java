package org.generation.italy.universoGames.entities;

import org.generation.italy.universoGames.util.IMappable;

public class Commento implements IMappable
{
  private int id;
  private String commento;
  private int idUtente;
  
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCommento() {
	return commento;
}
public void setCommento(String commento) {
	this.commento = commento;
}
public int getIdUtente() {
	return idUtente;
}
public void setIdUtente(int idUtente) {
	this.idUtente = idUtente;
}
public Commento(int id, String commento, int idUtente) {
	super();
	this.id = id;
	this.commento = commento;
	this.idUtente = idUtente;
}
  
  
}
