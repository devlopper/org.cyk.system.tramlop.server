package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class DeliveryBusinessImpl extends AbstractBusinessEntityImpl<Delivery, DeliveryPersistence> implements DeliveryBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
