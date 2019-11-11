package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.AgreementProductRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementProductDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class AgreementProductRepresentationImpl extends AbstractRepresentationEntityImpl<AgreementProductDto> implements AgreementProductRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
