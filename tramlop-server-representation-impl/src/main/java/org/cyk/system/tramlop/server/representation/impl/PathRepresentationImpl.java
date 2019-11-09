package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.PathRepresentation;
import org.cyk.system.tramlop.server.representation.entities.PathDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class PathRepresentationImpl extends AbstractRepresentationEntityImpl<PathDto> implements PathRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
