package cr.ac.ucr.paraiso.videorent.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import cr.ac.ucr.paraiso.videorent.domain.Actor;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;

public class PeliculaExtractor implements ResultSetExtractor<List<Pelicula>> {
	@Override
	public List<Pelicula> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Pelicula> map = new HashMap<Integer, Pelicula>();
		Pelicula pelicula = null;
		while (rs.next()) {
			Integer id = rs.getInt("pelicula_id");
			pelicula = map.get(id);
			if (pelicula == null) { // new pelicula record
				pelicula = new Pelicula();
				pelicula.setPeliculaId(id);
				pelicula.setTitulo(rs.getString("titulo"));
				pelicula.getGenero().setGeneroId(rs.getInt("genero_id"));
				pelicula.getGenero().setNombreGenero(rs.getString("nombre_genero"));
				pelicula.setSubtitulada(rs.getBoolean("subtitulada"));
				pelicula.setEstreno(rs.getBoolean("estreno"));
				map.put(id, pelicula);
			} // if
			int actorId = rs.getInt("actor_id");
			if (actorId > 0) {
				Actor actor = new Actor();
				actor.setActorId(actorId);
				actor.setNombreActor(rs.getString("nombre_actor"));
				actor.setApellidosActor(rs.getString("apellidos_actor"));
				pelicula.getActores().add(actor);
			} // if
			// TODO Auto-generated method stub
		} // while
		return new ArrayList<Pelicula>(map.values());
	} // extractData
}