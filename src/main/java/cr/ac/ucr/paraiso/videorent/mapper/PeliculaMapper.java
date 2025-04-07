package cr.ac.ucr.paraiso.videorent.mapper;

import cr.ac.ucr.paraiso.videorent.dto.PeliculaCreationDTO;
import cr.ac.ucr.paraiso.videorent.domain.Actor;
import cr.ac.ucr.paraiso.videorent.domain.Genero;
import cr.ac.ucr.paraiso.videorent.domain.Pelicula;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    @Mapping(source = "genero.generoId", target = "genero.generoId")
    @Mapping(target = "actores", expression = "java(mapActors(peliculaCreationDTO.getActores()))")
    Pelicula toPelicula(PeliculaCreationDTO peliculaCreationDTO);

    default List<Actor> mapActors(List<PeliculaCreationDTO.ActorDTO> actorDTOs) {
        if (actorDTOs == null) {
            return null;
        }
        return actorDTOs.stream()
                .map(actorDTO -> {
                    Actor actor = new Actor();
                    actor.setActorId(actorDTO.getActorId());
                    return actor;
                }).collect(java.util.stream.Collectors.toList());
    }

    default Genero mapGenero(PeliculaCreationDTO.GeneroDTO generoDTO){
        if(generoDTO == null){
            return null;
        }
        Genero genero = new Genero();
        genero.setGeneroId(generoDTO.getGeneroId());
        return genero;
    }

}