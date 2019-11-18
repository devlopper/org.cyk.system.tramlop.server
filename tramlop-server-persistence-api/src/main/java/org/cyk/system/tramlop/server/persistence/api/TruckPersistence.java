package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.persistence.QueryIdentifierBuilder;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface TruckPersistence extends PersistenceEntity<Truck> {

	/* get by agreement */
	
	Collection<Truck> readWhereAgreementClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExist() {
		return readWhereAgreementClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist() {
		return readWhereAgreementClosedIsFalseDoesNotExist(null);
	}
	
	/* get by delivery */
	
	Collection<Truck> readWhereDeliveryClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereDeliveryClosedIsFalseExist() {
		return readWhereDeliveryClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereDeliveryClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereDeliveryClosedIsFalseDoesNotExist() {
		return readWhereDeliveryClosedIsFalseDoesNotExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist() {
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist() {
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(null);
	}
	
	/**/
	
	String READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST 
		= QueryIdentifierBuilder.getInstance().build(Truck.class,"readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist");

}
