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
@Entity @Table(name=Loading.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=Loading.UNIQUE_CONSTRAINT_DELIVERY_TYPE_NAME,columnNames= {Loading.COLUMN_DELIVERY,Loading.COLUMN_TYPE}
		)})
public class Loading extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DELIVERY) private Delivery delivery;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_TYPE) private LoadingType type;
	
	public static final String FIELD_DELIVERY = "delivery";
	public static final String FIELD_TYPE = "type";
	
	public static final String COLUMN_DELIVERY = Delivery.TABLE_NAME;
	public static final String COLUMN_TYPE = "type";
	
	public static final String TABLE_NAME = "loading";
	
	public static final String UNIQUE_CONSTRAINT_DELIVERY_TYPE_NAME = COLUMN_DELIVERY+COLUMN_TYPE;
}