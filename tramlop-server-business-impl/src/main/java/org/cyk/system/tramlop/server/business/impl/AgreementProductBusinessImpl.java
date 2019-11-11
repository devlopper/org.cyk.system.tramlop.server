package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementProductBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementProductPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class AgreementProductBusinessImpl extends AbstractBusinessEntityImpl<AgreementProduct, AgreementProductPersistence> implements AgreementProductBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
