package cr.ac.ucr.paraiso.videorent.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.videorent.business.GeneroBusiness;
import cr.ac.ucr.paraiso.videorent.domain.Genero;
import cr.ac.ucr.paraiso.videorent.dto.GeneroDTO;

@RestController
@RequestMapping(value = "/generos")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
public class GeneroRestController {
	@Autowired
	private GeneroBusiness generoBusiness;

	@GetMapping
	public ResponseEntity<List<GeneroDTO>> getAllGeneros() {
		List<Genero> generos = generoBusiness.findAll();

		if (generos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		// Map Genero to GeneroDTO
		List<GeneroDTO> generoDTOs = generos.stream().map(this::convertToDTO).collect(Collectors.toList());

		return ResponseEntity.ok(generoDTOs);
	}

	private GeneroDTO convertToDTO(Genero genero) {
		return new GeneroDTO(genero.getGeneroId(), genero.getNombreGenero());
	}

}
