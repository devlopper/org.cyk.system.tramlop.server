package org.cyk.system.tramlop.server.representation.entities;

import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.utility.server.representation.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class PlaceDtoMapper extends AbstractMapperSourceDestinationImpl<PlaceDto, Place> {
	private static final long serialVersionUID = 1L;
    
 
}