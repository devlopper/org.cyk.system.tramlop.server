package org.cyk.system.tramlop.server.persistence.impl;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.annotation.System;
import org.cyk.utility.clazz.Classes;
import org.cyk.utility.function.AbstractFunctionWithPropertiesAsInputImpl;
import org.cyk.utility.server.persistence.PersistableClassesGetter;

@Dependent @System
public class PersistableClassesGetterImpl extends AbstractFunctionWithPropertiesAsInputImpl<Classes> implements PersistableClassesGetter, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Classes __execute__() throws Exception {
		Classes classes = __inject__(Classes.class);
		
		classes.add(AgreementTruck.class);
		classes.add(Agreement.class);
		
		classes.add(Customer.class);
		classes.add(Driver.class);
		classes.add(Place.class);
		classes.add(Truck.class);
		
		return classes;
	}
	
}