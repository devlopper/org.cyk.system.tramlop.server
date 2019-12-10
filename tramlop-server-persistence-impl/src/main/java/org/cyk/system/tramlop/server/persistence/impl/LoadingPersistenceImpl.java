package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.LoadingPersistence;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class LoadingPersistenceImpl extends AbstractPersistenceEntityImpl<Loading> implements LoadingPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByDeliveryCodeByTaskCode,readByDeliveryTask;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByDeliveryCodeByTaskCode, "SELECT loading FROM Loading loading WHERE loading.deliveryTask.delivery.code = :deliveryCode AND loading.deliveryTask.task.code = :taskCode");
		addQueryCollectInstances(readByDeliveryTask, "SELECT loading FROM Loading loading WHERE loading.deliveryTask.identifier = :deliveryTaskIdentifier");
	}

	@Override
	public Loading readByDeliveryCodeByTaskCode(String deliveryCode, String taskCode) {
		if(StringHelper.isBlank(deliveryCode) || StringHelper.isBlank(taskCode))
			return null;
		Properties properties = null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDeliveryCodeByTaskCode);
		return __readOne__(properties, ____getQueryParameters____(properties,deliveryCode,taskCode));
	}
	
	@Override
	public Loading readByDeliveryTask(DeliveryTask deliveryTask) {
		if(deliveryTask == null)
			return null;
		Properties properties = null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDeliveryTask);
		return __readOne__(properties, ____getQueryParameters____(properties,deliveryTask.getIdentifier()));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDeliveryCodeByTaskCode)) {
			return new Object[]{"deliveryCode",objects[0],"taskCode",objects[1]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDeliveryTask)) {
			return new Object[]{"deliveryTaskIdentifier",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}