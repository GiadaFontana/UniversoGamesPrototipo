package org.generation.italy.universoGames.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.universoGames.entities.Recensione;
import org.generation.italy.universoGames.util.BasicDao;
import org.generation.italy.universoGames.util.IMappable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class DaoRecensioniMySQL extends BasicDao implements IDaoRecensioni {

	private static final String RECENSIONI_SELECT = "SELECT * FROM recensioni;";
	private static final String RECENSIONI_SELECT_ID = "SELECT * FROM recensioni WHERE id = ?;";
	private static final String RECENSIONI_ADD = "INSERT INTO recensioni (titolovideogioco, datarecensione, recensione, "
								+ "punteggio, recensore) VALUES (?,?,?,?,?);";
	private static final String RECENSIONI_DELETE = "DELETE FROM recensioni WHERE id = ?;";
	private static final String RECENSIONI_UPDATE = "UPDATE recensioni SET titolovideogioco = ?, datarecensione = ?,"
							+ " recensione = ?, punteggio = ?, recensore = ? WHERE id = ?;";

	public DaoRecensioniMySQL(
			@Value("${db.address}")String dbAddress,
			@Value("${db.user}")String user,
			@Value("${db.psw}")String password
			) {
		super(dbAddress, user, password);
	}


	@Override
	public List<Recensione> recensioni() {
		List<Recensione> ris = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll(RECENSIONI_SELECT);
		for (Map<String, String> map : maps) {
			ris.add(IMappable.fromMap(Recensione.class, map));
		}
		
		return ris;
	}

	@Override
	public Recensione recensione(int id) {
		return IMappable.fromMap(Recensione.class, getOne(RECENSIONI_SELECT_ID, id));
	}

	@Override
	public void addRecensione(Recensione recensione) {
		execute(RECENSIONI_ADD,
				recensione.getTitoloVideogioco(),
				recensione.getDataRecensione(),
				recensione.getRecensione(),
				recensione.getPunteggio(),
				recensione.getRecensore());
	}

	@Override
	public void deleteRecensione(int id) {
		execute(RECENSIONI_DELETE, id);
	}

	@Override
	public void updateRecensione(Recensione recensione) {
		execute(RECENSIONI_UPDATE, 
				recensione.getTitoloVideogioco(),
				recensione.getDataRecensione(),
				recensione.getRecensione(),
				recensione.getPunteggio(),
				recensione.getRecensore(),
				recensione.getId());
	}



		
	}
	
	

