package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class DeliveryTaskBusinessImpl extends AbstractBusinessEntityImpl<DeliveryTask, DeliveryTaskPersistence> implements DeliveryTaskBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
