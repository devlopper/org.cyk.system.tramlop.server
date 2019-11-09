package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.WeighingRepresentation;
import org.cyk.system.tramlop.server.representation.entities.WeighingDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class WeighingRepresentationImpl extends AbstractRepresentationEntityImpl<WeighingDto> implements WeighingRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
