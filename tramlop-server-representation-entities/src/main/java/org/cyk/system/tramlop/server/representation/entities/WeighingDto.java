package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class WeighingDto extends AbstractDeliveryTaskDetailsDtoImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer weightInKiloGram;
	
	/**/
	
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
}