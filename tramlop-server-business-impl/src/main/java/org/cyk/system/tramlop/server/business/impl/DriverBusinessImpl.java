package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.DriverBusiness;
import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class DriverBusinessImpl extends AbstractBusinessEntityImpl<Driver, DriverPersistence> implements DriverBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
