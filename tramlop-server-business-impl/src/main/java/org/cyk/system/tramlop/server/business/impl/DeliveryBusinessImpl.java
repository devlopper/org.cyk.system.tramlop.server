package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.LoadingPersistence;
import org.cyk.system.tramlop.server.persistence.api.PathPersistence;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.system.tramlop.server.persistence.entities.Path;
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
	public void sendAlertWhereDurationExceed() {
		Collection<Delivery> deliveries = __persistence__.readByClosed(Boolean.FALSE);
		if(CollectionHelper.isEmpty(deliveries))
			return;
		Collection<DeliveryTask> deliveryTasks = __inject__(DeliveryTaskPersistence.class)
				.readByDeliveriesByTasksCodes(deliveries, CollectionHelper.listOf(Task.CODE_WEIGH_AFTER_LOAD,Task.CODE_LOAD,Task.CODE_WEIGH_BEFORE_UNLOAD));
		for(Delivery delivery : deliveries) {
			DeliveryTask load = CollectionHelper.getFirst(deliveryTasks.stream()
					.filter(deliveryTask -> deliveryTask.getDelivery().equals(delivery) && deliveryTask.getTask().getCode().equals(Task.CODE_LOAD))
					.collect(Collectors.toList()));
			if(load == null)
				continue;
			DeliveryTask start = CollectionHelper.getFirst(deliveryTasks.stream()
					.filter(deliveryTask -> deliveryTask.getDelivery().equals(delivery) && deliveryTask.getTask().getCode().equals(Task.CODE_WEIGH_AFTER_LOAD))
					.collect(Collectors.toList()));
			if(start == null || start.getExistence() == null || start.getExistence().getCreationDate() == null)
				continue;
			DeliveryTask end = CollectionHelper.getFirst(deliveryTasks.stream()
					.filter(deliveryTask -> deliveryTask.getDelivery().equals(delivery) && deliveryTask.getTask().getCode().equals(Task.CODE_WEIGH_BEFORE_UNLOAD))
					.collect(Collectors.toList()));
			if(end != null)
				continue;
			Loading loading = __inject__(LoadingPersistence.class).readByDeliveryTask(load);
			if(loading == null)
				continue;
			Path path = __inject__(PathPersistence.class).readByDepartureByArrival(delivery.getAgreement().getDeparturePlace(), loading.getUnloadingPlace());
			if(path == null)
				continue;
			Duration duration = Duration.between(start.getExistence().getCreationDate(), LocalDateTime.now());
			if(duration.toMinutes() > path.getDurationInMinute()) {
				System.out.println("DeliveryBusinessImpl.sendAlertWhereDurationExceed() *********** "+delivery.getCode()+" TIME HAS EXCEED");
			}
		}
	}
	
	@Override
	protected void __listenExecuteCreateBefore__(Delivery delivery, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(delivery, properties, function);
		if(StringHelper.isBlank(delivery.getCode()))
			delivery.setCode(System.currentTimeMillis()+"");
		if(delivery.getClosed() == null)
			delivery.setClosed(Boolean.FALSE);
		if(delivery.getAgreement() == null && delivery.getTruck() != null)
			delivery.setAgreement(CollectionHelper.getFirst(__inject__(AgreementPersistence.class).readWhereAgreementClosedIsFalseExistByTrucksCodes(delivery.getTruck().getCode())));
		if(delivery.getDriver() == null && delivery.getAgreement() != null && delivery.getTruck() != null) {
			AgreementTruck agreementTruck = __inject__(AgreementTruckPersistence.class).readByAgreementByTruck(delivery.getAgreement(), delivery.getTruck());
			if(agreementTruck != null)
				delivery.setDriver(agreementTruck.getDriver());
		}
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
