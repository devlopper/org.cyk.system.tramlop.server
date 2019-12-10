package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface AgreementPersistence extends PersistenceEntity<Agreement> {

	/* get by truck */
	
	Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes,Properties properties);
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes) {
		if(CollectionHelper.isEmpty(trucksCodes))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucksCodes(trucksCodes,null);
	}
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucksCodes(Properties properties,String...trucksCodes) {
		if(ArrayHelper.isEmpty(trucksCodes))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucksCodes(CollectionHelper.listOf(trucksCodes),properties);
	}
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucksCodes(String...trucksCodes) {
		if(ArrayHelper.isEmpty(trucksCodes))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucksCodes(CollectionHelper.listOf(trucksCodes),null);
	}
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucks(Collection<Truck> trucks,Properties properties) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucksCodes(trucks.stream().map(Truck::getCode).collect(Collectors.toList()));
	}
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucks(Collection<Truck> trucks) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucks(trucks,null);
	}
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucks(Properties properties,Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucks(CollectionHelper.listOf(trucks),properties);
	}
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucks(Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readWhereAgreementClosedIsFalseExistByTrucks(CollectionHelper.listOf(trucks),null);
	}
	
	/*
	Collection<Agreement> readWhereAgreementClosedIsFalseDoesNotExistByTrucksCodes(Collection<String> trucksCodes,Properties properties);
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseDoesNotExistByTrucksCodes(Collection<String> trucksCodes) {
		return readWhereAgreementClosedIsFalseDoesNotExistByTrucksCodes(trucksCodes,null);
	}
	*/
}
