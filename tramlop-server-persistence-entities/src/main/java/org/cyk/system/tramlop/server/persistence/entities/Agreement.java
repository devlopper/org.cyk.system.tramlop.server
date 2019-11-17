package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Existence;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Agreement.TABLE_NAME)
public class Agreement extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_CUSTOMER) private Customer customer;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DEPARTURE_PLACE) private Place departurePlace;
	@Embedded private Existence existence;
	@NotNull @Column(name=COLUMN_CLOSED) private Boolean closed;
	
	@Transient private Collection<Truck> trucks;
	@Transient private Collection<Delivery> deliveries;
	
	public Agreement(String code,String customerCode,String departurePlaceCode,Boolean closed) {
		super(code);
		setCustomerFromCode(customerCode);
		setDeparturePlaceFromCode(departurePlaceCode);
		setClosed(closed);
	}
	
	public Existence getExistence(Boolean injectIfNull) {
		if(existence == null && Boolean.TRUE.equals(injectIfNull))
			existence = new Existence();
		return existence;
	}
	
	public Agreement setCustomerFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.customer = null;
		else
			this.customer = InstanceGetter.getInstance().getByBusinessIdentifier(Customer.class, code);
		return this;
	}
	
	public Agreement setDeparturePlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.departurePlace = null;
		else
			this.departurePlace = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	public Collection<Truck> getTrucks(Boolean injectIfNull) {
		if(trucks == null && Boolean.TRUE.equals(injectIfNull))
			trucks = new ArrayList<>();
		return trucks;
	}
	
	public Agreement addTrucksFromCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String code : codes)
			getTrucks(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code));
		return this;
	}
	
	public Agreement addTrucksFromCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addTrucksFromCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	public Agreement addTruck(String code,String driverCode) {
		if(StringHelper.isBlank(code))
			return this;
		getTrucks(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code).setDriverFromCode(driverCode));
		return this;
	}
	
	public static final String FIELD_CUSTOMER = "customer";
	public static final String FIELD_DEPARTURE_PLACE = "departurePlace";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_CLOSED = "closed";
	
	public static final String COLUMN_CUSTOMER = Customer.TABLE_NAME;
	public static final String COLUMN_DEPARTURE_PLACE = FIELD_DEPARTURE_PLACE;
	public static final String COLUMN_CLOSED = FIELD_CLOSED;
	
	public static final String TABLE_NAME = "agreement";
}