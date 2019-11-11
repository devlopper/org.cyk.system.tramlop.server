package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementProductPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementProductPersistenceImpl extends AbstractPersistenceEntityImpl<AgreementProduct> implements AgreementProductPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}