package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadByTrucksCodes<ENTITY> {

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
}
