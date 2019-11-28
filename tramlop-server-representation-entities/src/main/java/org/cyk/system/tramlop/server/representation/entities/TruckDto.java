package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class TruckDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private DriverDto driver;
	private ArrayList<DriverDto> drivers;
	
	public TruckDto(String identifier,String code) {
		super(identifier,code);
	}
	
	public TruckDto(String code) {
		super(code);
	}
	
}