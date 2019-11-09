package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class DriverPersistenceImpl extends AbstractPersistenceEntityImpl<Driver> implements DriverPersistence,Serializable {
	private static final long serialVersionUID = 1L;

}