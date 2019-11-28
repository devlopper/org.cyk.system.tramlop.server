package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementTruckSecondaryDriverBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckSecondaryDriverPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruckSecondaryDriver;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class AgreementTruckSecondaryDriverBusinessImpl extends AbstractBusinessEntityImpl<AgreementTruckSecondaryDriver, AgreementTruckSecondaryDriverPersistence> implements AgreementTruckSecondaryDriverBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
