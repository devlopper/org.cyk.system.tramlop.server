package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementTruckBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class AgreementTruckBusinessImpl extends AbstractBusinessEntityImpl<AgreementTruck, AgreementTruckPersistence> implements AgreementTruckBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
