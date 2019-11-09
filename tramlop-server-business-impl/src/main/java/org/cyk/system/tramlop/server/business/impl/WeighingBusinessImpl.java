package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.WeighingBusiness;
import org.cyk.system.tramlop.server.persistence.api.WeighingPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class WeighingBusinessImpl extends AbstractBusinessEntityImpl<Weighing, WeighingPersistence> implements WeighingBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
