package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.WeighingDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(WeighingRepresentation.PATH)
public interface WeighingRepresentation extends RepresentationEntity<WeighingDto> {
	
	String PATH = "/weighing";
	
}
