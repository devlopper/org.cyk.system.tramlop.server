package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Product.TABLE_NAME)
public class Product extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name = COLUMN_LOSS_RATE) private BigDecimal lossRate;
	
	public Product(String code,String name,BigDecimal lossRate) {
		super(code,name);
		setLossRate(lossRate);
	}
	
	@Override
	public Product setCode(String code) {
		return (Product) super.setCode(code);
	}
	
	@Override
	public Product setName(String name) {
		return (Product) super.setName(name);
	}
	
	public static final String FIELD_LOSS_RATE = "lossRate";	
	
	public static final String COLUMN_LOSS_RATE = FIELD_LOSS_RATE;	
	
	public static final String TABLE_NAME = "product";	
}
