package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class DeliveryBusinessImpl extends AbstractBusinessEntityImpl<Delivery, DeliveryPersistence> implements DeliveryBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(Delivery delivery, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(delivery, properties, function);
		if(delivery.getClosed() == null)
			delivery.setClosed(Boolean.FALSE);
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(Delivery delivery, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(delivery, properties, function);
		if(CollectionHelper.isNotEmpty(delivery.getTasks()))
			__inject__(DeliveryTaskBusiness.class).createMany(delivery.getTasks().stream().map(task -> new DeliveryTask(delivery,task)).collect(Collectors.toList()));
	}
	
}
