package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=DeliveryTask.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=DeliveryTask.UNIQUE_CONSTRAINT_DELIVERY_TASK,columnNames= {DeliveryTask.COLUMN_DELIVERY,DeliveryTask.COLUMN_TASK}
		)})
public class DeliveryTask extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DELIVERY,unique = true) private Delivery delivery;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DELIVERY,unique = true) private Task task;
	
	public static final String FIELD_DELIVERY = "delivery";
	public static final String FIELD_TASK = "task";
	
	public static final String COLUMN_DELIVERY = Delivery.TABLE_NAME;
	public static final String COLUMN_TASK = Task.TABLE_NAME;
	
	public static final String TABLE_NAME = Delivery.TABLE_NAME+Task.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_DELIVERY_TASK = COLUMN_DELIVERY+COLUMN_TASK;
}