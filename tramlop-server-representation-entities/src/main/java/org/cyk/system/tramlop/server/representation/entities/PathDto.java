package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class PathDto extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private PlaceDto departure;
	private PlaceDto arrival;
	private Integer durationInMinute;
	
	public static final String FIELD_DEPARTURE = "departure";
	public static final String FIELD_ARRIVAL = "arrival";
	public static final String FIELD_DURATION_IN_MINUTE = "durationInMinute";
}