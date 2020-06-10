package org.generation.italy.universoGames.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.universoGames.entities.Commento;
import org.generation.italy.universoGames.util.BasicDao;
import org.generation.italy.universoGames.util.IMappable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCommenti extends BasicDao implements IDaoCommenti{

	private static final String UPDATE_COMMENTI = "UPDATE commenti SET commento= ?, idUtente = ? WHERE id = ?;";
	private static final String DELETE_FROM_COMMENTI = "DELETE FROM commenti WHERE id = ?;";
	private static final String INSERT_INTO_COMMENTI = "INSERT INTO commenti (commento, idUtente, recensione,) VALUES (?,?);";
	private static final String SELECT_FROM_COMMENTI_WHERE_ID = "SELECT * FROM COMMENTI WHERE id=?;";
	private static final String SELECT_FROM_COMMENTI = "SELECT *FROM COMMENTI";

	public DaoCommenti(@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user, 
			@Value("${db.psw}") String password
			) {
		super (dbAddress, user, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Commento> commenti() {
List<Commento>ris = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll(SELECT_FROM_COMMENTI);
		for (Map<String, String> map : maps) {
			ris.add(IMappable.fromMap(Commento.class, map));
		}
		
		return ris;
		
	}

	@Override
	public Commento c(int id) {
		return IMappable.fromMap(Commento.class, getOne(SELECT_FROM_COMMENTI_WHERE_ID, id));
		
	}

	@Override
	public void aggiungi(Commento c) {
		execute(INSERT_INTO_COMMENTI,
				c.getCommento(),
				c.getIdUtente());
		
	}

	@Override
	public void elimina(int id) {
		execute(DELETE_FROM_COMMENTI,id);
		
	}

	@Override
	public void modifica(Commento n, int id) {
		execute(UPDATE_COMMENTI, 
				n.getCommento(),
				n.getIdUtente()
				);
	
		
	}  

}
