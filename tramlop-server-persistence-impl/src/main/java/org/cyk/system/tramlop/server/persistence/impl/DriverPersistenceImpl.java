package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadDriversByTrucks;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;

@ApplicationScoped
public class DriverPersistenceImpl extends AbstractPersistenceEntityImpl<Driver> implements DriverPersistence,ReadDriversByTrucks,Serializable {
	private static final long serialVersionUID = 1L;

	private String readWhereNotExistAgreementClosedIsFalse,readByTrucksCodes,readByAgreementsCodesByTrucksCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readWhereNotExistAgreementClosedIsFalse, "SELECT tuple FROM Driver tuple");
		addQueryCollectInstances(readByTrucksCodes, "SELECT driver FROM Driver driver WHERE "
				+ " EXISTS(SELECT agreementTruck FROM AgreementTruck agreementTruck WHERE agreementTruck.driver = driver AND agreementTruck.truck.code IN :trucksCodes) "
				+ " OR EXISTS(SELECT agreementTruckSecondaryDriver FROM AgreementTruckSecondaryDriver agreementTruckSecondaryDriver WHERE agreementTruckSecondaryDriver.driver = driver AND agreementTruckSecondaryDriver.truck.code IN :trucksCodes)");
		addQueryCollectInstances(readByAgreementsCodesByTrucksCodes, "SELECT driver FROM Driver driver WHERE "
				+ " EXISTS(SELECT agreementTruck FROM AgreementTruck agreementTruck WHERE agreementTruck.driver = driver AND agreementTruck.agreement.code IN :agreementsCodes AND agreementTruck.truck.code IN :trucksCodes) "
				+ " OR EXISTS(SELECT agreementTruckSecondaryDriver FROM AgreementTruckSecondaryDriver agreementTruckSecondaryDriver WHERE agreementTruckSecondaryDriver.driver = driver AND agreementTruckSecondaryDriver.agreement.code IN :agreementsCodes AND agreementTruckSecondaryDriver.truck.code IN :trucksCodes)");
	}
	
	@Override
	public Collection<Driver> readWhereNotExistAgreementClosedIsFalse() {
		return read(new Properties().setQueryIdentifier(readWhereNotExistAgreementClosedIsFalse));
	}
	
	@Override
	public Collection<Driver> readByTrucksCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByTrucksCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}
	
	@Override
	public Collection<Driver> readByAgreementsCodesByTrucksCodes(Collection<String> agreementsCodes,Collection<String> trucksCodes,Properties properties) {
		if(CollectionHelper.isEmpty(agreementsCodes) || CollectionHelper.isEmpty(trucksCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodesByTrucksCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,agreementsCodes,trucksCodes));
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> klass, Properties properties, Object... objects) {
		if(PersistenceFunctionReader.class.equals(klass)) {
			if(__isFilterByKeys__(properties, Driver.FIELD_AGREEMENTS) && __isFilterByKeys__(properties, Driver.FIELD_TRUCKS))
				return readByAgreementsCodesByTrucksCodes;
		}
		return super.__getQueryIdentifier__(klass, properties, objects);
	}
	
	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(READ_WHERE_NOT_EXIST_AGREEMENT_CLOSED_IS_FALSE))
			return null;
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByTrucksCodes))
			return new Object[] {"trucksCodes",objects[0]};
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodesByTrucksCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Driver.FIELD_AGREEMENTS),queryContext.getFilterByKeysValue(Driver.FIELD_TRUCKS)};
			return new Object[] {"agreementsCodes",objects[0],"trucksCodes",objects[1]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}

}