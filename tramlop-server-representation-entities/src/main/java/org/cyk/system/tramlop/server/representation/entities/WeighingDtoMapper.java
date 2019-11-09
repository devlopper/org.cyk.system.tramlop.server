package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class WeighingDtoMapper extends AbstractMapperSourceDestinationImpl<WeighingDto, Weighing> {
	private static final long serialVersionUID = 1L;
    
 
}