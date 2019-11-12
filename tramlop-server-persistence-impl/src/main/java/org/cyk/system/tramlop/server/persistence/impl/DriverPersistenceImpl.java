package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class DriverPersistenceImpl extends AbstractPersistenceEntityImpl<Driver> implements DriverPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readWhereNotExistAgreementClosedIsFalse;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereNotExistAgreementClosedIsFalse, "SELECT tuple FROM Driver tuple");
	}
	
	@Override
	public Collection<Driver> readWhereNotExistAgreementClosedIsFalse() {
		return read(new Properties().setQueryIdentifier(readWhereNotExistAgreementClosedIsFalse));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(READ_WHERE_NOT_EXIST_AGREEMENT_CLOSED_IS_FALSE))
			return null;
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}