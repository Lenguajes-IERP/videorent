package cr.ac.ucr.paraiso.videorent.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cr.ac.ucr.paraiso.videorent.domain.Pelicula;

@SpringBootTest
public class PeliculaDataTest {
	@Autowired
	private PeliculaData peliculaData;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void findMoviesByTitleAndGenre_conTituloGenero_existentes() {
		// Arrange: Populate test database with data
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Genero (nombre_genero) VALUES (?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, "Science Fiction");
				return ps;
			}
		}, keyHolder);
		int generoId = keyHolder.getKey().intValue();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.
			prepareStatement("INSERT INTO Pelicula (titulo, subtitulada, estreno, genero_id) VALUES (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, "Avatar");
				ps.setBoolean(2, false);
				ps.setBoolean(3, false);
				ps.setInt(4, generoId);
				return ps;
			}
		}, keyHolder);
		
		int peliculaId = keyHolder.getKey().intValue();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.
			prepareStatement("INSERT INTO Actor (nombre_actor, apellidos_actor) VALUES (?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, "Sam");
				ps.setString(2, "Worthington");
				return ps;
			}
		}, keyHolder);
		
		int actorId = keyHolder.getKey().intValue();

		jdbcTemplate.execute("INSERT INTO PeliculaActor (pelicula_id, actor_id) VALUES (" + peliculaId +
				"," + actorId + ")");
		// Act
		List<Pelicula> peliculas = peliculaData.findMoviesByTitleAndGenre("avatar", "action");
		// Assert
		assertNotNull(peliculas);
		assertTrue(!peliculas.isEmpty());
		assertTrue(peliculas.stream().anyMatch(p -> p.getTitulo().equalsIgnoreCase("avatar")));

	}
}