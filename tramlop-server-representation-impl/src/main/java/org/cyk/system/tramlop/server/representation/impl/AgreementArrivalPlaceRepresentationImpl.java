package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.AgreementArrivalPlaceRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementArrivalPlaceDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class AgreementArrivalPlaceRepresentationImpl extends AbstractRepresentationEntityImpl<AgreementArrivalPlaceDto> implements AgreementArrivalPlaceRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
