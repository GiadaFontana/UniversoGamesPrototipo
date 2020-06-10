package org.generation.italy.universoGames.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.universoGames.entities.News;
import org.generation.italy.universoGames.util.BasicDao;
import org.generation.italy.universoGames.util.IMappable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class DaoNews extends BasicDao implements IDaoNews {

	private static final String WHERE_ID = "WHERE id = ?;";
	private static final String DELETE_FROM_NEWS = "DELETE FROM news ";
	private static final String INSERT_INTO_NEWS_ = "INSERT INTO news(titolo, categoria, contenuto, datapubblicazione, autore) VALUES(?,?,?,?,?);";
	private static final String SELECT_FROM_NEWS_WHERE_ID = "SELECT * FROM news WHERE id = ?;";
	private static final String SELECT_FROM_NEWS = "SELECT * FROM news;";

	public DaoNews  (@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user, 
			@Value("${db.psw}") String password
			)  {
		super(dbAddress, user, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<News> news() {
		List<News> ris = new ArrayList<>();

		List<Map<String, String>> maps = getAll(SELECT_FROM_NEWS);
		System.out.println(maps);
		for (Map<String, String> map : maps) {
			ris.add(IMappable.fromMap(News.class, map));
		}

		return ris;
	}
		
	@Override
	public News n(int id) {
		return IMappable.fromMap(News.class,
				getOne(SELECT_FROM_NEWS_WHERE_ID, id));
	}

	@Override
	public void aggiungi(News n) {
		execute(INSERT_INTO_NEWS_, 
				n.getTitolo(), n.getCategoria(), n.getContenuto(), n.getDataPubblicazione(), n.getAutore());
	}

	@Override
	public void elimina(int id) {
		execute(DELETE_FROM_NEWS + 
				WHERE_ID, id);
	}

	@Override
	public void modifica(News n, int id) {
		execute("UPDATE news SET titolo = ?, categoria = ?, contenuto = ?, datapubblicazione = ?, autore = ? WHERE id = ?;",
				n.getTitolo(), n.getCategoria(), n.getContenuto(), n.getDataPubblicazione(), n.getAutore(), id);		
	}
	

}
