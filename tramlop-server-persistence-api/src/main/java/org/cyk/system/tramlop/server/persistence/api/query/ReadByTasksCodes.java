package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadByTasksCodes<ENTITY> {

	Collection<ENTITY> readByTasksCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> readByTasksCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return readByTasksCodes(codes,null);
	}
	
	default Collection<ENTITY> readByTasksCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByTasksCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> readByTasksCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByTasksCodes(CollectionHelper.listOf(codes),null);
	}
}
