package cr.ac.ucr.paraiso.videorent.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.ac.ucr.paraiso.videorent.data.ActorData;
import cr.ac.ucr.paraiso.videorent.domain.Actor;

@Service
public class ActorBusiness {
	@Autowired
	private ActorData actorData;
	
	 public List<Actor> findAll() {
		 return actorData.findAll();
	 }

}
