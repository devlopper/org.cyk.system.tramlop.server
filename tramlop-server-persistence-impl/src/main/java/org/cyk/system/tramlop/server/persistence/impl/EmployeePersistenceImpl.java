package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.EmployeePersistence;
import org.cyk.system.tramlop.server.persistence.entities.Employee;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class EmployeePersistenceImpl extends AbstractPersistenceEntityImpl<Employee> implements EmployeePersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readWhereNotifiableOnDeliveryDurationAlertIsTrue;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereNotifiableOnDeliveryDurationAlertIsTrue, "SELECT employee FROM Employee employee WHERE employee.notifiableOnDeliveryDurationAlert = true");
	}

	@Override
	public Collection<Employee> readWhereNotifiableOnDeliveryDurationAlertIsTrue() {
		Properties properties = null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereNotifiableOnDeliveryDurationAlertIsTrue);
		return __readMany__(properties, ____getQueryParameters____(properties));
	}
	
}