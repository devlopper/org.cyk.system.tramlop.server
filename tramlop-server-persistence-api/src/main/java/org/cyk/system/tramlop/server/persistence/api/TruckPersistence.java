package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface TruckPersistence extends PersistenceEntity<Truck> {

	Collection<Truck> readWhereAgreementClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExist() {
		return readWhereAgreementClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist() {
		return readWhereAgreementClosedIsFalseDoesNotExist(null);
	}
	
}
