package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class DeliveryTaskDtoMapper extends AbstractMapperSourceDestinationImpl<DeliveryTaskDto, DeliveryTask> {
	private static final long serialVersionUID = 1L;
    
 
}