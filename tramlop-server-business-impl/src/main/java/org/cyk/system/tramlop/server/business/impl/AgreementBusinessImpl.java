package org.cyk.system.tramlop.server.business.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementArrivalPlaceBusiness;
import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.AgreementProductBusiness;
import org.cyk.system.tramlop.server.business.api.AgreementTruckBusiness;
import org.cyk.system.tramlop.server.persistence.api.AgreementArrivalPlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadAgreementArrivalPlaceByAgreements;
import org.cyk.system.tramlop.server.persistence.api.query.ReadAgreementTruckByAgreements;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.system.tramlop.server.persistence.entities.AgreementProduct;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.__kernel__.string.Strings;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;

@ApplicationScoped
public class AgreementBusinessImpl extends AbstractBusinessEntityImpl<Agreement, AgreementPersistence> implements AgreementBusiness,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenExecuteCreateBefore__(Agreement agreement, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateBefore__(agreement, properties, function);
		if(StringHelper.isBlank(agreement.getCode()))
			agreement.setCode(System.currentTimeMillis()+"");
		if(agreement.getClosed() == null)
			agreement.setClosed(Boolean.FALSE);
	}
	
	@Override
	protected void __listenExecuteCreateAfter__(Agreement agreement, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateAfter__(agreement, properties, function);
		if(CollectionHelper.isNotEmpty(agreement.getProducts()))
			__inject__(AgreementProductBusiness.class).createMany(agreement.getProducts().stream().map(product -> new AgreementProduct(agreement,product,product.getWeightInKiloGram())).collect(Collectors.toList()));
		if(CollectionHelper.isNotEmpty(agreement.getArrivalPlaces()))
			__inject__(AgreementArrivalPlaceBusiness.class).createMany(agreement.getArrivalPlaces().stream().map(place -> new AgreementArrivalPlace(agreement,place,place.getDurationInMinute())).collect(Collectors.toList()));
		if(CollectionHelper.isNotEmpty(agreement.getTrucks()))
			__inject__(AgreementTruckBusiness.class).createMany(agreement.getTrucks().stream().map(truck -> new AgreementTruck(agreement,truck,truck.getDriver())).collect(Collectors.toList()));		
	}
	
	@Override
	protected void __listenExecuteUpdateBefore__(Agreement agreement, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateBefore__(agreement, properties, function);
		Strings fieldsNames = __getFieldsFromProperties__(properties);
		if(CollectionHelper.contains(fieldsNames, Agreement.FIELD_CLOSED)) {
			Long deliveryCount = __inject__(DeliveryPersistence.class).countByAgreementsByClosed(Boolean.FALSE, agreement);
			if( deliveryCount > 0)
				throw new RuntimeException("Le contrat "+agreement.getCode()+" ne peut pas être clos car il a "+deliveryCount+" livraison(s) en cours.");
		}else if(CollectionHelper.contains(fieldsNames, Agreement.FIELD_PRODUCTS)) {
			Collection<AgreementProduct> databaseAgreementProducts = __inject__(AgreementProductPersistence.class).readByAgreements(agreement);
			Collection<Product> databaseProducts = CollectionHelper.isEmpty(databaseAgreementProducts) ? null : databaseAgreementProducts.stream()
					.map(AgreementProduct::getProduct).collect(Collectors.toList());
			
			__delete__(agreement.getProducts(), databaseAgreementProducts,AgreementProduct.FIELD_PRODUCT);
			__save__(AgreementProduct.class,agreement.getProducts(), databaseProducts, AgreementProduct.FIELD_PRODUCT, agreement, AgreementProduct.FIELD_AGREEMENT);
			
			if(CollectionHelper.isNotEmpty(databaseAgreementProducts) && CollectionHelper.isNotEmpty(agreement.getProducts())) {
				for(AgreementProduct agreementProduct : databaseAgreementProducts) {
					for(Product product : agreement.getProducts()) {
						if(agreementProduct.getProduct().equals(product)) {
							agreementProduct.setWeightInKiloGram(product.getWeightInKiloGram());
							__inject__(AgreementProductPersistence.class).update(agreementProduct);
							break;
						}
					}
				}
			}
		}else if(CollectionHelper.contains(fieldsNames, Agreement.FIELD_ARRIVAL_PLACES)) {
			Collection<AgreementArrivalPlace> databaseAgreementArrivalPlaces = ((ReadAgreementArrivalPlaceByAgreements)__inject__(AgreementArrivalPlacePersistence.class)).readByAgreements(agreement);
			Collection<Place> databasePlaces = CollectionHelper.isEmpty(databaseAgreementArrivalPlaces) ? null : databaseAgreementArrivalPlaces.stream()
					.map(AgreementArrivalPlace::getPlace).collect(Collectors.toList());
			
			__delete__(agreement.getArrivalPlaces(), databaseAgreementArrivalPlaces,AgreementArrivalPlace.FIELD_PLACE);
			__save__(AgreementArrivalPlace.class,agreement.getArrivalPlaces(), databasePlaces, AgreementArrivalPlace.FIELD_PLACE, agreement, AgreementArrivalPlace.FIELD_AGREEMENT);
			
			if(CollectionHelper.isNotEmpty(databaseAgreementArrivalPlaces) && CollectionHelper.isNotEmpty(agreement.getArrivalPlaces())) {
				for(AgreementArrivalPlace agreementArrivalPlace : databaseAgreementArrivalPlaces) {
					for(Place place : agreement.getArrivalPlaces()) {
						if(agreementArrivalPlace.getPlace().equals(place)) {
							agreementArrivalPlace.setDurationInMinute(place.getDurationInMinute());
							__inject__(AgreementArrivalPlacePersistence.class).update(agreementArrivalPlace);
							break;
						}
					}
				}
			}
		}else if(CollectionHelper.contains(fieldsNames, Agreement.FIELD_TRUCKS)) {
			Collection<AgreementTruck> databaseAgreementTrucks = ((ReadAgreementTruckByAgreements)__inject__(AgreementTruckPersistence.class)).readByAgreements(agreement);
			Collection<Truck> databaseTrucks = CollectionHelper.isEmpty(databaseAgreementTrucks) ? null : databaseAgreementTrucks.stream()
					.map(AgreementTruck::getTruck).collect(Collectors.toList());
			
			__delete__(agreement.getTrucks(), databaseAgreementTrucks,AgreementTruck.FIELD_TRUCK);
			__save__(AgreementTruck.class,agreement.getTrucks(), databaseTrucks, AgreementTruck.FIELD_TRUCK, agreement, AgreementTruck.FIELD_AGREEMENT);
			
			if(CollectionHelper.isNotEmpty(databaseAgreementTrucks) && CollectionHelper.isNotEmpty(agreement.getTrucks())) {
				for(AgreementTruck agreementTruck : databaseAgreementTrucks) {
					for(Truck truck : agreement.getTrucks()) {
						if(agreementTruck.getTruck().equals(truck)) {
							agreementTruck.setDriver(truck.getDriver());
							__inject__(AgreementTruckPersistence.class).update(agreementTruck);
							break;
						}
					}
				}
			}
		}
	}
	
	@Override
	protected <M, D> D __getSavableInstance__(Class<D> klass, M finalInstance, Collection<M> persistedInstances,String fieldName, Object master, String masterFieldName) {
		D instance =  super.__getSavableInstance__(klass, finalInstance, persistedInstances, fieldName, master, masterFieldName);
		if(instance instanceof AgreementProduct && finalInstance instanceof Product) {
			((AgreementProduct)instance).setWeightInKiloGram(((Product)finalInstance).getWeightInKiloGram());
		}
		return instance;
	}
}
