package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class TruckBusinessImpl extends AbstractBusinessEntityImpl<Truck, TruckPersistence> implements TruckBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
