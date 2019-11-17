package org.cyk.system.tramlop.server.persistence.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.system.tramlop.server.persistence.entities.AgreementPath;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruckSecondaryDriver;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Incident;
import org.cyk.system.tramlop.server.persistence.entities.IncidentResolution;
import org.cyk.system.tramlop.server.persistence.entities.IncidentType;
import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.klass.PersistableClassesGetter;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		PersistableClassesGetter.COLLECTION.set(List.of(
				IncidentResolution.class,Incident.class,Weighing.class,DeliveryTask.class,Delivery.class
				,AgreementArrivalPlace.class,AgreementPath.class,AgreementProduct.class,AgreementTruck.class,AgreementTruckSecondaryDriver.class,Agreement.class
				,Path.class,Customer.class,Driver.class,Place.class,Truck.class,Product.class,Task.class,IncidentType.class));
		__inject__(org.cyk.utility.server.persistence.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
		__inject__(org.cyk.system.tramlop.server.persistence.entities.ApplicationScopeLifeCycleListener.class).initialize(null);
		
	}
	
	@Override
	public void __destroy__(Object object) {}
	
}
