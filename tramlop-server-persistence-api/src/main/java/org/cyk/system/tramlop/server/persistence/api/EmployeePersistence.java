package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Employee;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface EmployeePersistence extends PersistenceEntity<Employee> {

	Collection<Employee> readWhereNotifiableOnDeliveryDurationAlertIsTrue();
	
}
