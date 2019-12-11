package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.EmployeeDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(EmployeeRepresentation.PATH)
public interface EmployeeRepresentation extends RepresentationEntity<EmployeeDto> {
	
	String PATH = "/employee";
	
}
