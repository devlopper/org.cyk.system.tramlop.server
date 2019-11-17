package org.cyk.system.tramlop.server.business.api.query;

import java.util.Collection;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface FindByAgreementsCodes<ENTITY> {

	Collection<ENTITY> findByAgreementsCodes(Collection<String> codes,Properties properties);
	
	default Collection<ENTITY> findByAgreementsCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return findByAgreementsCodes(codes,null);
	}
	
	default Collection<ENTITY> findByAgreementsCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return findByAgreementsCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<ENTITY> findByAgreementsCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return findByAgreementsCodes(CollectionHelper.listOf(codes),null);
	}
}
