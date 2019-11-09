package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementPathPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementPath;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementPathPersistenceImpl extends AbstractPersistenceEntityImpl<AgreementPath> implements AgreementPathPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}