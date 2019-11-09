package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.DeliveryTaskDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(DeliveryTaskRepresentation.PATH)
public interface DeliveryTaskRepresentation extends RepresentationEntity<DeliveryTaskDto> {
	
	String PATH = "/deliverytask";
	
}
