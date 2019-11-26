package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class TaskPersistenceImpl extends AbstractPersistenceEntityImpl<Task> implements TaskPersistence,ReadTaskByDeliveriesCodes,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByDeliveriesCodes,readByOrderNumber;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByDeliveriesCodes, "SELECT task FROM Task task WHERE"
				+ " EXISTS(SELECT deliveryTask FROM DeliveryTask deliveryTask WHERE deliveryTask.task = task AND deliveryTask.delivery.code IN :deliveriesCodes)");
		addQueryCollectInstances(readByOrderNumber, "SELECT task FROM Task task WHERE task.orderNumber = :orderNumber");
	}
	
	@Override
	public Collection<Task> readByDeliveriesCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDeliveriesCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}
	
	@Override
	public Task readByOrderNumber(Integer orderNumber) {
		if(orderNumber == null)
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByOrderNumber);
		return __readOne__(properties, ____getQueryParameters____(properties,orderNumber));
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> klass, Properties properties, Object... objects) {
		if(PersistenceFunctionReader.class.equals(klass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, Task.FIELD_DELIVERIES)))
				return readByDeliveriesCodes;
		}
		return super.__getQueryIdentifier__(klass, properties, objects);
	}

	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDeliveriesCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Task.FIELD_DELIVERIES)};
			return new Object[]{"deliveriesCodes",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByOrderNumber)) {
			return new Object[]{"orderNumber",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}