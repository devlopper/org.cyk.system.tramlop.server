package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadArrivalPlaceByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class PlacePersistenceImpl extends AbstractPersistenceEntityImpl<Place> implements PlacePersistence,ReadArrivalPlaceByAgreementsCodes,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByAgreementsCodes,readArrivalByAgreementsCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementsCodes, "SELECT place FROM Place place WHERE"
				+ " EXISTS(SELECT agreement FROM Agreement agreement WHERE agreement.departurePlace = place AND agreement.code IN :agreementsCodes)"
				+ " OR "
				+ " EXISTS(SELECT agreementArrivalPlace FROM AgreementArrivalPlace agreementArrivalPlace WHERE agreementArrivalPlace.place = place AND agreementArrivalPlace.agreement.code IN :agreementsCodes)"
				);
		addQueryCollectInstances(readArrivalByAgreementsCodes, "SELECT place FROM Place place WHERE"
				+ " EXISTS(SELECT agreementArrivalPlace FROM AgreementArrivalPlace agreementArrivalPlace WHERE agreementArrivalPlace.place = place AND agreementArrivalPlace.agreement.code IN :agreementsCodes)");
	}
	
	@Override
	public Collection<Place> readByAgreementsCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}
	
	@Override
	public Collection<Place> readArrivalByAgreementsCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readArrivalByAgreementsCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}

	@Override
	protected Object[] __getQueryParameters__(PersistenceQueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodes)) {
			return new Object[]{"agreementsCodes",objects[0]};
		}else if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readArrivalByAgreementsCodes)) {
			return new Object[]{"agreementsCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}