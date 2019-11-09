package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class CustomerDtoMapper extends AbstractMapperSourceDestinationImpl<CustomerDto, Customer> {
	private static final long serialVersionUID = 1L;
    
 
}