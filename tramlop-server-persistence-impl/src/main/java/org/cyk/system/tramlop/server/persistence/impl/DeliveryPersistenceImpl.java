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
import org.cyk.system.tramlop.server.persistence.api.query.ReadDeliveryByAgreements;
import org.cyk.system.tramlop.server.persistence.api.query.ReadDeliveryTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.number.NumberHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class DeliveryPersistenceImpl extends AbstractPersistenceEntityImpl<Delivery> implements DeliveryPersistence,ReadDeliveryByAgreements,Serializable {
	private static final long serialVersionUID = 1L;

	private static final String READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_BY_TRUCKS_CODES_FORMAT = "SELECT delivery FROM Delivery delivery WHERE delivery.closed = %s AND "
			+ " delivery.truck.code IN :trucksCodes";
	
	private String readByAgreementsCodes,readWhereDeliveryClosedIsFalseExistByTrucksCodes,readByAgreementsCodesByClosed,countByAgreementsCodesByClosed,readView;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementsCodes, "SELECT delivery FROM Delivery delivery WHERE delivery.agreement.code = :agreementsCodes");
		addQueryCollectInstances(readWhereDeliveryClosedIsFalseExistByTrucksCodes, String.format(READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_BY_TRUCKS_CODES_FORMAT, "false"));
		addQueryCollectInstances(readByAgreementsCodesByClosed, "SELECT delivery FROM Delivery delivery WHERE delivery.agreement.code IN :agreementsCodes AND delivery.closed = :closed");
		addQuery(readView, "SELECT new  org.cyk.system.tramlop.server.persistence.entities.Delivery(delivery.identifier,truck.code,CONCAT(driver.person.firstName,' ',driver.person.lastNames)" + 
				"	,product.name,place.name" + 
				"	,weighing_before_load.weightInKiloGram,weighing_after_load.weightInKiloGram,weighing_after_load.weightInKiloGram - weighing_before_load.weightInKiloGram" + 
				"	,weighing_before_unload.weightInKiloGram,weighing_after_unload.weightInKiloGram,weighing_before_unload.weightInKiloGram - weighing_after_unload.weightInKiloGram" + 
				"	,(weighing_after_load.weightInKiloGram - weighing_before_load.weightInKiloGram)-(weighing_before_unload.weightInKiloGram - weighing_after_unload.weightInKiloGram)" + 
				"	,delivery.closed)" + 
				
				" FROM Delivery delivery,Truck truck,Driver driver,Agreement agreement,AgreementProduct agreementproduct,Product product,Place place" + 
				"	,DeliveryTask deliverytask_before_load,Task task_before_load,Weighing weighing_before_load" + 
				"   ,DeliveryTask deliverytask_load,Task task_load,Loading loading" + 
				"	,DeliveryTask deliverytask_after_load,Task as task_after_load,Weighing as weighing_after_load" + 
				"	,DeliveryTask deliverytask_before_unload,Task as task_before_unload,Weighing as weighing_before_unload" + 
				"	,DeliveryTask deliverytask_after_unload,Task as task_after_unload,Weighing as weighing_after_unload" + 

				" WHERE delivery.truck = truck.identifier " + 
				"  AND delivery.driver = driver.identifier" + 
				"  AND delivery.agreement = agreement.identifier" + 
				"  AND agreement.identifier = agreementproduct.agreement" + 
				"  AND product.identifier = agreementproduct.product" + 
				"  AND delivery.identifier = deliverytask_load.delivery" + 
				"  " + 
				"  AND deliverytask_before_load.delivery = delivery.identifier" + 
				"  AND deliverytask_before_load.task = task_before_load.identifier" + 
				"  AND task_before_load.orderNumber = 1" + 
				"  AND weighing_before_load.deliveryTask = deliverytask_before_load.identifier" + 
				"  " + 
				"  AND deliverytask_load.delivery = delivery.identifier" + 
				"  AND deliverytask_load.task = task_load.identifier" + 
				"  AND task_load.orderNumber = 2" + 
				"  AND loading.deliveryTask = deliverytask_load.identifier" + 
				"  AND loading.product = product.identifier" + 
				"  AND loading.unloadingPlace = place.identifier" + 
				"  " + 
				"  AND deliverytask_after_load.delivery = delivery.identifier" + 
				"  AND deliverytask_after_load.task = task_after_load.identifier" + 
				"  AND task_after_load.orderNumber = 3" + 
				"  AND weighing_after_load.deliveryTask = deliverytask_after_load.identifier" + 
				"  " + 
				"  AND deliverytask_before_unload.delivery = delivery.identifier" + 
				"  AND deliverytask_before_unload.task = task_before_unload.identifier" + 
				"  AND task_before_unload.orderNumber = 4" + 
				"  AND weighing_before_unload.deliveryTask = deliverytask_before_unload.identifier" + 
				"  " + 
				"  AND deliverytask_after_unload.delivery = delivery.identifier" + 
				"  AND deliverytask_after_unload.task = task_after_unload.identifier" + 
				"  AND task_after_unload.orderNumber = 5" + 
				"  AND weighing_after_unload.deliveryTask = deliverytask_after_unload.identifier",Delivery.class);
	}
	
	@Override
	public Collection<Delivery> readByAgreementsCodes(Collection<String> agreementsCodes, Properties properties) {
		if(CollectionHelper.isEmpty(agreementsCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,agreementsCodes));
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
	public Collection<Delivery> readByAgreementsCodesByClosed(Collection<String> agreementsCodes, Boolean closed,Properties properties) {
		if(CollectionHelper.isEmpty(agreementsCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodesByClosed);
		return __readMany__(properties, ____getQueryParameters____(properties,agreementsCodes,closed));
	}
	
	@Override
	public Long countByAgreementsCodesByClosed(Collection<String> agreementsCodes, Boolean closed,Properties properties) {
		if(CollectionHelper.isEmpty(agreementsCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, countByAgreementsCodesByClosed);
		return __count__(properties, ____getQueryParameters____(properties,agreementsCodes,closed));
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldsValues__(Delivery delivery, Strings fieldsNames, Properties properties) {
		super.__listenExecuteReadAfterSetFieldsValues__(delivery, fieldsNames, properties);
		if(CollectionHelper.contains(fieldsNames, Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_LOAD)) {
			if(CollectionHelper.getSize(delivery.getTasks()) > 2) {
				setTasksIfNull(delivery);
				delivery.setWeightInKiloGramOfProductAfterLoad(NumberHelper.getInteger(NumberHelper.subtract(CollectionHelper.getElementAt(delivery.getTasks(), 2).getWeightInKiloGram()
						- CollectionHelper.getElementAt(delivery.getTasks(), 0).getWeightInKiloGram())));
			}
		}
		if(CollectionHelper.contains(fieldsNames,Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_UNLOAD)) {			
			if(CollectionHelper.getSize(delivery.getTasks()) > 4) {
				setTasksIfNull(delivery);
				delivery.setWeightInKiloGramOfProductAfterUnload(CollectionHelper.getElementAt(delivery.getTasks(), 3).getWeightInKiloGram()
						- CollectionHelper.getElementAt(delivery.getTasks(), 4).getWeightInKiloGram());
			}
		}
		if(CollectionHelper.contains(fieldsNames,Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOST)) {
			if(CollectionHelper.getSize(delivery.getTasks()) > 4) {
				setTasksIfNull(delivery);
				delivery.setWeightInKiloGramOfProductLost(delivery.getWeightInKiloGramOfProductAfterLoad()-delivery.getWeightInKiloGramOfProductAfterUnload());
			}
		}
		if(CollectionHelper.contains(fieldsNames,Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOSTABLE)) {
			if(CollectionHelper.getSize(delivery.getTasks()) > 4) {
				setTasksIfNull(delivery);
				delivery.setWeightInKiloGramOfProductLostable(NumberHelper.getInteger(NumberHelper.multiply(delivery.getWeightInKiloGramOfProductAfterLoad()
						,CollectionHelper.getElementAt(delivery.getTasks(), 1).getProduct().getLossRate())));
			}
		}
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(Delivery delivery, Field field, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(delivery, field, properties);
		if(field.getName().equals(Delivery.FIELD_TASKS)) {
			Collection<DeliveryTask> deliveryTasks = ((ReadDeliveryTaskByDeliveriesCodes)__inject__(DeliveryTaskPersistence.class)).readByDeliveries(delivery);
			if(CollectionHelper.isNotEmpty(deliveryTasks))
				delivery.setTasks(deliveryTasks.stream().map(deliveryTask -> deliveryTask.getTask().setExistence(deliveryTask.getExistence())).collect(Collectors.toList()));
		}
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(Delivery delivery, String fieldName, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(delivery, fieldName, properties);
		if(fieldName.startsWith(Delivery.FIELD_TASKS+".")) {
			setTasksIfNull(delivery);
		}
		if((Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE).equals(fieldName)) {						
			if(CollectionHelper.isNotEmpty(delivery.getTasks())){
				for(Task task : delivery.getTasks()) {
					DeliveryTask deliveryTask = __inject__(DeliveryTaskPersistence.class).readByDeliveryByTask(delivery, task);
					if(deliveryTask != null)
						task.setExistence(deliveryTask.getExistence());
				}
			}
		}else if((Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM).equals(fieldName)) {
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
		}else if((Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE).equals(fieldName)) {
			if(CollectionHelper.isNotEmpty(delivery.getTasks())){
				for(Task task : delivery.getTasks()) {
					DeliveryTask deliveryTask = __inject__(DeliveryTaskPersistence.class).readByDeliveryByTask(delivery, task);
					if(deliveryTask != null && Boolean.TRUE.equals(deliveryTask.getTask().getProductable())) {
						Loading loading = __inject__(LoadingPersistence.class).readByDeliveryByTask(delivery, task);
						if(loading != null)
							task.setUnloadingPlace(loading.getUnloadingPlace());
					}
				}
			}
		}
	}
	
	private void setTasksIfNull(Delivery delivery) {
		if(delivery.getTasks() == null) {
			Collection<DeliveryTask> deliveryTasks = ((ReadDeliveryTaskByDeliveriesCodes)__inject__(DeliveryTaskPersistence.class)).readByDeliveries(delivery);
			if(CollectionHelper.isNotEmpty(deliveryTasks))
				delivery.setTasks(deliveryTasks.stream().map(DeliveryTask::getTask).collect(Collectors.toList()));
		}
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> klass, Properties properties, Object... objects) {
		if(PersistenceFunctionReader.class.equals(klass)) {
			if(__isFilterByKeys__(properties, Delivery.FIELD_TRUCKS) && __isFilterByKeys__(properties, Delivery.FIELD_CLOSED))
				return readWhereDeliveryClosedIsFalseExistByTrucksCodes;
			else if(__isFilterByKeys__(properties, Delivery.FIELD_AGREEMENT))
				return readByAgreementsCodes;
		}
		return super.__getQueryIdentifier__(klass, properties, objects);
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereDeliveryClosedIsFalseExistByTrucksCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Delivery.FIELD_TRUCKS)};
			return new Object[]{"trucksCodes",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Delivery.FIELD_AGREEMENT)};
			return new Object[]{"agreementsCodes",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodesByClosed)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Delivery.FIELD_AGREEMENT),queryContext.getFilterByKeysValue(Delivery.FIELD_CLOSED)};
			return new Object[]{"agreementsCodes",objects[0],"closed",objects[1]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}