package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.AgreementTruckRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementTruckDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class AgreementTruckRepresentationImpl extends AbstractRepresentationEntityImpl<AgreementTruckDto> implements AgreementTruckRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
