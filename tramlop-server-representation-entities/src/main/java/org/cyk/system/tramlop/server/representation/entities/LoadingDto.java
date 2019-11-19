package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class LoadingDto extends AbstractDeliveryTaskDetailsDtoImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProductDto product;
	
	/**/
	
	public static final String FIELD_PRODUCT = "product";
}