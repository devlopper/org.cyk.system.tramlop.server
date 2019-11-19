package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface LoadingPersistence extends PersistenceEntity<Loading> {

	Loading readByDeliveryCodeByTaskCode(String deliveryCode,String taskCode);
	
}
