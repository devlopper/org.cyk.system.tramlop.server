package org.cyk.system.tramlop.server.business.impl;
import javax.ejb.Singleton;

import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.utility.__kernel__.DependencyInjection;
 
@Singleton
public class Scheduler {
 
    //@javax.ejb.Schedule(second = "0", minute = "*/1", hour = "*", persistent = false)
    public void checkDeliveriesDuration() throws InterruptedException {
    	DependencyInjection.inject(DeliveryBusiness.class).sendAlertWhereDurationExceed();
    }
}