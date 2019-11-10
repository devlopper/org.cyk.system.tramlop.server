package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PRODUCT) private Product product;
	@NotNull @Column(name=COLUMN_PRODUCT_WEIGHT_IN_KILO_GRAM) private Integer productWeightInKiloGram;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DEPARTURE_PLACE) private Place departurePlace;
	@Embedded private Existence existence;
	
	public Agreement(String code,String customerCode,String productCode,Integer productWeightInKiloGram,String departurePlaceCode) {
		super(code);
		setCustomerFromCode(customerCode);
		setProductFromCode(productCode);
		setProductWeightInKiloGram(productWeightInKiloGram);
		setDeparturePlaceFromCode(departurePlaceCode);
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
	
	public Agreement setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	public Agreement setDeparturePlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.departurePlace = null;
		else
			this.departurePlace = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	public static final String FIELD_CUSTOMER = "customer";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_PRODUCT_WEIGHT_IN_KILO_GRAM = "productWeightInKiloGram";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_DEPARTURE_PLACE = "departurePlace";
	
	public static final String COLUMN_CUSTOMER = Customer.TABLE_NAME;
	public static final String COLUMN_PRODUCT = Product.TABLE_NAME;
	public static final String COLUMN_PRODUCT_WEIGHT_IN_KILO_GRAM = FIELD_PRODUCT_WEIGHT_IN_KILO_GRAM;
	public static final String COLUMN_DEPARTURE_PLACE = FIELD_DEPARTURE_PLACE;
	
	public static final String TABLE_NAME = "agreement";
}