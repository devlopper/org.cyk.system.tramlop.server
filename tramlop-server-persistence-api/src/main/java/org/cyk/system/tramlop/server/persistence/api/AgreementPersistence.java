package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
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
	/*
	Collection<Agreement> readWhereAgreementClosedIsFalseDoesNotExistByTrucksCodes(Collection<String> trucksCodes,Properties properties);
	
	default Collection<Agreement> readWhereAgreementClosedIsFalseDoesNotExistByTrucksCodes(Collection<String> trucksCodes) {
		return readWhereAgreementClosedIsFalseDoesNotExistByTrucksCodes(trucksCodes,null);
	}
	*/
}
