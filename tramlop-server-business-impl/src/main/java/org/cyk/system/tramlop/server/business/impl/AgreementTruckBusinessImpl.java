package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementTruckBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class AgreementTruckBusinessImpl extends AbstractBusinessEntityImpl<AgreementTruck, AgreementTruckPersistence> implements AgreementTruckBusiness,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenExecuteCreateBefore__(AgreementTruck agreementTruck, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(agreementTruck, properties, function);
		if(agreementTruck.getAgreement() != null && agreementTruck.getTruck() != null) {
			Collection<Agreement> agreements = __inject__(AgreementPersistence.class).readWhereAgreementClosedIsFalseExistByTrucks(agreementTruck.getTruck());
			if(CollectionHelper.isNotEmpty(agreements))
				throw new RuntimeException("Le camion "+agreementTruck.getTruck().getCode()+" est déja associé à un contract"+agreements+" qui est en cours.");
			if(Boolean.TRUE.equals(agreementTruck.getAgreement().getClosed()))
				throw new RuntimeException("Le camion "+agreementTruck.getTruck().getCode()+" ne peut pas être associé au contrat "+agreementTruck.getAgreement().getCode()+" qui est déja clos.");	
		}		
	}
	
}
