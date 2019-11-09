package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Driver.TABLE_NAME)
public class Driver extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Embedded private Person person;
	
	public Driver(String code,Person person) {
		super(code);
		this.person = person;
	}
	
	public static final String FIELD_PERSON = "person";
	
	public static final String TABLE_NAME = "driver";	
}
