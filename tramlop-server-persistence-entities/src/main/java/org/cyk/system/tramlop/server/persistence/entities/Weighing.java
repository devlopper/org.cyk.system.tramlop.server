package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
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
@Entity @Table(name=Weighing.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=Weighing.UNIQUE_CONSTRAINT_DELIVERY_TYPE_NAME,columnNames= {Weighing.COLUMN_DELIVERY,Weighing.COLUMN_TYPE}
		)})
public class Weighing extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DELIVERY) private Delivery delivery;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_TYPE) private WeighingType type;
	@NotNull @Column(name=COLUMN_WEIGHT_IN_KILO_GRAM) private Integer weightInKiloGram;
	
	public static final String FIELD_DELIVERY = "delivery";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
	
	public static final String COLUMN_DELIVERY = Delivery.TABLE_NAME;
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_WEIGHT_IN_KILO_GRAM = FIELD_WEIGHT_IN_KILO_GRAM;
	
	public static final String TABLE_NAME = "weighing";
	
	public static final String UNIQUE_CONSTRAINT_DELIVERY_TYPE_NAME = COLUMN_DELIVERY+COLUMN_TYPE;
}