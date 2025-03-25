package cr.ac.ucr.paraiso.videorent.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cr.ac.ucr.paraiso.videorent.data.PeliculaData;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;

@Service
public class PeliculaBusiness {
	@Autowired
	private PeliculaData peliculaData;
	
	public List<Pelicula> findAllMoviesByTitleAndGenre(String title, String genre) {
		float tipoCambio = 530f;
		return peliculaData.findMoviesByTitleAndGenre(title, genre);
	} 

}
