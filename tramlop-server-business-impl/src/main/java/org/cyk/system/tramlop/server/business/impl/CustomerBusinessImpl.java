package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.CustomerBusiness;
import org.cyk.system.tramlop.server.persistence.api.CustomerPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class CustomerBusinessImpl extends AbstractBusinessEntityImpl<Customer, CustomerPersistence> implements CustomerBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
