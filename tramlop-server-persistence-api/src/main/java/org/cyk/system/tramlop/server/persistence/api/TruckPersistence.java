package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.QueryIdentifierBuilder;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface TruckPersistence extends PersistenceEntity<Truck> {

	/* get by agreement */
	
	Collection<Truck> readWhereAgreementClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExist() {
		return readWhereAgreementClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist() {
		return readWhereAgreementClosedIsFalseDoesNotExist(null);
	}
	
	/* get by delivery */
	
	Collection<Truck> readWhereDeliveryClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereDeliveryClosedIsFalseExist() {
		return readWhereDeliveryClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereDeliveryClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereDeliveryClosedIsFalseDoesNotExist() {
		return readWhereDeliveryClosedIsFalseDoesNotExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist() {
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(null);
	}
	
	Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist() {
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(null);
	}
	
	/*
	Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(Collection<String> tasksCodes,Properties properties);
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(Collection<String> tasksCodes) {
		if(CollectionHelper.isEmpty(tasksCodes))
			return null;
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(tasksCodes,null);
	}
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(Properties properties,String...tasksCodes) {
		if(ArrayHelper.isEmpty(tasksCodes))
			return null;
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(CollectionHelper.listOf(tasksCodes),properties);
	}
	
	default Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(String...tasksCodes) {
		if(ArrayHelper.isEmpty(tasksCodes))
			return null;
		return readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExistAndTaskOrderNumberIsHighestByTasksCodes(CollectionHelper.listOf(tasksCodes),null);
	}
	*/
	
	Collection<Truck> readByTasksCounts(Collection<Integer> tasksCounts,Properties properties);
	
	default Collection<Truck> readByTasksCounts(Collection<Integer> tasksCounts) {
		if(CollectionHelper.isEmpty(tasksCounts))
			return null;
		return readByTasksCounts(tasksCounts,null);
	}
	
	default Collection<Truck> readByTasksCounts(Properties properties,Integer...tasksCounts) {
		if(ArrayHelper.isEmpty(tasksCounts))
			return null;
		return readByTasksCounts(CollectionHelper.listOf(tasksCounts),properties);
	}
	
	default Collection<Truck> readByTasksCounts(Integer...tasksCounts) {
		if(ArrayHelper.isEmpty(tasksCounts))
			return null;
		return readByTasksCounts(CollectionHelper.listOf(tasksCounts),null);
	}
	
	/**/
	
	String READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST 
		= QueryIdentifierBuilder.getInstance().build(Truck.class,"readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist");

	String READ_BY_TASKS_COUNTS = QueryIdentifierBuilder.getInstance().build(Truck.class,"readByTasksCounts");
}
