package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadByAgreementsCodes<ENTITY> {

	Collection<ENTITY> readByAgreementsCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> readByAgreementsCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return readByAgreementsCodes(codes,null);
	}
	
	default Collection<ENTITY> readByAgreementsCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByAgreementsCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> readByAgreementsCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readByAgreementsCodes(CollectionHelper.listOf(codes),null);
	}
}
