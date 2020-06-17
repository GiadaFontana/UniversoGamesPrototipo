package org.generation.italy.universoGames.dao;

import java.util.List;

import org.generation.italy.universoGames.entities.CommentoNews;
import org.generation.italy.universoGames.util.BasicDao;
import org.springframework.beans.factory.annotation.Value;

public class DaoCommentiNewsMySQL extends BasicDao implements IDaoCommentiNews{
	
	private static final String COMMENTO_RECENSIONE_ADD = "INSERT INTO commentirecensioni (commento, "
			+ "idutente, idrecensione) VALUES (?,?,?)";
public static final String COMMENTO_RECENSIONE_DELETE = "DELETE FROM commentirecensioni WHERE"
			+ " id = ?";
private static final String COMMENTI_RECENSIONE_UPDATE = "UPDATE commentirecensioni SET commento = ?"
			+ " WHERE id = ?";
private static final String COMMENTI_SELECT_INNER_JOIN_UTENTI = 
"SELECT progettofinale.commentirecensioni.*, demosecuritytestluca.utente.username "
+ "FROM progettofinale.commentirecensioni INNER JOIN "
+ "demosecuritytestluca.utente ON idutente = utente.id "
+ "WHERE idrecensione = ?;";

	public DaoCommentiNewsMySQL(@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user, 
			@Value("${db.psw}") String password) {
		super(dbAddress, user, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CommentoNews> commentiNews(int idNews) {
		
		return null;
	}

	@Override
	public CommentoNews commentoNews(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCommentoNews(CommentoNews commento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommentoNews(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCommentoNews(CommentoNews commento) {
		// TODO Auto-generated method stub
		
	}

}
