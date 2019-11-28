package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadByTrucks<ENTITY> {

	Collection<ENTITY> readByTrucksCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> readByTrucksCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return readByTrucksCodes(codes,null);
	}
	
	default Collection<ENTITY> readByTrucksCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByTrucksCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> readByTrucksCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByTrucksCodes(CollectionHelper.listOf(codes),null);
	}
	
	default Collection<ENTITY> readByTrucks(Collection<Truck> trucks,Properties properties) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readByTrucksCodes(trucks.stream().map(Truck::getCode).collect(Collectors.toSet()), properties);
	}
	
	default Collection<ENTITY> readByTrucks(Collection<Truck> trucks) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readByTrucks(trucks,null);
	}
	
	default Collection<ENTITY> readByTrucks(Properties properties,Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readByTrucks(CollectionHelper.listOf(trucks),properties);
	}
	
	default Collection<ENTITY> readByTrucks(Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readByTrucks(CollectionHelper.listOf(trucks),null);
	}
}
