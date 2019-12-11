package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Employee.TABLE_NAME)
public class Employee extends AbstractPersonImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name = COLUMN_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT) private Boolean notifiableOnDeliveryDurationAlert;
	
	public Employee(String code,Person person) {
		super(code);
		this.person = person;
	}
	
	public static final String FIELD_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT = "notifiableOnDeliveryDurationAlert";
	
	public static final String COLUMN_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT = FIELD_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT;
	
	public static final String TABLE_NAME = "employee";
	
}
