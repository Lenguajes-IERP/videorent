package cr.ac.ucr.paraiso.videorent.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import cr.ac.ucr.paraiso.videorent.data.PeliculaData;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;

@SpringBootTest
class PeliculaBusinessTest {
	@Mock
	private PeliculaData peliculaData;
	
	@InjectMocks
	private PeliculaBusiness peliculaBusiness;

	@Test
	void findAllMoviesByTitleAndGenre_cuando_tituloGenero_noEncontrado() {
		// Arrange
		String titulo = "women xyz";
		String genero = "action xyz";
		List<Pelicula> peliculas = new LinkedList<>();
		Mockito.when(peliculaData.findMoviesByTitleAndGenre(titulo, genero)).thenReturn(peliculas);
		
		//Act
		int valorEsperado = 0;
		List<Pelicula> peliculasRetornadas =
				peliculaBusiness.findAllMoviesByTitleAndGenre(titulo, genero);
		//Assert
		assertEquals(valorEsperado,peliculas.size());
	}

}
