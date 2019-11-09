package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class AgreementBusinessImpl extends AbstractBusinessEntityImpl<Agreement, AgreementPersistence> implements AgreementBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
