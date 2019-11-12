package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Truck.TABLE_NAME)
public class Truck extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient private Driver driver;
	@Transient private Collection<Agreement> agreements;
	
	public Truck(String code) {
		super(code);
	}
	
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_AGREEMENTS = "agreements";
	
	public static final String TABLE_NAME = "truck";
	
}