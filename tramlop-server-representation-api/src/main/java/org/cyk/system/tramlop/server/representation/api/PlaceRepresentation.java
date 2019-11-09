package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.PlaceDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(PlaceRepresentation.PATH)
public interface PlaceRepresentation extends RepresentationEntity<PlaceDto> {
	
	String PATH = "/place";
	
}
