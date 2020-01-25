package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementProductPersistence;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class AgreementProductPersistenceImpl extends AbstractPersistenceEntityImpl<AgreementProduct> implements AgreementProductPersistence,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByAgreementsCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementsCodes, "SELECT agreementProduct FROM AgreementProduct agreementProduct WHERE agreementProduct.agreement.code IN :agreementsCodes");
	}
	
	@Override
	public Collection<AgreementProduct> readByAgreementsCodes(Collection<String> agreementsCodes,Properties properties) {
		if(CollectionHelper.isEmpty(agreementsCodes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,agreementsCodes));
	}
	
	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodes)) {
			return new Object[]{"agreementsCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}

}