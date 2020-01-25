package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadProductByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@ApplicationScoped
public class ProductPersistenceImpl extends AbstractPersistenceEntityImpl<Product> implements ProductPersistence,ReadProductByAgreementsCodes,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByAgreementsCodes;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementsCodes, "SELECT product FROM Product product WHERE"
				+ " EXISTS(SELECT agreementProduct FROM AgreementProduct agreementProduct WHERE agreementProduct.product = product AND agreementProduct.agreement.code IN :agreementsCodes)");
	}
	
	@Override
	public Collection<Product> readByAgreementsCodes(Collection<String> codes, Properties properties) {
		if(CollectionHelper.isEmpty(codes))
			return null;
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readByAgreementsCodes);
		return __readMany__(properties, ____getQueryParameters____(properties,codes));
	}

	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
		if(queryContext.getQuery().isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByAgreementsCodes)) {
			return new Object[]{"agreementsCodes",objects[0]};
		}
		return super.__getQueryParameters__(queryContext, properties, objects);
	}
}