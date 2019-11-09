package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.AgreementRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class AgreementRepresentationImpl extends AbstractRepresentationEntityImpl<AgreementDto> implements AgreementRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
