package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.TaskRepresentation;
import org.cyk.system.tramlop.server.representation.entities.TaskDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class TaskRepresentationImpl extends AbstractRepresentationEntityImpl<TaskDto> implements TaskRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
