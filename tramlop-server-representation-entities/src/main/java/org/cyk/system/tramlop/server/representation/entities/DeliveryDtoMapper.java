package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class DeliveryDtoMapper extends AbstractMapperSourceDestinationImpl<DeliveryDto, Delivery> {
	private static final long serialVersionUID = 1L;
    
 
}