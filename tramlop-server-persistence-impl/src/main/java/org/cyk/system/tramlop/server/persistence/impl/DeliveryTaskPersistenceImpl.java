package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadDeliveryTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class DeliveryTaskPersistenceImpl extends AbstractPersistenceEntityImpl<DeliveryTask> implements DeliveryTaskPersistence,ReadDeliveryTaskByDeliveriesCodes,Serializable {
	private static final long serialVersionUID = 1L;

private String readByDeliveryCodeByTaskCode,readByDeliveryCodeByTaskOrderNumber,readByDeliveriesCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByDeliveryCodeByTaskCode, "SELECT deliveryTask FROM DeliveryTask deliveryTask WHERE deliveryTask.delivery.code = :deliveryCode AND deliveryTask.task.code = :taskCode ORDER BY deliveryTask.task.orderNumber ASC");
		addQueryCollectInstances(readByDeliveryCodeByTaskOrderNumber, "SELECT deliveryTask FROM DeliveryTask deliveryTask WHERE deliveryTask.delivery.code = :deliveryCode AND deliveryTask.task.orderNumber = :orderNumber ORDER BY deliveryTask.task.orderNumber ASC");
		addQueryCollectInstances(readByDeliveriesCodes, "SELECT deliveryTask FROM DeliveryTask deliveryTask WHERE deliveryTask.delivery.code IN :deliveriesCodes ORDER BY deliveryTask.task.orderNumber ASC");
	}

	@Override
	public DeliveryTask readByDeliveryCodeByTaskCode(String deliveryCode, String taskCode) {
		if(StringHelper.isBlank(deliveryCode) || StringHelper.isBlank(taskCode))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDeliveryCodeByTaskCode);
		return __readOne__(properties, ____getQueryParameters____(properties,deliveryCode,taskCode));
	}
	
	@Override
	public DeliveryTask readByDeliveryCodeByTaskOrderNumber(String deliveryCode, Integer taskOrderNumber) {
		if(StringHelper.isBlank(deliveryCode) || taskOrderNumber == null)
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDeliveryCodeByTaskOrderNumber);
		return __readOne__(properties, ____getQueryParameters____(properties,deliveryCode,taskOrderNumber));
	}
	
	@Override
	public Collection<DeliveryTask> readByDeliveriesCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDeliveriesCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}
	
	@Override
	public DeliveryTask readByDeliveryByTask(Delivery delivery, Task task) {
		if(delivery == null || task == null)
			return null;
		return readByDeliveryCodeByTaskCode(delivery.getCode(), task.getCode());
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDeliveryCodeByTaskCode)) {
			return new Object[]{"deliveryCode",objects[0],"taskCode",objects[1]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDeliveryCodeByTaskOrderNumber)) {
			return new Object[]{"deliveryCode",objects[0],"orderNumber",objects[1]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDeliveriesCodes)) {
			return new Object[]{"deliveriesCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}

	
	
}