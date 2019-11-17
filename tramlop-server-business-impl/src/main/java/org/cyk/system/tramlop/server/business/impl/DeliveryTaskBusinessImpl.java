package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.business.api.WeighingBusiness;
import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class DeliveryTaskBusinessImpl extends AbstractBusinessEntityImpl<DeliveryTask, DeliveryTaskPersistence> implements DeliveryTaskBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(DeliveryTask deliveryTask, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(deliveryTask, properties, function);
		if(deliveryTask.getTask().getOrderNumber() > 1) {
			Task previousTask = __inject__(TaskPersistence.class).readByOrderNumber(deliveryTask.getTask().getOrderNumber() - 1);
			if(__persistence__.readByDeliveryCodeByTaskOrderNumber(deliveryTask.getDelivery().getCode(), previousTask.getOrderNumber()) == null)
				;//throw new RuntimeException("La réalisation de la tache <<"+previousTask.getName()+">> est obligatoire.");
		}
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(DeliveryTask deliveryTask, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(deliveryTask, properties, function);
		if(deliveryTask.getWeightInKiloGram() != null && deliveryTask.getTask().getCode().contains("PESEE"))
			__inject__(WeighingBusiness.class).create(new Weighing(deliveryTask,deliveryTask.getWeightInKiloGram()));
		if(deliveryTask.getTask().getCode().contentEquals(Task.CODE_PESE_CHARGE)) {
			create(new DeliveryTask(null, deliveryTask.getDelivery().getCode(), Task.CODE_DEPART));
		}else if(deliveryTask.getTask().getCode().contentEquals(Task.CODE_ARRIVEE)) {
			create(new DeliveryTask(null, deliveryTask.getDelivery().getCode(), Task.CODE_PESE_DECHARGE).setWeightInKiloGram(deliveryTask.getWeightInKiloGram()));
			create(new DeliveryTask(null, deliveryTask.getDelivery().getCode(), Task.CODE_DECHARGE));
		}
	}
	
}
