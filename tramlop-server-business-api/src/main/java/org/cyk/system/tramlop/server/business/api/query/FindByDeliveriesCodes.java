package org.cyk.system.tramlop.server.business.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface FindByDeliveriesCodes<ENTITY> {

	Collection<ENTITY> findByDeliveriesCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> findByDeliveriesCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return findByDeliveriesCodes(codes,null);
	}
	
	default Collection<ENTITY> findByDeliveriesCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return findByDeliveriesCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> findByDeliveriesCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return findByDeliveriesCodes(CollectionHelper.listOf(codes),null);
	}
}
