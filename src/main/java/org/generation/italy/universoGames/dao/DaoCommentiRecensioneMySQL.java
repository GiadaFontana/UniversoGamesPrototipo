package org.generation.italy.universoGames.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.generation.italy.universoGames.auth.Utente;
import org.generation.italy.universoGames.entities.CommentoRecensione;
import org.generation.italy.universoGames.util.BasicDao;
import org.generation.italy.universoGames.util.IMappable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoCommentiRecensioneMySQL extends BasicDao implements IDaoCommentiRecensione{

	
	
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
	
	private static final String COMMENTI_RECENSIONE_SELECT_ID = "SELECT * FROM commentirecensioni WHERE "
										+ "id = ?";
	private static final String COMMENTI_RECENSIONE_DELETEALL_IDRECENSIONE = 
			"DELETE * FROM commentirecensioni WHERE idrecensione = ?";

	public DaoCommentiRecensioneMySQL  (
			@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user, 
			@Value("${db.psw}") String password
			)  {
		super(dbAddress, user, password);
	}

	private CommentoRecensione commentoRecensionefromMap(Map<String, String> map) {
		return new CommentoRecensione(Integer.parseInt(map.get("id")),
									  map.get("commento"),
									  Integer.parseInt(map.get("idutente")),
									  Integer.parseInt(map.get("idrecensione")));
	}
	
	@Override
	public List<CommentoRecensione> commentiRecensione(int idRecensione) {
		List<CommentoRecensione> ris = new ArrayList<>();
		List<Map<String, String>> maps = getAll(COMMENTI_SELECT_INNER_JOIN_UTENTI, idRecensione);
		
		for (Map<String, String> map : maps) {
			ris.add(commentoRecensionefromMap(map));
		}
		
		return ris;
	}
	
	@Override
	public List<String> nomiUtentiCommenti(int idRecensione){
		List<String> ris = new ArrayList<>();
		List<Map<String, String>> maps = getAll(COMMENTI_SELECT_INNER_JOIN_UTENTI, idRecensione);
		
		for (Map<String, String> map : maps) {
			ris.add(map.get("username"));
		}
		return ris;
	}

	@Override
	public CommentoRecensione commentoRecensione(int id) {
		return IMappable.fromMap(CommentoRecensione.class, getOne(COMMENTI_RECENSIONE_SELECT_ID, id));
	}

	@Override
	public void addCommentoRecensione(CommentoRecensione commento) {
		execute(COMMENTO_RECENSIONE_ADD, 
				commento.getCommento(),
				commento.getIdUtente(),
				commento.getIdRecensione());
	}

	@Override
	public void deleteCommentoRecensione(int idCommento) {
		execute(COMMENTO_RECENSIONE_DELETE, idCommento);
	}

	@Override
	public void updateCommentoRecensione(CommentoRecensione commento) {
		execute(COMMENTI_RECENSIONE_UPDATE, 
				commento.getCommento(),
				commento.getId());
	}

	@Override
	public void deleteCommentiRecensione(int idRecensione) {
		 execute(COMMENTI_RECENSIONE_DELETEALL_IDRECENSIONE, idRecensione);
		
	}

}
