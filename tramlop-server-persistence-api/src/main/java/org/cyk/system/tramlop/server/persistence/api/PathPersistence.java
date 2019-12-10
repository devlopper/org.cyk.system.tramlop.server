package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface PathPersistence extends PersistenceEntity<Path> {

	Path readByDepartureCodeByArrivalCode(String departureCode,String arrivalCode);
	
	default Path readByDepartureByArrival(Place departure,Place arrival) {
		if(departure == null || arrival == null)
			return null;
		return readByDepartureCodeByArrivalCode(departure.getCode(), arrival.getCode());
	}
	
}
