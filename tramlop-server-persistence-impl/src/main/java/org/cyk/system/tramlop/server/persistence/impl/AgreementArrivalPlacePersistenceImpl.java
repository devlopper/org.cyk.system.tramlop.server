package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementArrivalPlacePersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementArrivalPlacePersistenceImpl extends AbstractPersistenceEntityImpl<AgreementArrivalPlace> implements AgreementArrivalPlacePersistence,Serializable {
	private static final long serialVersionUID = 1L;

}