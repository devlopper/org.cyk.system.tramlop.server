package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementTruckSecondaryDriverPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruckSecondaryDriver;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementTruckSecondaryDriverPersistenceImpl extends AbstractPersistenceEntityImpl<AgreementTruckSecondaryDriver> implements AgreementTruckSecondaryDriverPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}