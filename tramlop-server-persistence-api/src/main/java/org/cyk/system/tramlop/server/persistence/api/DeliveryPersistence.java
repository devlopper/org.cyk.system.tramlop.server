package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DeliveryPersistence extends PersistenceEntity<Delivery> {

	/* get by truck */
	
	Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes,Properties properties);
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes) {
		if(CollectionHelper.isEmpty(trucksCodes))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(trucksCodes,null);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Properties properties,String...trucksCodes) {
		if(ArrayHelper.isEmpty(trucksCodes))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(CollectionHelper.listOf(trucksCodes),properties);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(String...trucksCodes) {
		if(ArrayHelper.isEmpty(trucksCodes))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(CollectionHelper.listOf(trucksCodes),null);
	}
	
}
