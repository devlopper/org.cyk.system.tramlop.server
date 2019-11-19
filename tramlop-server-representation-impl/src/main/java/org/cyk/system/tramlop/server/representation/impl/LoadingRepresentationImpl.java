package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.LoadingRepresentation;
import org.cyk.system.tramlop.server.representation.entities.LoadingDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class LoadingRepresentationImpl extends AbstractRepresentationEntityImpl<LoadingDto> implements LoadingRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
