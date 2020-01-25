package org.cyk.system.tramlop.server.persistence.impl;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.persistence.api.AgreementArrivalPlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadAgreementArrivalPlaceByAgreements;
import org.cyk.system.tramlop.server.persistence.api.query.ReadAgreementByTrucksCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadAgreementTruckByAgreements;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantEmpty;
import org.cyk.utility.__kernel__.persistence.query.QueryContext;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.PersistenceFunctionReader;

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
	protected void __listenExecuteReadAfterSetFieldValue__(Agreement agreement, Field field, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(agreement, field, properties);
		if(field.getName().equals(Agreement.FIELD_AGREEMENT_PRODUCTS)) {
			agreement.setAgreementProducts(__inject__(AgreementProductPersistence.class).readByAgreements(agreement));
		} else if(field.getName().equals(Agreement.FIELD_AGREEMENT_ARRIVAL_PLACES)) {
			agreement.setAgreementArrivalPlaces( ((ReadAgreementArrivalPlaceByAgreements)__inject__(AgreementArrivalPlacePersistence.class)).readByAgreements(agreement));
		}else if(field.getName().equals(Agreement.FIELD_AGREEMENT_TRUCKS))
			agreement.setAgreementTrucks( ((ReadAgreementTruckByAgreements)__inject__(AgreementTruckPersistence.class)).readByAgreements(agreement));
		else if(field.getName().equals(Agreement.FIELD_PRODUCTS)) {
			agreement.setAgreementProducts(__inject__(AgreementProductPersistence.class).readByAgreements(agreement));
			if(CollectionHelper.isNotEmpty(agreement.getAgreementProducts()))
				agreement.setProducts(agreement.getAgreementProducts().stream().map(AgreementProduct::getProduct).collect(Collectors.toList()));
		} else if(field.getName().equals(Agreement.FIELD_ARRIVAL_PLACES)) {
			agreement.setAgreementArrivalPlaces( ((ReadAgreementArrivalPlaceByAgreements)__inject__(AgreementArrivalPlacePersistence.class)).readByAgreements(agreement));
			if(CollectionHelper.isNotEmpty(agreement.getAgreementArrivalPlaces()))
				agreement.setArrivalPlaces(agreement.getAgreementArrivalPlaces().stream().map(AgreementArrivalPlace::getPlace).collect(Collectors.toList()));
		}else if(field.getName().equals(Agreement.FIELD_TRUCKS))
			agreement.setTrucks(((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes(agreement.getCode()));
	}
	
	@Override
	protected void __listenExecuteReadAfterSetFieldValue__(Agreement agreement, String fieldName, Properties properties) {
		super.__listenExecuteReadAfterSetFieldValue__(agreement, fieldName, properties);
		if(fieldName.equals(Agreement.FIELD_PRODUCTS+"."+Product.FIELD_WEIGHT_IN_KILO_GRAM)) {
			if(CollectionHelper.isNotEmpty(agreement.getProducts())) {
				for(Product product : agreement.getProducts())
					product.setWeightInKiloGram(agreement.getAgreementProducts().stream().filter(x -> x.getProduct().equals(product)).collect(Collectors.toList()).get(0).getWeightInKiloGram());
			}
		}else if(fieldName.equals(Agreement.FIELD_ARRIVAL_PLACES+"."+Place.FIELD_DURATION_IN_MINUTE)) {
			if(CollectionHelper.isNotEmpty(agreement.getArrivalPlaces())) {
				for(Place place : agreement.getArrivalPlaces())
					place.setDurationInMinute(agreement.getAgreementArrivalPlaces().stream().filter(x -> x.getPlace().equals(place)).collect(Collectors.toList()).get(0).getDurationInMinute());
			}
		}
	}
	
	@Override
	protected String __getQueryIdentifier__(Class<?> klass, Properties properties, Object... objects) {
		if(PersistenceFunctionReader.class.equals(klass)) {
			if(Boolean.TRUE.equals(__isFilterByKeys__(properties, Agreement.FIELD_TRUCKS)))
				return readByTrucksCodes;
		}
		return super.__getQueryIdentifier__(klass, properties, objects);
	}

	@Override
	protected Object[] __getQueryParameters__(QueryContext queryContext, Properties properties,Object... objects) {
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