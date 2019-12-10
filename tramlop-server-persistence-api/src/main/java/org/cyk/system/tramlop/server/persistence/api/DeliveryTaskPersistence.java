package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DeliveryTaskPersistence extends PersistenceEntity<DeliveryTask> {

	Collection<DeliveryTask> readByDeliveriesCodesByTasksCodes(Collection<String> deliveriesCodes,Collection<String> tasksCodes,Properties properties);
	
	default Collection<DeliveryTask> readByDeliveriesByTasksCodes(Collection<Delivery> deliveries,Collection<String> tasksCodes,Properties properties) {
		if(CollectionHelper.isEmpty(deliveries) || CollectionHelper.isEmpty(tasksCodes))
			return null;
		return readByDeliveriesCodesByTasksCodes(deliveries.stream().map(Delivery::getCode).collect(Collectors.toList()),tasksCodes,properties);
	}
	
	default Collection<DeliveryTask> readByDeliveriesByTasksCodes(Collection<Delivery> deliveries,Collection<String> tasksCodes) {
		if(CollectionHelper.isEmpty(deliveries) || CollectionHelper.isEmpty(tasksCodes))
			return null;
		return readByDeliveriesByTasksCodes(deliveries,tasksCodes,null);
	}
	
	DeliveryTask readByDeliveryCodeByTaskCode(String deliveryCode,String taskCode);
	DeliveryTask readByDeliveryByTask(Delivery delivery,Task task);
	DeliveryTask readByDeliveryCodeByTaskOrderNumber(String deliveryCode,Integer taskOrderNumber);
	default DeliveryTask readByDeliveryByTaskOrderNumber(Delivery delivery,Integer taskOrderNumber) {
		if(delivery == null || taskOrderNumber == null)
			return null;
		return readByDeliveryCodeByTaskOrderNumber(delivery.getCode(), taskOrderNumber);
	}
}
