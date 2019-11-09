package org.cyk.system.tramlop.server.representation.api;
import javax.ws.rs.Path;

import org.cyk.system.tramlop.server.representation.entities.TaskDto;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(TaskRepresentation.PATH)
public interface TaskRepresentation extends RepresentationEntity<TaskDto> {
	
	String PATH = "/task";
	
}
