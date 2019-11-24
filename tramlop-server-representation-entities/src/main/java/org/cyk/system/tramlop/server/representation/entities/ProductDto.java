package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class ProductDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal lossRate;
	private Integer weightInKiloGram;
	
	public ProductDto(String identifier,String code,String name) {
		super(identifier,code,name);
	}
	
	public ProductDto(String code,String name) {
		super(code,name);
	}
	
	@Override
	public ProductDto setIdentifier(String identifier) {
		return (ProductDto) super.setIdentifier(identifier);
	}
	
	@Override
	public ProductDto setCode(String code) {
		return (ProductDto) super.setCode(code);
	}
	
	public static final String FIELD_LOSS_RATE = "lossRate";	
	
}
