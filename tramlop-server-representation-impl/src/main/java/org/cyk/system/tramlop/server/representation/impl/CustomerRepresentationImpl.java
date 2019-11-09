package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.CustomerRepresentation;
import org.cyk.system.tramlop.server.representation.entities.CustomerDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class CustomerRepresentationImpl extends AbstractRepresentationEntityImpl<CustomerDto> implements CustomerRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
