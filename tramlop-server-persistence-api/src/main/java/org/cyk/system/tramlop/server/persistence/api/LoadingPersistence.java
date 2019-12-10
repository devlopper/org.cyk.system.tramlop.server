package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface LoadingPersistence extends PersistenceEntity<Loading> {

	Loading readByDeliveryCodeByTaskCode(String deliveryCode,String taskCode);
	
	default Loading readByDeliveryByTask(Delivery delivery,Task task) {
		if(delivery == null || task == null)
			return null;
		return readByDeliveryCodeByTaskCode(delivery.getCode(), task.getCode());
	}
	
	Loading readByDeliveryTask(DeliveryTask deliveryTask);
}
