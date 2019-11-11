package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.IncidentTypeRepresentation;
import org.cyk.system.tramlop.server.representation.entities.IncidentTypeDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class IncidentTypeRepresentationImpl extends AbstractRepresentationEntityImpl<IncidentTypeDto> implements IncidentTypeRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
