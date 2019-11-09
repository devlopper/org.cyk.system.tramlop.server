package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.CustomerDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(CustomerRepresentation.PATH)
public interface CustomerRepresentation extends RepresentationEntity<CustomerDto> {
	
	String PATH = "/customer";
	
}
