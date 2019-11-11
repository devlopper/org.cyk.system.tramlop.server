package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.IncidentType;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class IncidentTypeDtoMapper extends AbstractMapperSourceDestinationImpl<IncidentTypeDto, IncidentType> {
	private static final long serialVersionUID = 1L;
    
 
}