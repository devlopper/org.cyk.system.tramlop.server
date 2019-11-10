package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementArrivalPlaceBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementArrivalPlacePersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class AgreementArrivalPlaceBusinessImpl extends AbstractBusinessEntityImpl<AgreementArrivalPlace, AgreementArrivalPlacePersistence> implements AgreementArrivalPlaceBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
