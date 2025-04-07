package cr.ac.ucr.paraiso.videorent.domain;

import java.util.LinkedList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pelicula {
	private int peliculaId;
	
	private String titulo;
	
	private boolean subtitulada;
	private boolean estreno;
	
	private Genero genero;
	
	private List<Actor> actores;
	
	public Pelicula() {
		genero = new Genero();
		actores = new LinkedList<>();
	}

	public Pelicula(int peliculaId, String titulo, 
			boolean subtitulada, boolean estreno,
			Genero genero,
			List<Actor> actores) {
		this.peliculaId = peliculaId;
		this.titulo = titulo;
		this.subtitulada = subtitulada;
		this.estreno = estreno;
		this.genero = genero;
		this.actores = actores;
	}

	public int getPeliculaId() {
		return peliculaId;
	}

	public void setPeliculaId(int peliculaId) {
		this.peliculaId = peliculaId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isSubtitulada() {
		return subtitulada;
	}

	public void setSubtitulada(boolean subtitulada) {
		this.subtitulada = subtitulada;
	}

	public boolean isEstreno() {
		return estreno;
	}

	public void setEstreno(boolean estreno) {
		this.estreno = estreno;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Actor> getActores() {
		return actores;
	}

	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}
	
	
	

}
