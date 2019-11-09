package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class ProductDtoMapper extends AbstractMapperSourceDestinationImpl<ProductDto, Product> {
	private static final long serialVersionUID = 1L;
    
 
}