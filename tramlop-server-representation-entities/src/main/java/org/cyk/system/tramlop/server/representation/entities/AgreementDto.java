package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.object.__static__.representation.ExistenceDto;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class AgreementDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private CustomerDto customer;
	private ProductDto product;
	private PlaceDto departurePlace;
	private List<ProductDto> products;
	private List<TruckDto> trucks;
	private List<PlaceDto> arrivalPlaces;
	
	private List<AgreementProductDto> agreementProducts;
	private List<AgreementTruckDto> agreementTrucks;
	private List<AgreementArrivalPlaceDto> agreementArrivalPlaces;
	
	private Integer productWeightInKiloGram;
	private ExistenceDto existence;
	private Boolean closed;
	
	public AgreementDto(String code,String customerCode,String departurePlaceCode) {
		setCode(code);
		setCustomer(new CustomerDto(customerCode));
		setDeparturePlace(new PlaceDto(departurePlaceCode,null));
	}
	
	public AgreementDto(String customerCode,String departurePlaceCode) {
		setCustomer(new CustomerDto(customerCode));
		setDeparturePlace(new PlaceDto(departurePlaceCode,null));
	}
	
	@Override
	public AgreementDto setIdentifier(String identifier) {
		return (AgreementDto) super.setIdentifier(identifier);
	}
	
	@Override
	public AgreementDto setCode(String code) {
		return (AgreementDto) super.setCode(code);
	}
	
	public List<ProductDto> getProducts(Boolean injectIfNull) {
		if(products == null && Boolean.TRUE.equals(injectIfNull))
			products = new ArrayList<>();
		return products;
	}
	
	public AgreementDto addProducts(Collection<ProductDto> products) {
		if(CollectionHelper.isEmpty(products))
			return this;
		getProducts(Boolean.TRUE).addAll(products);
		return this;
	}
	
	public AgreementDto addProducts(ProductDto...products) {
		if(ArrayHelper.isEmpty(products))
			return this;
		getProducts(Boolean.TRUE).addAll(CollectionHelper.listOf(products));
		return this;
	}
	
	public AgreementDto addProduct(String code,Integer weightInKiloGram) {
		if(StringHelper.isBlank(code))
			return this;
		Product product = __inject__(ProductPersistence.class).readByBusinessIdentifier(code);
		if(product == null)
			return this;
		return addProducts(new ProductDto(product.getIdentifier(),null,null).setWeightInKiloGram(weightInKiloGram));
	}
	
	public List<PlaceDto> getArrivalPlaces(Boolean injectIfNull) {
		if(arrivalPlaces == null && Boolean.TRUE.equals(injectIfNull))
			arrivalPlaces = new ArrayList<>();
		return arrivalPlaces;
	}
	
	public AgreementDto addArrivalPlaces(Collection<PlaceDto> arrivalPlaces) {
		if(CollectionHelper.isEmpty(products))
			return this;
		getArrivalPlaces(Boolean.TRUE).addAll(arrivalPlaces);
		return this;
	}
	
	public AgreementDto addArrivalPlaces(PlaceDto...arrivalPlaces) {
		if(ArrayHelper.isEmpty(arrivalPlaces))
			return this;
		getArrivalPlaces(Boolean.TRUE).addAll(CollectionHelper.listOf(arrivalPlaces));
		return this;
	}
	
	public AgreementDto addArrivalPlace(String code,Integer durationInMinute) {
		if(StringHelper.isBlank(code))
			return this;
		Place place = __inject__(PlacePersistence.class).readByBusinessIdentifier(code);
		if(place == null)
			return this;
		return addArrivalPlaces(new PlaceDto(place.getIdentifier(),null,null).setDurationInMinute(durationInMinute));
	}
	
	public List<TruckDto> getTrucks(Boolean injectIfNull) {
		if(trucks == null && Boolean.TRUE.equals(injectIfNull))
			trucks = new ArrayList<>();
		return trucks;
	}
	
	public AgreementDto addTrucks(Collection<TruckDto> trucks) {
		if(CollectionHelper.isEmpty(trucks))
			return this;
		getTrucks(Boolean.TRUE).addAll(trucks);
		return this;
	}
	
	public AgreementDto addTrucks(TruckDto...trucks) {
		if(ArrayHelper.isEmpty(trucks))
			return this;
		getTrucks(Boolean.TRUE).addAll(CollectionHelper.listOf(trucks));
		return this;
	}
	
	public AgreementDto addTruck(String code,String driverCode) {
		if(StringHelper.isBlank(code))
			return this;
		Truck truck = __inject__(TruckPersistence.class).readByBusinessIdentifier(code);
		if(truck == null)
			return this;
		Driver driver = __inject__(DriverPersistence.class).readByBusinessIdentifier(driverCode);
		if(driver == null)
			return this;
		return addTrucks(new TruckDto(truck.getIdentifier(),null).setDriver(new DriverDto(driver.getIdentifier(),null)));
	}
	
	public ExistenceDto getExistence(Boolean injectIfNull) {
		if(existence == null && Boolean.TRUE.equals(injectIfNull))
			existence = new ExistenceDto();
		return existence;
	}
	
	public static final String FIELD_CUSTOMER = "customer";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_PRODUCT_WEIGHT_IN_KILO_GRAM = "productWeightInKiloGram";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_CLOSED = "closed";
}