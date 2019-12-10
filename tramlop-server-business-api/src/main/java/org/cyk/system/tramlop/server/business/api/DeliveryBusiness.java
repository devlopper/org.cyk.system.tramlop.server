package org.cyk.system.tramlop.server.business.api;

import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.utility.server.business.BusinessEntity;

public interface DeliveryBusiness extends BusinessEntity<Delivery> {

	void sendAlertWhereDurationExceed();
	
}
