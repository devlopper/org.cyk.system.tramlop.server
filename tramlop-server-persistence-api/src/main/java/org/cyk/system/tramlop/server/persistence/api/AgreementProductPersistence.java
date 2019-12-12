package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface AgreementProductPersistence extends PersistenceEntity<AgreementProduct> {

	Collection<AgreementProduct> readByAgreementsCodes(Collection<String> agreementsCodes,Properties properties);
	
	default Collection<AgreementProduct> readByAgreementsCodes(Collection<String> agreementsCodes) {
		if(CollectionHelper.isEmpty(agreementsCodes))
			return null;
		return readByAgreementsCodes(agreementsCodes,null);
	}
	
	default Collection<AgreementProduct> readByAgreementsCodes(Properties properties,String...agreementsCodes) {
		if(ArrayHelper.isEmpty(agreementsCodes))
			return null;
		return readByAgreementsCodes(CollectionHelper.listOf(agreementsCodes),properties);
	}
	
	default Collection<AgreementProduct> readByAgreementsCodes(String...agreementsCodes) {
		if(ArrayHelper.isEmpty(agreementsCodes))
			return null;
		return readByAgreementsCodes(CollectionHelper.listOf(agreementsCodes),null);
	}
	
	default Collection<AgreementProduct> readByAgreements(Collection<Agreement> agreements,Properties properties) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return readByAgreementsCodes(agreements.stream().map(Agreement::getCode).collect(Collectors.toList()),properties);
	}
	
	default Collection<AgreementProduct> readByAgreements(Collection<Agreement> agreements) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return readByAgreements(agreements,null);
	}
	
	default Collection<AgreementProduct> readByAgreements(Properties properties,Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return readByAgreements(CollectionHelper.listOf(agreements),properties);
	}
	
	default Collection<AgreementProduct> readByAgreements(Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return readByAgreements(CollectionHelper.listOf(agreements),null);
	}

}
