package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=Person.TABLE_NAME)
public class Person extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name = COLUMN_FIRST_NAME) private String firstName;
	@Column(name = COLUMN_LAST_NAMES) private String lastNames;
	
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAMES = "lastNames";
	
	public static final String COLUMN_FIRST_NAME = FIELD_FIRST_NAME;
	public static final String COLUMN_LAST_NAMES = FIELD_LAST_NAMES;
	
	public static final String TABLE_NAME = "person";	
}
