package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.AgreementPathRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementPathDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class AgreementPathRepresentationImpl extends AbstractRepresentationEntityImpl<AgreementPathDto> implements AgreementPathRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
