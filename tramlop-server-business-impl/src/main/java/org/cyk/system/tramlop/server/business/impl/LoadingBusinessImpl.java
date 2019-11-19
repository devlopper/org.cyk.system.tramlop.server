package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.LoadingBusiness;
import org.cyk.system.tramlop.server.persistence.api.LoadingPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Loading;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class LoadingBusinessImpl extends AbstractBusinessEntityImpl<Loading, LoadingPersistence> implements LoadingBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
