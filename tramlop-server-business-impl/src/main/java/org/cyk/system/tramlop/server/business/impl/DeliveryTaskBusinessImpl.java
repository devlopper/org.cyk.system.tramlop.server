package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.business.api.LoadingBusiness;
import org.cyk.system.tramlop.server.business.api.WeighingBusiness;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Existence;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class DeliveryTaskBusinessImpl extends AbstractBusinessEntityImpl<DeliveryTask, DeliveryTaskPersistence> implements DeliveryTaskBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(DeliveryTask deliveryTask, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(deliveryTask, properties, function);
		if(deliveryTask.getDelivery() == null && deliveryTask.getTruck() != null) {
			Collection<Delivery> deliveries = __inject__(DeliveryPersistence.class).readWhereDeliveryClosedIsFalseExistByTrucksCodes(deliveryTask.getTruck().getCode());
			deliveryTask.setDelivery(CollectionHelper.getFirst(deliveries));
		}
		if(deliveryTask.getTask() != null && deliveryTask.getTask().getOrderNumber() > 1) {
			Task previousTask = __inject__(TaskPersistence.class).readByOrderNumber(deliveryTask.getTask().getOrderNumber() - 1);
			if(__persistence__.readByDeliveryCodeByTaskOrderNumber(deliveryTask.getDelivery().getCode(), previousTask.getOrderNumber()) == null)
				throw new RuntimeException("La réalisation de la tache <<"+previousTask.getName()+">> est obligatoire avant "+deliveryTask.getTask().getName()+".");
		}
		
		if(deliveryTask.getExistence() == null)
			deliveryTask.setExistence(new Existence());
		if(deliveryTask.getExistence().getCreationDate() == null)
			deliveryTask.getExistence().setCreationDate(LocalDateTime.now());
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(DeliveryTask deliveryTask, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(deliveryTask, properties, function);
		if(Boolean.TRUE.equals(deliveryTask.getTask().getWeighable())) {
			if(deliveryTask.getWeightInKiloGram() == null)
				throw new RuntimeException(deliveryTask.getTask().getCode()+" : weight is required");
			__inject__(WeighingBusiness.class).create(new Weighing(deliveryTask,deliveryTask.getWeightInKiloGram()));
		}
		
		if(Boolean.TRUE.equals(deliveryTask.getTask().getProductable())) {
			if(deliveryTask.getProduct() == null)
				throw new RuntimeException(deliveryTask.getTask().getCode()+" : product is required");
			if(deliveryTask.getUnloadingPlace() == null)
				throw new RuntimeException(deliveryTask.getTask().getCode()+" : product unloading place is required");
			if(deliveryTask.getDriver() == null)
				throw new RuntimeException(deliveryTask.getTask().getCode()+" : truck driver is required");
			__inject__(LoadingBusiness.class).create(new Loading(deliveryTask,deliveryTask.getProduct(),deliveryTask.getUnloadingPlace()));
			if(!deliveryTask.getDelivery().getDriver().equals(deliveryTask.getDriver())) {
				deliveryTask.getDelivery().setDriver(deliveryTask.getDriver());
				__inject__(DeliveryPersistence.class).update(deliveryTask.getDelivery());
			}
		}
	}
	
}
