package org.cyk.system.tramlop.server.persistence.api;

import java.util.Collection;

import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.utility.__kernel__.persistence.QueryIdentifierBuilder;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface DriverPersistence extends PersistenceEntity<Driver> {

	Collection<Driver> readWhereNotExistAgreementClosedIsFalse();
	
	String READ_WHERE_NOT_EXIST_AGREEMENT_CLOSED_IS_FALSE = QueryIdentifierBuilder.getInstance().build(Driver.class,"readWhereNotExistAgreementClosedIsFalse");
	
}
