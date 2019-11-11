package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class AgreementProductDto extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private AgreementDto agreement;
	private ProductDto truck;
	private Integer weightInKiloGram;
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
}