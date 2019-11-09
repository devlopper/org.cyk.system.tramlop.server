package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.DeliveryTaskRepresentation;
import org.cyk.system.tramlop.server.representation.entities.DeliveryTaskDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class DeliveryTaskRepresentationImpl extends AbstractRepresentationEntityImpl<DeliveryTaskDto> implements DeliveryTaskRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
