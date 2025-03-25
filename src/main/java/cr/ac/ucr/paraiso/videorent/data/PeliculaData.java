package cr.ac.ucr.paraiso.videorent.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.videorent.domain.Actor;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;


@Repository
public class PeliculaData {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Transactional(readOnly=true)
	public List<Pelicula> findMoviesByTitleAndGenre(String title, String genre) {
		
		String sqlSelect = 
				"SELECT p.pelicula_id, p.titulo, p.subtitulada, p.estreno,"+
			    "p.genero_id,g.nombre_genero,"+
			    "a.actor_id, a.nombre_actor,a.apellidos_actor "+
			    "FROM  Pelicula p " +
			    "JOIN  Genero g ON p.genero_id = g.genero_id " +
			    "LEFT JOIN PeliculaActor pa ON p.pelicula_id = pa.pelicula_id "+
			    "LEFT JOIN Actor a ON pa.actor_id = a.actor_id " +
			    "WHERE p.titulo LIKE ? OR g.nombre_genero LIKE ?";
				
		return jdbcTemplate.query(sqlSelect, new PeliculaExtractor(), "%" + title + "%", "%" +genre + "%");
	}// findMoviesByTitleAndGenre
}

