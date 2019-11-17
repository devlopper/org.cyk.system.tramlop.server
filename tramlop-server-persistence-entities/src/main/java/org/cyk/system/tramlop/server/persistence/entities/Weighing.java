package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Weighing.TABLE_NAME)
public class Weighing extends AbstractDeliveryTaskDetailsImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name=COLUMN_WEIGHT_IN_KILO_GRAM) private Integer weightInKiloGram;
	
	public Weighing(DeliveryTask deliveryTask,Integer weightInKiloGram) {
		super(deliveryTask);
		setWeightInKiloGram(weightInKiloGram);
	}
	
	public Weighing(String deliveryTaskIdentifier,Integer weightInKiloGram) {
		super(deliveryTaskIdentifier);
		setWeightInKiloGram(weightInKiloGram);
	}
	
	/**/
	
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
	
	public static final String COLUMN_WEIGHT_IN_KILO_GRAM = FIELD_WEIGHT_IN_KILO_GRAM;
	
	public static final String TABLE_NAME = "weighing";
}