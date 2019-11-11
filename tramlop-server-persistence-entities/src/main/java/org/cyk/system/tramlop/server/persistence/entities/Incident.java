package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Incident.TABLE_NAME)
public class Incident extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @JoinColumn(name = COLUMN_TYPE) private IncidentType type;
	@NotNull @JoinColumn(name = COLUMN_DELIVERY_TASK) private DeliveryTask deliveryTask;
	
	public Incident(String code,String typeCode,String deliveryTaskIdentifier) {
		super(code);
		setTypeFromCode(typeCode);
		setDeliveryTaskFromIdentifier(deliveryTaskIdentifier);
	}
	
	public Incident setTypeFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.type = null;
		else
			this.type = InstanceGetter.getInstance().getByBusinessIdentifier(IncidentType.class, code);
		return this;
	}
	
	public Incident setDeliveryTaskFromIdentifier(String identifier) {
		if(StringHelper.isBlank(code))
			this.deliveryTask = null;
		else
			this.deliveryTask = InstanceGetter.getInstance().getBySystemIdentifier(DeliveryTask.class, code);
		return this;
	}
	
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_DELIVERY_TASK = "deliveryTask";
	
	public static final String COLUMN_TYPE = FIELD_TYPE;
	public static final String COLUMN_DELIVERY_TASK = FIELD_DELIVERY_TASK;
	
	public static final String TABLE_NAME = "incident";
}