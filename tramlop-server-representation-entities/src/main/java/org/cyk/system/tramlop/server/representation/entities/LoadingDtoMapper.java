package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class LoadingDtoMapper extends AbstractMapperSourceDestinationImpl<LoadingDto, Loading> {
	private static final long serialVersionUID = 1L;
    
 
}