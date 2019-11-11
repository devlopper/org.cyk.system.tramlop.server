package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.IncidentTypeDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(IncidentTypeRepresentation.PATH)
public interface IncidentTypeRepresentation extends RepresentationEntity<IncidentTypeDto> {
	
	String PATH = "/incidenttype";
	
}
