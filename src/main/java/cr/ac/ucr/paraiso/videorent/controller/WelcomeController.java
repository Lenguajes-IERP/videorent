package cr.ac.ucr.paraiso.videorent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cr.ac.ucr.paraiso.videorent.business.PeliculaBusiness;

@Controller
public class WelcomeController {
	@Autowired
	private PeliculaBusiness peliculaBusiness;

	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	  public String welcome(){
		this.peliculaBusiness.findAllMoviesByTitleAndGenre("women", "action");
		// TODO hace una serie tareas en el backend y luego llama a un template
	       return "welcoming";
	   }

}
