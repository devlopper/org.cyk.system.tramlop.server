package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.PathPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class PathPersistenceImpl extends AbstractPersistenceEntityImpl<Path> implements PathPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByDepartureCodeByArrivalCode;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByDepartureCodeByArrivalCode, "SELECT path FROM Path path WHERE path.departure.code = :departureCode AND path.arrival.code = :arrivalCode");
	}
	
	@Override
	public Path readByDepartureCodeByArrivalCode(String departureCode, String arrivalCode) {
		if(StringHelper.isBlank(departureCode) || StringHelper.isBlank(arrivalCode))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByDepartureCodeByArrivalCode);
		return __readOne__(properties, ____getQueryParameters____(properties,departureCode,arrivalCode));
	}
	
	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByDepartureCodeByArrivalCode)) {
			return new Object[]{"departureCode",objects[0],"arrivalCode",objects[1]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}

}