package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class AgreementProductDtoMapper extends AbstractMapperSourceDestinationImpl<AgreementProductDto, AgreementProduct> {
	private static final long serialVersionUID = 1L;
    
 
}