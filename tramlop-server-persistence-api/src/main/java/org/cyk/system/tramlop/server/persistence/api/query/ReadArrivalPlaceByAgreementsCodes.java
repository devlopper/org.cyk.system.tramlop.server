package org.cyk.system.tramlop.server.persistence.api.query;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;

public interface ReadArrivalPlaceByAgreementsCodes extends ReadByAgreementsCodes<Place> {

	Collection<Place> readArrivalByAgreementsCodes(Collection<String> codes,Properties properties);
	
	default Collection<Place> readArrivalByAgreementsCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		return readArrivalByAgreementsCodes(codes,null);
	}
	
	default Collection<Place> readArrivalByAgreementsCodes(Properties properties,String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readArrivalByAgreementsCodes(CollectionHelper.listOf(codes),properties);
	}
	
	default Collection<Place> readArrivalByAgreementsCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return null;
		return readArrivalByAgreementsCodes(CollectionHelper.listOf(codes),null);
	}
	
}
