package org.cyk.system.tramlop.server.business.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.BusinessEntity;

public interface TruckBusiness extends BusinessEntity<Truck> {

	/* get by agreement */
	
	Collection<Truck> findWhereAgreementClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> findWhereAgreementClosedIsFalseExist() {
		return findWhereAgreementClosedIsFalseExist(null);
	}
	
	Collection<Truck> findWhereAgreementClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> findWhereAgreementClosedIsFalseDoesNotExist() {
		return findWhereAgreementClosedIsFalseDoesNotExist(null);
	}
	
	/* get by delivery */
	
	Collection<Truck> findWhereDeliveryClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> findWhereDeliveryClosedIsFalseExist() {
		return findWhereDeliveryClosedIsFalseExist(null);
	}
	
	Collection<Truck> findWhereDeliveryClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> findWhereDeliveryClosedIsFalseDoesNotExist() {
		return findWhereDeliveryClosedIsFalseDoesNotExist(null);
	}
	
	Collection<Truck> findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist() {
		return findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(null);
	}
	
	Collection<Truck> findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist() {
		return findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(null);
	}
	
}
