package cr.ac.ucr.paraiso.videorent.data;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cr.ac.ucr.paraiso.videorent.domain.Pelicula;

@SpringBootTest
public class PeliculaDataTest {
	@Autowired
	private PeliculaData peliculaData;

	@Test
	public void findMoviesByTitleAndGenre() {
		List<Pelicula> peliculas = peliculaData.findMoviesByTitleAndGenre("time", "suspenso");
		assertNotNull(peliculas);
		assertTrue(!peliculas.isEmpty());
		
	}
}