package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementPersistenceImpl extends AbstractPersistenceEntityImpl<Agreement> implements AgreementPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}