package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.representation.api.ProductRepresentation;
import org.cyk.system.tramlop.server.representation.entities.ProductDto;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@ApplicationScoped
public class ProductRepresentationImpl extends AbstractRepresentationEntityImpl<ProductDto> implements ProductRepresentation,Serializable {
	private static final long serialVersionUID = 1L;
	
}
