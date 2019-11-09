package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.TruckDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(TruckRepresentation.PATH)
public interface TruckRepresentation extends RepresentationEntity<TruckDto> {
	
	String PATH = "/truck";
	
}
