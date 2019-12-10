package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.QueryIdentifierBuilder;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DeliveryPersistence extends PersistenceEntity<Delivery> {

	/* get by truck */
	
	Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes,Properties properties);
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes) {
		if(CollectionHelper.isEmpty(trucksCodes))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(trucksCodes,null);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Properties properties,String...trucksCodes) {
		if(ArrayHelper.isEmpty(trucksCodes))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(CollectionHelper.listOf(trucksCodes),properties);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(String...trucksCodes) {
		if(ArrayHelper.isEmpty(trucksCodes))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(CollectionHelper.listOf(trucksCodes),null);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Collection<Truck> trucks,Properties properties) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(trucks.stream().map(Truck::getCode).collect(Collectors.toList()),properties);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Collection<Truck> trucks) {
		if(CollectionHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucksCodes(trucks.stream().map(Truck::getCode).collect(Collectors.toList()),null);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Properties properties,Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucks(CollectionHelper.listOf(trucks),properties);
	}
	
	default Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucks(Truck...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return null;
		return readWhereDeliveryClosedIsFalseExistByTrucks(CollectionHelper.listOf(trucks),null);
	}
	
	/* get by agreement */
	
	Collection<Delivery> readByAgreementsCodesByClosed(Collection<String> agreementsCodes,Boolean closed,Properties properties);
	Long countByAgreementsCodesByClosed(Collection<String> agreementsCodes,Boolean closed,Properties properties);
	
	default Collection<Delivery> readByAgreementsCodesByClosed(Collection<String> agreementsCodes,Boolean closed) {
		if(CollectionHelper.isEmpty(agreementsCodes))
			return null;
		return readByAgreementsCodesByClosed(agreementsCodes,closed,null);
	}
	
	default Collection<Delivery> readByAgreementsCodesByClosed(Properties properties,Boolean closed,String...agreementsCodes) {
		if(ArrayHelper.isEmpty(agreementsCodes))
			return null;
		return readByAgreementsCodesByClosed(CollectionHelper.listOf(agreementsCodes),closed,properties);
	}
	
	default Collection<Delivery> readByAgreementsCodesByClosed(Boolean closed,String...agreementsCodes) {
		if(ArrayHelper.isEmpty(agreementsCodes))
			return null;
		return readByAgreementsCodesByClosed(CollectionHelper.listOf(agreementsCodes),closed,null);
	}
	
	default Long countByAgreementsCodesByClosed(Boolean closed,String...agreementsCodes) {
		if(ArrayHelper.isEmpty(agreementsCodes))
			return null;
		return countByAgreementsCodesByClosed(CollectionHelper.listOf(agreementsCodes),closed,null);
	}
	
	default Collection<Delivery> readByAgreementsByClosed(Collection<Agreement> agreements,Boolean closed,Properties properties) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return readByAgreementsCodesByClosed(agreements.stream().map(Agreement::getCode).collect(Collectors.toList()),closed,properties);
	}
	
	default Long countByAgreementsByClosed(Collection<Agreement> agreements,Boolean closed,Properties properties) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return countByAgreementsCodesByClosed(agreements.stream().map(Agreement::getCode).collect(Collectors.toList()),closed,properties);
	}
	
	default Collection<Delivery> readByAgreementsByClosed(Collection<Agreement> agreements,Boolean closed) {
		if(CollectionHelper.isEmpty(agreements))
			return null;
		return readByAgreementsCodesByClosed(agreements.stream().map(Agreement::getCode).collect(Collectors.toList()),closed,null);
	}
	
	default Collection<Delivery> readByAgreementsByClosed(Properties properties,Boolean closed,Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return readByAgreementsByClosed(CollectionHelper.listOf(agreements),closed,properties);
	}
	
	default Collection<Delivery> readByAgreementsByClosed(Boolean closed,Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return readByAgreementsByClosed(CollectionHelper.listOf(agreements),closed,null);
	}
	
	default Long countByAgreementsByClosed(Boolean closed,Agreement...agreements) {
		if(ArrayHelper.isEmpty(agreements))
			return null;
		return countByAgreementsByClosed(CollectionHelper.listOf(agreements),closed,null);
	}
	
	/* get by closed */
	
	Collection<Delivery> readByClosed(Boolean closed,Properties properties);
	
	default Collection<Delivery> readByClosed(Boolean closed) {
		return readByClosed(closed,null);
	}
	
	/**/
	
	String READ_WIEW = QueryIdentifierBuilder.getInstance().build(Delivery.class,"readView");
}
