package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementPathBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementPathPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementPath;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class AgreementPathBusinessImpl extends AbstractBusinessEntityImpl<AgreementPath, AgreementPathPersistence> implements AgreementPathBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
