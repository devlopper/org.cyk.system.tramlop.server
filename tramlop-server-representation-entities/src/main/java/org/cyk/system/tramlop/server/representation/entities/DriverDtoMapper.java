package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class DriverDtoMapper extends AbstractMapperSourceDestinationImpl<DriverDto, Driver> {
	private static final long serialVersionUID = 1L;
    
 
}