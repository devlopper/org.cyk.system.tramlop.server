package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DeliveryTaskPersistence extends PersistenceEntity<DeliveryTask> {

	DeliveryTask readByDeliveryCodeByTaskCode(String deliveryCode,String taskCode);
	DeliveryTask readByDeliveryByTask(Delivery delivery,Task task);
	DeliveryTask readByDeliveryCodeByTaskOrderNumber(String deliveryCode,Integer taskOrderNumber);
	default DeliveryTask readByDeliveryByTaskOrderNumber(Delivery delivery,Integer taskOrderNumber) {
		if(delivery == null || taskOrderNumber == null)
			return null;
		return readByDeliveryCodeByTaskOrderNumber(delivery.getCode(), taskOrderNumber);
	}
}
