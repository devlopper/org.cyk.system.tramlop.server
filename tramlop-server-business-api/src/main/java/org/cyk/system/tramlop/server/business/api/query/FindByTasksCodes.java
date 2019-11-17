package org.cyk.system.tramlop.server.business.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface FindByTasksCodes<ENTITY> {

	Collection<ENTITY> findByTasksCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> findByTasksCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return findByTasksCodes(codes,null);
	}
	
	default Collection<ENTITY> findByTasksCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return findByTasksCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> findByTasksCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return findByTasksCodes(CollectionHelper.listOf(codes),null);
	}
}
