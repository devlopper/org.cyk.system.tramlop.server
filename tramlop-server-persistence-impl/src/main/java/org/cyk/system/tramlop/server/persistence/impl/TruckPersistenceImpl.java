package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class TruckPersistenceImpl extends AbstractPersistenceEntityImpl<Truck> implements TruckPersistence,ReadTruckByAgreementsCodes,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByAgreementsCodes,readWhereAgreementClosedIsFalseExist,readWhereAgreementClosedIsFalseDoesNotExist;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementsCodes, "SELECT tuple FROM Truck tuple WHERE"
				+ " EXISTS(SELECT subTuple FROM AgreementTruck subTuple WHERE subTuple.truck = tuple AND subTuple.agreement.code IN :agreementsCodes)");
		addQueryCollectInstances(readWhereAgreementClosedIsFalseExist, "SELECT tuple FROM Truck tuple WHERE"
				+ " EXISTS(SELECT subTuple FROM AgreementTruck subTuple WHERE subTuple.truck = tuple AND subTuple.agreement.closed = false)");
		addQueryCollectInstances(readWhereAgreementClosedIsFalseDoesNotExist, "SELECT tuple FROM Truck tuple WHERE"
				+ " NOT EXISTS(SELECT subTuple FROM AgreementTruck subTuple WHERE subTuple.truck = tuple AND subTuple.agreement.closed = false)");
	}
	
	@Override
	public Collection<Truck> readByAgreementsCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}
	
	@Override
	public Collection<Truck> readWhereAgreementClosedIsFalseExist(Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereAgreementClosedIsFalseExist);
		return __readMany__(properties, ____getQueryParameters____(properties));
	}
	
	@Override
	public Collection<Truck> readWhereAgreementClosedIsFalseDoesNotExist(Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereAgreementClosedIsFalseDoesNotExist);
		return __readMany__(properties, ____getQueryParameters____(properties));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Truck.FIELD_AGREEMENTS)};
			return new Object[]{"agreementsCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
	
}