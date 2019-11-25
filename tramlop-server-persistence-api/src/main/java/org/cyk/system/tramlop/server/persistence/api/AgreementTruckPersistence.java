package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface AgreementTruckPersistence extends PersistenceEntity<AgreementTruck> {

	AgreementTruck readByAgreementCodeByTruckCode(String agreementCode,String truckCode);
	
	default AgreementTruck readByAgreementByTruck(Agreement agreement,Truck truck) {
		if(agreement == null || truck == null)
			return null;
		return readByAgreementCodeByTruckCode(agreement.getCode(),truck.getCode());
	}
	
}
