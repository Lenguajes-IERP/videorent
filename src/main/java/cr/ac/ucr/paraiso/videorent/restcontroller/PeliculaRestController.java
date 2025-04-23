package cr.ac.ucr.paraiso.videorent.restcontroller;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.videorent.business.PeliculaBusiness;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;
import cr.ac.ucr.paraiso.videorent.dto.PeliculaCreationDTO;
import cr.ac.ucr.paraiso.videorent.mapper.PeliculaMapper;

@RestController
@RequestMapping(value = "/peliculas")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaRestController {
	@Autowired
	private PeliculaBusiness peliculaBusiness;
	
	@Autowired
    private PeliculaMapper peliculaMapper; // Inject the mapper
	
	@PostMapping
    public ResponseEntity<?> createPelicula(@Validated @RequestBody PeliculaCreationDTO peliculaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> errorMessages = errors.stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
		Pelicula pelicula = peliculaMapper.toPelicula(peliculaDTO);
        try {
			this.peliculaBusiness.save(pelicula);
		} catch (SQLException e) {
			// TODO what to do with this
			e.printStackTrace();
		}
        
        return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
    }
	

}
