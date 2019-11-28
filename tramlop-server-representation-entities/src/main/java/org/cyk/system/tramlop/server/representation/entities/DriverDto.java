package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.object.__static__.representation.PersonDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class DriverDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonDto person;
	private ArrayList<AgreementDto> agreements;
	private ArrayList<TruckDto> trucks;
	
	public DriverDto(String identifier,String code) {
		super(identifier,code);
	}
	
	public DriverDto(String code) {
		super(code);
	}
	
	public static final String FIELD_PERSON = "person";
	
}
