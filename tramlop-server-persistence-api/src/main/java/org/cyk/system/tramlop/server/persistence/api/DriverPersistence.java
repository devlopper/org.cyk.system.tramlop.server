package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.QueryIdentifierBuilder;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DriverPersistence extends PersistenceEntity<Driver> {

	Collection<Driver> readWhereNotExistAgreementClosedIsFalse();
	
	Collection<Driver> readByAgreementsCodesByTrucksCodes(Collection<String> agreementsCodes,Collection<String> trucksCodes,Properties properties);
	
	default Collection<Driver> readByAgreementCodeByTruckCode(String agreementCode,String truckCode,Properties properties) {
		if(StringHelper.isBlank(agreementCode) || StringHelper.isBlank(truckCode))
			return null;
		return readByAgreementsCodesByTrucksCodes(CollectionHelper.listOf(agreementCode),CollectionHelper.listOf(truckCode),properties);
	}
	
	default Collection<Driver> readByAgreementCodeByTruckCode(String agreementCode,String truckCode) {
		if(StringHelper.isBlank(agreementCode) || StringHelper.isBlank(truckCode))
			return null;
		return readByAgreementsCodesByTrucksCodes(CollectionHelper.listOf(agreementCode),CollectionHelper.listOf(truckCode),null);
	}
	
	default Collection<Driver> readByAgreementByTruck(Agreement agreement,Truck truck,Properties properties) {
		if(agreement == null || truck == null)
			return null;
		return readByAgreementCodeByTruckCode(agreement.getCode(),truck.getCode(),properties);
	}
	
	default Collection<Driver> readByAgreementByTruck(Agreement agreement,Truck truck) {
		if(agreement == null || truck == null)
			return null;
		return readByAgreementCodeByTruckCode(agreement.getCode(),truck.getCode(),null);
	}
	
	String READ_WHERE_NOT_EXIST_AGREEMENT_CLOSED_IS_FALSE = QueryIdentifierBuilder.getInstance().build(Driver.class,"readWhereNotExistAgreementClosedIsFalse");
	
}
