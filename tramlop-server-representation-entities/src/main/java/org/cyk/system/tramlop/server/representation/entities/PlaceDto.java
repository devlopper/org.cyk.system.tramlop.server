package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class PlaceDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal longitude;
	private BigDecimal latitude;
	private Integer durationInMinute;
	
	public PlaceDto(String identifier,String code,String name) {
		super(identifier,code,name);
	}
	
	public PlaceDto(String code,String name) {
		super(code,name);
	}
	
	@Override
	public PlaceDto setCode(String code) {
		return (PlaceDto) super.setCode(code);
	}
	
	public static final String FIELD_LONGITUDE = "longitude";
	public static final String FIELD_LATITUDE = "latitude";
	public static final String FIELD_DURATION_IN_MINUTE = "durationInMinute";
}