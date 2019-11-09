package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.DeliveryRepresentation;
import org.cyk.system.tramlop.server.representation.entities.DeliveryDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class DeliveryRepresentationImpl extends AbstractRepresentationEntityImpl<DeliveryDto> implements DeliveryRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
