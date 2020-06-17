package org.generation.italy.universoGames.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.universoGames.entities.CommentoNews;
import org.generation.italy.universoGames.util.BasicDao;
import org.generation.italy.universoGames.util.IMappable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
@Repository
public class DaoCommentiNewsMySQL extends BasicDao implements IDaoCommentiNews{
	
	private static final String COMMENTI_SELECT_INNER_JOIN_UTENTI = 
			"SELECT progettofinale.commentinews.*, demosecurity.utente.username "
			+ "FROM progettofinale.commentirecensioni INNER JOIN "
			+ "demosecurity.utente ON idutente = utente.id "
			+ "WHERE idnews = ?;";
	
	private static final String COMMENTO_NEWS_ADD = "INSERT INTO commentinews (commento, "
			+ "idutente, idnews) VALUES (?,?,?)";
public static final String COMMENTO_NEWS_DELETE = "DELETE FROM commentinews WHERE"
			+ " id = ?";
private static final String COMMENTI_NEWS_UPDATE = "UPDATE commentinews SET commento = ?"
			+ " WHERE id = ?";

private static final String COMMENTI_NEWS_SELECT_ID = "SELECT * FROM commentinews WHERE "
		+ "id = ?";
private static final String COMMENTI_NEWS_DELETEALL_IDNEWS = 
"DELETE * FROM commentinews WHERE idnews = ?";




public DaoCommentiNewsMySQL(@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user, 
			@Value("${db.psw}") String password) {
		super(dbAddress, user, password);
		// TODO Auto-generated constructor stub
	}
   
	
	private CommentoNews commentoNewsfromMap(Map<String, String> map) {
		return new CommentoNews(Integer.parseInt(map.get("id")),
									  map.get("commento"),
									  Integer.parseInt(map.get("idutente")),
									  Integer.parseInt(map.get("idnews")));
	}
	@Override
	public List<CommentoNews> commentiNews(int idNews) {
		List<CommentoNews> ris = new ArrayList<>();
		List<Map<String, String>> maps = getAll(COMMENTI_SELECT_INNER_JOIN_UTENTI, idNews);
		
		for (Map<String, String> map : maps) {
			ris.add(commentoNewsfromMap(map));
		}
		
		return ris;
	}
	
	
	

	@Override
	public CommentoNews commentoNews(int id) {
		return IMappable.fromMap(CommentoNews.class, getOne(COMMENTI_NEWS_SELECT_ID, id));
	}
	
	

	@Override
	public void addCommentoNews(CommentoNews commento) {
		execute(COMMENTO_NEWS_ADD, 
				commento.getCommento(),
				commento.getIdUtente(),
				commento.getIdNews());
		
	}

	@Override
	public void deleteCommentoNews(int idCommento) {
		execute(COMMENTO_NEWS_DELETE, idCommento);
		
	}

	@Override
	public void updateCommentoNews(CommentoNews commento) {
		execute(COMMENTI_NEWS_UPDATE, 
				commento.getCommento(),
				commento.getId());
		
	}


	@Override
	public List<String> nomiUtentiCommenti(int idNews) {
		List<String> ris = new ArrayList<>();
		List<Map<String, String>> maps = getAll(COMMENTI_SELECT_INNER_JOIN_UTENTI, idNews);
		
		for (Map<String, String> map : maps) {
			ris.add(map.get("username"));
		}
		return ris;
	}
	


	@Override
	public void deleteCommentiNews(int idNews) {
		 execute(COMMENTI_NEWS_DELETEALL_IDNEWS, idNews);
		
	}

}
