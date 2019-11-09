package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.PathDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(PathRepresentation.PATH)
public interface PathRepresentation extends RepresentationEntity<PathDto> {
	
	String PATH = "/path";
	
}
