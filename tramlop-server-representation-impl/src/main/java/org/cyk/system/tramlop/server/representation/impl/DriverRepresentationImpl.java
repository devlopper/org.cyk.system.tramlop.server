package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.DriverRepresentation;
import org.cyk.system.tramlop.server.representation.entities.DriverDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class DriverRepresentationImpl extends AbstractRepresentationEntityImpl<DriverDto> implements DriverRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
