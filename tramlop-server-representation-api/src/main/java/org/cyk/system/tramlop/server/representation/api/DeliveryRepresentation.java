package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.DeliveryDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(DeliveryRepresentation.PATH)
public interface DeliveryRepresentation extends RepresentationEntity<DeliveryDto> {
	
	String PATH = "/delivery";
	
}
