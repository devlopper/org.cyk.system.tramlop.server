package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementTruckPersistenceImpl extends AbstractPersistenceEntityImpl<AgreementTruck> implements AgreementTruckPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}