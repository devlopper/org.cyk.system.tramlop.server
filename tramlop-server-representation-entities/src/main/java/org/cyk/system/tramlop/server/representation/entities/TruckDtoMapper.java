package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class TruckDtoMapper extends AbstractMapperSourceDestinationImpl<TruckDto, Truck> {
	private static final long serialVersionUID = 1L;
    
 
}