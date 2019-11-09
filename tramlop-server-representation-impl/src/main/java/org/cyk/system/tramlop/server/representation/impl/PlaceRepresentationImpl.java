package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.PlaceRepresentation;
import org.cyk.system.tramlop.server.representation.entities.PlaceDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class PlaceRepresentationImpl extends AbstractRepresentationEntityImpl<PlaceDto> implements PlaceRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
