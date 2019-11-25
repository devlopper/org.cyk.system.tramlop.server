package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class AgreementTruckPersistenceImpl extends AbstractPersistenceEntityImpl<AgreementTruck> implements AgreementTruckPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByAgreementCodeByTruckCode;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementCodeByTruckCode, "SELECT agreementTruck FROM AgreementTruck agreementTruck WHERE agreementTruck.agreement.code = :agreementCode"
				+ " AND agreementTruck.truck.code = :truckCode");
	}
	
	@Override
	public AgreementTruck readByAgreementCodeByTruckCode(String agreementCode, String truckCode) {
		if(StringHelper.isBlank(agreementCode) || StringHelper.isBlank(agreementCode))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementCodeByTruckCode);
		return __readOne__(properties, ____getQueryParameters____(properties,agreementCode,truckCode));
	}
	
	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementCodeByTruckCode))
			return new Object[]{"agreementCode",objects[0],"truckCode",objects[1]};
		return super.__getQueryParameters__(queryContext, properties, objects);
	}

}