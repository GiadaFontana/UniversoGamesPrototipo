package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.entities.Commento;


public interface IDaoCommenti 
{ 
public List<Commento>commenti();
public Commento c(int id);
public void aggiungi(Commento c);
public void elimina(int id);
public void modifica(Commento n);

}
