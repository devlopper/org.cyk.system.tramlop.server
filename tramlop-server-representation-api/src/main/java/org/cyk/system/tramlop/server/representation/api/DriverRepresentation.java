package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.DriverDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(DriverRepresentation.PATH)
public interface DriverRepresentation extends RepresentationEntity<DriverDto> {
	
	String PATH = "/driver";
	
}
