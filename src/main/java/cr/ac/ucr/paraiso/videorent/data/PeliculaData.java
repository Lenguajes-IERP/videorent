package cr.ac.ucr.paraiso.videorent.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.videorent.domain.Actor;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;


@Repository
public class PeliculaData {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional  // The @Transactional annotation bounds the method execution in a transaction context as it is performing a database update.
	public void save(Pelicula pelicula) throws SQLException{
			SimpleJdbcCall simpleJdbcCallPelicula = new SimpleJdbcCall(jdbcTemplate).
					withCatalogName("dbo").
					withProcedureName("InsertPelicula").withoutProcedureColumnMetaDataAccess().
					declareParameters(new SqlOutParameter("@pelicula_id", Types.INTEGER)).
					declareParameters(new SqlParameter("@titulo", Types.VARCHAR)).
					declareParameters(new SqlParameter("@subtitulada", Types.BIT)).
					declareParameters(new SqlParameter("@estreno", Types.BIT)).
					declareParameters(new SqlParameter("@genero_id", Types.INTEGER));
			Map<String, Object> outParameters = simpleJdbcCallPelicula.execute(pelicula.getTitulo(), pelicula.isSubtitulada(), pelicula.isEstreno(), pelicula.getGenero().getGeneroId());
			pelicula.setPeliculaId(Integer.parseInt(outParameters.get("@pelicula_id").toString()));
			
			SimpleJdbcCall simpleJdbcCallPeliculaActor = new SimpleJdbcCall(jdbcTemplate).
					withCatalogName("dbo").
					withProcedureName("InsertPeliculaActor").withoutProcedureColumnMetaDataAccess().
					declareParameters(new SqlParameter("@pelicula_id", Types.INTEGER)).
					declareParameters(new SqlParameter("@actor_id", Types.INTEGER));
			for(Actor actor:pelicula.getActores())
				simpleJdbcCallPeliculaActor.execute(pelicula.getPeliculaId(), actor.getActorId());
	}
	
	public List<Pelicula> findMoviesByTitleAndGenre(String title, String genre) {
		
		String genreWithoutSpaces = genre.trim();
		String sqlSelect = 
				"SELECT p.pelicula_id, p.titulo, p.subtitulada, p.estreno,"+
			    "p.genero_id,g.nombre_genero,"+
			    "a.actor_id, a.nombre_actor,a.apellidos_actor "+
			    "FROM  Pelicula p " +
			    "JOIN  Genero g ON p.genero_id = g.genero_id " +
			    "LEFT JOIN PeliculaActor pa ON p.pelicula_id = pa.pelicula_id "+
			    "LEFT JOIN Actor a ON pa.actor_id = a.actor_id " +
			    
			    "WHERE p.titulo LIKE ? OR g.nombre_genero LIKE ?";
				
		return jdbcTemplate.query(sqlSelect, new PeliculaExtractor(), "%" + title + "%", "%" +genreWithoutSpaces + "%");
	}// findMoviesByTitleAndGenre
}

