package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.QueryIdentifierBuilder;
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
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Collection<Truck> trucks,Properties properties) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(trucks.stream().map(Truck::getCode).collect(Collectors.toList()),properties);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Collection<Truck> trucks) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(trucks.stream().map(Truck::getCode).collect(Collectors.toList()),null);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Properties properties,Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucks(CollectionHelper.listOf(trucks),properties);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucks(CollectionHelper.listOf(trucks),null);
	}
	
	/**/
	
	String READ_WIEW = QueryIdentifierBuilder.getInstance().build(Delivery.class,"readView");
}
