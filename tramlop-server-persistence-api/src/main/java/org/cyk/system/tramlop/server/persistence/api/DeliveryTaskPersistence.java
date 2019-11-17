package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DeliveryTaskPersistence extends PersistenceEntity<DeliveryTask> {

	DeliveryTask readByDeliveryCodeByTaskCode(String deliveryCode,String taskCode);
	DeliveryTask readByDeliveryCodeByTaskOrderNumber(String deliveryCode,Integer taskOrderNumber);
}
