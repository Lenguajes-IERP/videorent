package cr.ac.ucr.paraiso.videorent.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.videorent.business.PeliculaBusiness;

@RestController
public class HomeRestController {
	@Autowired
	private PeliculaBusiness peliculaBusiness;
   @RequestMapping(value="/home", method=RequestMethod.GET)
   public String home(){
	   peliculaBusiness.findAllMoviesByTitleAndGenre("women", "action");
       return "Hello World!";
   }
}
