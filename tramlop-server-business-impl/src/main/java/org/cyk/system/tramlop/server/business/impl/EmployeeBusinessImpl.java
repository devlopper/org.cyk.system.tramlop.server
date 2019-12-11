package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.EmployeeBusiness;
import org.cyk.system.tramlop.server.persistence.api.EmployeePersistence;
import org.cyk.system.tramlop.server.persistence.entities.Employee;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;

@ApplicationScoped
public class EmployeeBusinessImpl extends AbstractBusinessEntityImpl<Employee, EmployeePersistence> implements EmployeeBusiness,Serializable {
	private static final long serialVersionUID = 1L;
		
}
