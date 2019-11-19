package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadAgreementByTrucksCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantEmpty;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class AgreementPersistenceImpl extends AbstractPersistenceEntityImpl<Agreement> implements AgreementPersistence,ReadAgreementByTrucksCodes,Serializable {
	private static final long serialVersionUID = 1L;

	private static final String READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_BY_TRUCKS_CODES_FORMAT = "SELECT agreement FROM Agreement agreement WHERE agreement.closed = false AND "
			+ " %s EXISTS(SELECT agreementTruck FROM AgreementTruck agreementTruck WHERE agreementTruck.agreement = agreement AND agreementTruck.truck.code IN :trucksCodes)";
	
	private String readByTrucksCodes,readWhereAgreementClosedIsFalseExistByTrucksCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByTrucksCodes, "SELECT agreement FROM Agreement agreement WHERE"
				+ " EXISTS(SELECT agreementTruck FROM AgreementTruck agreementTruck WHERE agreementTruck.agreement = agreement AND agreementTruck.truck.code IN :trucksCodes)");
		addQueryCollectInstances(readWhereAgreementClosedIsFalseExistByTrucksCodes, String.format(READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_BY_TRUCKS_CODES_FORMAT, ConstantEmpty.STRING));
	}
	
	@Override
	public Collection<Agreement> readByTrucksCodes(Collection<String> trucksCodes, Properties properties) {
		if(CollectionHelper.isEmpty(trucksCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByTrucksCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,trucksCodes));
	}
	
	@Override
	public Collection<Agreement> readWhereAgreementClosedIsFalseExistByTrucksCodes(Collection<String> trucksCodes,Properties properties) {
		if(CollectionHelper.isEmpty(trucksCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereAgreementClosedIsFalseExistByTrucksCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,trucksCodes));
	}

	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByTrucksCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Agreement.FIELD_TRUCKS)};
			return new Object[]{"trucksCodes",objects[0]};
		}
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readWhereAgreementClosedIsFalseExistByTrucksCodes)) {
			if(ArrayHelper.isEmpty(objects))
				objects = new Object[] {queryContext.getFilterByKeysValue(Agreement.FIELD_TRUCKS)};
			return new Object[]{"trucksCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}