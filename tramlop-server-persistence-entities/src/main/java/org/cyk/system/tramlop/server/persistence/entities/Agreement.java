package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=Agreement.TABLE_NAME)
public class Agreement extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_CUSTOMER) private Customer customer;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PRODUCT) private Product product;
	@NotNull @Column(name=COLUMN_PRODUCT_WEIGHT_IN_KILO_GRAM) private Integer productWeightInKiloGram;
	
	//@NotNull @Column(name=COLUMN_FROM_DATE) private LocalDateTime fromDate;
	//@NotNull @Column(name=COLUMN_TO_DATE) private LocalDateTime toDate;
	
	public static final String FIELD_CUSTOMER = "customer";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_PRODUCT_WEIGHT_IN_KILO_GRAM = "productWeightInKiloGram";
	//public static final String FIELD_FROM_DATE = "fromDate";
	//public static final String FIELD_TO_DATE = "toDate";
	
	public static final String COLUMN_CUSTOMER = Customer.TABLE_NAME;
	public static final String COLUMN_PRODUCT = Product.TABLE_NAME;
	public static final String COLUMN_PRODUCT_WEIGHT_IN_KILO_GRAM = FIELD_PRODUCT_WEIGHT_IN_KILO_GRAM;
	//public static final String COLUMN_FROM_DATE = FIELD_FROM_DATE;
	//public static final String COLUMN_TO_DATE = FIELD_TO_DATE;
	
	public static final String TABLE_NAME = "agreement";
}