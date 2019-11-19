package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.LoadingDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(LoadingRepresentation.PATH)
public interface LoadingRepresentation extends RepresentationEntity<LoadingDto> {
	
	String PATH = "/loading";
	
}
