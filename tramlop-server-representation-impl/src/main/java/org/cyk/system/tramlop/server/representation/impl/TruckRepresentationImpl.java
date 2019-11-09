package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.TruckRepresentation;
import org.cyk.system.tramlop.server.representation.entities.TruckDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class TruckRepresentationImpl extends AbstractRepresentationEntityImpl<TruckDto> implements TruckRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
