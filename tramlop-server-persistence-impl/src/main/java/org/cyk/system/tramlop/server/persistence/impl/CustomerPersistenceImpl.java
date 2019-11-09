package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.CustomerPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class CustomerPersistenceImpl extends AbstractPersistenceEntityImpl<Customer> implements CustomerPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}