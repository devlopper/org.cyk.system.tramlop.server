package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.LoadingPersistence;
import org.cyk.system.tramlop.server.persistence.api.WeighingPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadDeliveryTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class DeliveryPersistenceImpl extends AbstractPersistenceEntityImpl<Delivery> implements DeliveryPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private static final String READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_BY_TRUCKS_CODES_FORMAT = "SELECT delivery FROM Delivery delivery WHERE delivery.closed = %s AND "
			+ " delivery.truck.code IN :trucksCodes";
	
	
	private String readWhereDeliveryClosedIsFalseExistByTrucksCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereDeliveryClosedIsFalseExistByTrucksCodes, String.format(READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_BY_TRUCKS_CODES_FORMAT, "false"));
	}
	
	@Override
	public Collection<Delivery> readWhereDeliveryClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes,Properties properties) {
		if(CollectionHelper.isEmpty(trucksCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereDeliveryClosedIsFalseExistByTrucksCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,trucksCodes));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereDeliveryClosedIsFalseExistByTrucksCodes)) {
			return new Object[]{"trucksCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(Delivery delivery, Field field, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(delivery, field, properties);
		if(field.getName().equals(Delivery.FIELD_TASKS)) {
			Collection<DeliveryTask> deliveryTasks = ((ReadDeliveryTaskByDeliveriesCodes)__inject__(DeliveryTaskPersistence.class)).readByDeliveries(delivery);
			if(CollectionHelper.isNotEmpty(deliveryTasks))
				delivery.setTasks(deliveryTasks.stream().map(DeliveryTask::getTask).collect(Collectors.toList()));
		}else {
			
		}
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(Delivery delivery, String fieldName, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(delivery, fieldName, properties);
		if((Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE).equals(fieldName)) {
			if(delivery.getTasks() == null) {
				Collection<DeliveryTask> deliveryTasks = ((ReadDeliveryTaskByDeliveriesCodes)__inject__(DeliveryTaskPersistence.class)).readByDeliveries(delivery);
				if(CollectionHelper.isNotEmpty(deliveryTasks))
					delivery.setTasks(deliveryTasks.stream().map(DeliveryTask::getTask).collect(Collectors.toList()));
			}				
			if(CollectionHelper.isNotEmpty(delivery.getTasks())){
				for(Task task : delivery.getTasks()) {
					DeliveryTask deliveryTask = __inject__(DeliveryTaskPersistence.class).readByDeliveryByTask(delivery, task);
					if(deliveryTask != null)
						task.setExistence(deliveryTask.getExistence());
				}
			}
		}else if((Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM).equals(fieldName)) {
			if(delivery.getTasks() == null) {
				Collection<DeliveryTask> deliveryTasks = ((ReadDeliveryTaskByDeliveriesCodes)__inject__(DeliveryTaskPersistence.class)).readByDeliveries(delivery);
				if(CollectionHelper.isNotEmpty(deliveryTasks))
					delivery.setTasks(deliveryTasks.stream().map(DeliveryTask::getTask).collect(Collectors.toList()));
			}				
			if(CollectionHelper.isNotEmpty(delivery.getTasks())){
				for(Task task : delivery.getTasks()) {
					DeliveryTask deliveryTask = __inject__(DeliveryTaskPersistence.class).readByDeliveryByTask(delivery, task);
					if(deliveryTask != null && Boolean.TRUE.equals(deliveryTask.getTask().getWeighable())) {
						Weighing weighing = __inject__(WeighingPersistence.class).readByDeliveryByTask(delivery, task);
						if(weighing != null)
							task.setWeightInKiloGram(weighing.getWeightInKiloGram());
					}
				}
			}
		}else if((Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT).equals(fieldName)) {
			if(delivery.getTasks() == null) {
				Collection<DeliveryTask> deliveryTasks = ((ReadDeliveryTaskByDeliveriesCodes)__inject__(DeliveryTaskPersistence.class)).readByDeliveries(delivery);
				if(CollectionHelper.isNotEmpty(deliveryTasks))
					delivery.setTasks(deliveryTasks.stream().map(DeliveryTask::getTask).collect(Collectors.toList()));
			}				
			if(CollectionHelper.isNotEmpty(delivery.getTasks())){
				for(Task task : delivery.getTasks()) {
					DeliveryTask deliveryTask = __inject__(DeliveryTaskPersistence.class).readByDeliveryByTask(delivery, task);
					if(deliveryTask != null && Boolean.TRUE.equals(deliveryTask.getTask().getProductable())) {
						Loading loading = __inject__(LoadingPersistence.class).readByDeliveryByTask(delivery, task);
						if(loading != null)
							task.setProduct(loading.getProduct());
					}
				}
			}
		}
	}
}