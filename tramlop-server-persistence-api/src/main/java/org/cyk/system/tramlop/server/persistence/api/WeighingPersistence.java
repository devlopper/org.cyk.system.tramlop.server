package org.cyk.system.tramlop.server.persistence.api;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface WeighingPersistence extends PersistenceEntity<Weighing> {

	Weighing readByDeliveryCodeByTaskCode(String deliveryCode,String taskCode);
	
	default Weighing readByDeliveryByTask(Delivery delivery,Task task) {
		if(delivery == null || task == null)
			return null;
		return readByDeliveryCodeByTaskCode(delivery.getCode(), task.getCode());
	}
}
