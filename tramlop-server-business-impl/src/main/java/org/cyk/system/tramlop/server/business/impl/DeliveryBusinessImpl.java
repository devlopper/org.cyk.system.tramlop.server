package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionRemover;

@ApplicationScoped
public class DeliveryBusinessImpl extends AbstractBusinessEntityImpl<Delivery, DeliveryPersistence> implements DeliveryBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(Delivery delivery, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(delivery, properties, function);
		if(StringHelper.isBlank(delivery.getCode()))
			delivery.setCode(System.currentTimeMillis()+"");
		if(delivery.getClosed() == null)
			delivery.setClosed(Boolean.FALSE);
		if(delivery.getAgreement() == null && delivery.getTruck() != null)
			delivery.setAgreement(CollectionHelper.getFirst(__inject__(AgreementPersistence.class).readWhereAgreementClosedIsFalseExistByTrucksCodes(delivery.getTruck().getCode())));
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(Delivery delivery, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(delivery, properties, function);
		Collection<Task> tasks = delivery.getTasks();
		if(CollectionHelper.isEmpty(tasks)) {
			tasks = new ArrayList<>();
			tasks.add(__inject__(TaskPersistence.class).readByOrderNumber(1).setWeightInKiloGram(delivery.getWeightInKiloGram()));
		}
		Task taskPeseeAVideAvantCharge = CollectionHelper.getFirst(tasks.stream().filter(x -> x.getOrderNumber().equals(1)).collect(Collectors.toList()));
		if(taskPeseeAVideAvantCharge == null)
			throw new RuntimeException("La livraison doit commencer par la tache suivante : "+__inject__(TaskPersistence.class).readByOrderNumber(1).getName());
		__inject__(DeliveryTaskBusiness.class).createMany(tasks.stream().map(task -> new DeliveryTask(delivery,task,task.getWeightInKiloGram())
				.setProduct(task.getProduct()).setUnloadingPlace(task.getUnloadingPlace()))
				.collect(Collectors.toList()));
	
	}
	
	@Override
	protected void __listenExecuteDeleteBefore__(Delivery delivery, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteBefore__(delivery, properties, function);
		
	}
}
