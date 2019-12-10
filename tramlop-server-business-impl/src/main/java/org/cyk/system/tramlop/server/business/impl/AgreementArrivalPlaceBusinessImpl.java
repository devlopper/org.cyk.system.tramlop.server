package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementArrivalPlaceBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementArrivalPlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.PathPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;

@ApplicationScoped
public class AgreementArrivalPlaceBusinessImpl extends AbstractBusinessEntityImpl<AgreementArrivalPlace, AgreementArrivalPlacePersistence> implements AgreementArrivalPlaceBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(AgreementArrivalPlace agreementArrivalPlace, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(agreementArrivalPlace, properties, function);
		if(agreementArrivalPlace.getDurationInMinute() == null) {
			Path path = __inject__(PathPersistence.class).readByDepartureByArrival(agreementArrivalPlace.getAgreement().getDeparturePlace(), agreementArrivalPlace.getPlace());
			if(path != null)
				agreementArrivalPlace.setDurationInMinute(path.getDurationInMinute());
		}
	}
	
}
