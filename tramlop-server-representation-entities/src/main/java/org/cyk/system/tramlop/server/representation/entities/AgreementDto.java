package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.object.__static__.representation.ExistenceDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class AgreementDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private CustomerDto customer;
	private ProductDto product;
	private Integer productWeightInKiloGram;
	private ExistenceDto existence;
	
	public ExistenceDto getExistence(Boolean injectIfNull) {
		if(existence == null && Boolean.TRUE.equals(injectIfNull))
			existence = new ExistenceDto();
		return existence;
	}
	
	public static final String FIELD_CUSTOMER = "customer";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_PRODUCT_WEIGHT_IN_KILO_GRAM = "productWeightInKiloGram";
	public static final String FIELD_EXISTENCE = "existence";
}