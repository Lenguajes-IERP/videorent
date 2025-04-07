package cr.ac.ucr.paraiso.videorent.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.videorent.business.ActorBusiness;
import cr.ac.ucr.paraiso.videorent.domain.Actor;
import cr.ac.ucr.paraiso.videorent.dto.ActorDTO;

@RestController
@RequestMapping(value = "/actores")
@CrossOrigin(origins = "http://localhost:4200")
public class ActorRestController {
	@Autowired
	private ActorBusiness actorBusiness;
	
	
	@GetMapping
	public ResponseEntity<List<ActorDTO>> getAllGeneros() {
		List<Actor> actores = actorBusiness.findAll();

		if (actores.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		// Map Actor to ActorDTO
		List<ActorDTO> actorDTOs = actores.stream().map(this::convertToDTO).collect(Collectors.toList());

		return ResponseEntity.ok(actorDTOs);
	}

	private ActorDTO convertToDTO(Actor actor) {
		return new ActorDTO(actor.getActorId(), actor.getNombreActor(), actor.getApellidosActor());
	}
}
