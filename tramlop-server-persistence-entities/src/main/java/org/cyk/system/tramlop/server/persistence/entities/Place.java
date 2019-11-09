package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Place.TABLE_NAME)
public class Place extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = COLUMN_LONGITUDE) private BigDecimal longitude;
	@Column(name = COLUMN_LATITUDE) private BigDecimal latitude;
	
	public Place(String code,String name,BigDecimal longitude,BigDecimal latitude) {
		super(code,name);
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public static final String FIELD_LONGITUDE = "longitude";
	public static final String FIELD_LATITUDE = "latitude";
	
	public static final String COLUMN_LONGITUDE = FIELD_LONGITUDE;
	public static final String COLUMN_LATITUDE = FIELD_LATITUDE;
	
	public static final String TABLE_NAME = "place";
}