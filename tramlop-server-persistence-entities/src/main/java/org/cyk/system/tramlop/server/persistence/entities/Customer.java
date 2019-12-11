package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Customer.TABLE_NAME)
public class Customer extends AbstractPersonImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	public Customer(String code,Person person) {
		super(code);
		this.person = person;
	}
	
	public static final String TABLE_NAME = "customer";	
}
