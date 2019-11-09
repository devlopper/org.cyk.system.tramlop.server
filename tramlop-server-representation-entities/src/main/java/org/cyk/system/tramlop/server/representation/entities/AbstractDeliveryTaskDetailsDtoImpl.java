package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringImpl;

public abstract class AbstractDeliveryTaskDetailsDtoImpl extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected DeliveryTaskDto deliveryTask;
	
	public static final String FIELD_DELIVERY_TASK = "deliveryTask";
}
