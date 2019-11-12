package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantEmpty;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQueryContext;

@ApplicationScoped
public class TruckPersistenceImpl extends AbstractPersistenceEntityImpl<Truck> implements TruckPersistence,ReadTruckByAgreementsCodes,Serializable {
	private static final long serialVersionUID = 1L;

	private String readByAgreementsCodes,readWhereAgreementClosedIsFalseExist,readWhereAgreementClosedIsFalseDoesNotExist
		,readWhereDeliveryClosedIsFalseExist,readWhereDeliveryClosedIsFalseDoesNotExist
		,readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist,readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist;

	private static final String READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_FORMAT = "SELECT tuple FROM Truck tuple WHERE"
			+ " %s EXISTS(SELECT subTuple FROM AgreementTruck subTuple WHERE subTuple.truck = tuple AND subTuple.agreement.closed = false)";
	
	private static final String READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_FORMAT = "SELECT tuple FROM Truck tuple WHERE"
			+ " %s EXISTS(SELECT subTuple FROM Delivery subTuple WHERE subTuple.truck = tuple AND subTuple.closed = false)";
	
	private static final String READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_EXIST_FORMAT = "SELECT truck FROM Truck truck WHERE"
			+ " EXISTS(SELECT agreementTruck FROM AgreementTruck agreementTruck WHERE agreementTruck.truck = truck AND agreementTruck.agreement.closed = false) AND"
			+ " %s EXISTS(SELECT delivery FROM Delivery delivery WHERE delivery.truck = truck AND delivery.agreement.closed = false AND delivery.closed = false)"
			;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByAgreementsCodes, "SELECT tuple FROM Truck tuple WHERE"
				+ " EXISTS(SELECT subTuple FROM AgreementTruck subTuple WHERE subTuple.truck = tuple AND subTuple.agreement.code IN :agreementsCodes)");
		addQueryCollectInstances(readWhereAgreementClosedIsFalseExist, String.format(READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_FORMAT, ConstantEmpty.STRING));
		addQueryCollectInstances(readWhereAgreementClosedIsFalseDoesNotExist, String.format(READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_FORMAT, "NOT"));
		
		addQueryCollectInstances(readWhereDeliveryClosedIsFalseExist, String.format(READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_FORMAT, ConstantEmpty.STRING));
		addQueryCollectInstances(readWhereDeliveryClosedIsFalseDoesNotExist, String.format(READ_WHERE_DELIVERY_CLOSED_IS_FALSE_EXIST_FORMAT, "NOT"));
		
		addQueryCollectInstances(readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist
				, String.format(READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_EXIST_FORMAT, ConstantEmpty.STRING));
		addQueryCollectInstances(readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist
				, String.format(READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_EXIST_FORMAT, "NOT"));
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
	public Collection<Truck> readWhereDeliveryClosedIsFalseExist(Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereDeliveryClosedIsFalseExist);
		return __readMany__(properties, ____getQueryParameters____(properties));
	}
	
	@Override
	public Collection<Truck> readWhereDeliveryClosedIsFalseDoesNotExist(Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereDeliveryClosedIsFalseDoesNotExist);
		return __readMany__(properties, ____getQueryParameters____(properties));
	}
	
	@Override
	public Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist);
		return __readMany__(properties, ____getQueryParameters____(properties));
	}
	
	@Override
	public Collection<Truck> readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(Properties properties) {
		if(properties == null)
			properties = new Properties();
		properties.setIfNull(Properties.QUERY_IDENTIFIER, readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist);
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