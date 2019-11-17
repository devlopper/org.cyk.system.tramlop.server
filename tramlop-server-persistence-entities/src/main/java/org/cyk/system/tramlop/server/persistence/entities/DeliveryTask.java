package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Existence;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=DeliveryTask.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=DeliveryTask.UNIQUE_CONSTRAINT_DELIVERY_TASK,columnNames= {DeliveryTask.COLUMN_DELIVERY,DeliveryTask.COLUMN_TASK}
		)})
public class DeliveryTask extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DELIVERY) private Delivery delivery;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_TASK) private Task task;
	@Embedded private Existence existence;
	@Column(name=COLUMN_COMMENT) private String comment;
	
	public DeliveryTask(Delivery delivery,Task task) {
		setDelivery(delivery);
		setTask(task);
	}
	
	public DeliveryTask(String identifier,String deliveryCode,String taskCode) {
		setIdentifier(identifier);
		setDeliveryFromCode(deliveryCode);
		setTaskFromCode(taskCode);
	}
	
	public DeliveryTask setDeliveryFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.delivery = null;
		else
			this.delivery = InstanceGetter.getInstance().getByBusinessIdentifier(Delivery.class, code);
		return this;
	}
	
	public DeliveryTask setTaskFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.task = null;
		else
			this.task = InstanceGetter.getInstance().getByBusinessIdentifier(Task.class, code);
		return this;
	}
	
	public Existence getExistence(Boolean injectIfNull) {
		if(existence == null && Boolean.TRUE.equals(injectIfNull))
			existence = new Existence();
		return existence;
	}
	
	public static final String FIELD_DELIVERY = "delivery";
	public static final String FIELD_TASK = "task";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_COMMENT = "comment";
	
	public static final String COLUMN_DELIVERY = Delivery.TABLE_NAME;
	public static final String COLUMN_TASK = Task.TABLE_NAME;
	public static final String COLUMN_COMMENT = FIELD_COMMENT;
	
	public static final String TABLE_NAME = Delivery.TABLE_NAME+Task.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_DELIVERY_TASK = COLUMN_DELIVERY+COLUMN_TASK;
}