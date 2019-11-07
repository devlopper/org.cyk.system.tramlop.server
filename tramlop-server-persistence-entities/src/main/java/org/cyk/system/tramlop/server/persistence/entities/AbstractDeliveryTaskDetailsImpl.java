package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@MappedSuperclass
public abstract class AbstractDeliveryTaskDetailsImpl extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DELIVERY_TASK,unique = true) protected DeliveryTask deliveryTask;
	
	public static final String FIELD_DELIVERY_TASK = "deliveryTask";
	
	public static final String COLUMN_DELIVERY_TASK = DeliveryTask.TABLE_NAME;
}
