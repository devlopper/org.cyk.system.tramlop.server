package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadByAgreements<ENTITY> {

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
	
	default Collection<ENTITY> readByAgreements(Collection<Agreement> agreements,Properties properties) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return readByAgreementsCodes(agreements.stream().map(Agreement::getCode).collect(Collectors.toList()), properties);
	}
	
	default Collection<ENTITY> readByAgreements(Collection<Agreement> agreements) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return readByAgreements(agreements,null);
	}
	
	default Collection<ENTITY> readByAgreements(Properties properties,Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return readByAgreements(CollectionHelper.listOf(agreements),properties);
	}
	
	default Collection<ENTITY> readByAgreements(Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return readByAgreements(CollectionHelper.listOf(agreements),null);
	}
}
