package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.AgreementTruckBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class AgreementBusinessImpl extends AbstractBusinessEntityImpl<Agreement, AgreementPersistence> implements AgreementBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(Agreement agreement, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(agreement, properties, function);
		if(agreement.getClosed() == null)
			agreement.setClosed(Boolean.FALSE);
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(Agreement agreement, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(agreement, properties, function);
		if(CollectionHelper.isNotEmpty(agreement.getTrucks()))
			__inject__(AgreementTruckBusiness.class).createMany(agreement.getTrucks().stream().map(truck -> new AgreementTruck(agreement,truck,truck.getDriver())).collect(Collectors.toList()));
	}
	
}
