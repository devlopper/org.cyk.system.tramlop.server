package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class ProductDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal lossRate;
	
	public static final String FIELD_LOSS_RATE = "lossRate";	
	
}
