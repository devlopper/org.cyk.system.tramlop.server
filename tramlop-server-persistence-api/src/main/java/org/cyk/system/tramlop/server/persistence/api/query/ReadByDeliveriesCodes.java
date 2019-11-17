package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadByDeliveriesCodes<ENTITY> {

	Collection<ENTITY> readByDeliveriesCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> readByDeliveriesCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return readByDeliveriesCodes(codes,null);
	}
	
	default Collection<ENTITY> readByDeliveriesCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByDeliveriesCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> readByDeliveriesCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByDeliveriesCodes(CollectionHelper.listOf(codes),null);
	}
}
