package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.object.__static__.representation.ExistenceDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class DeliveryTaskDto extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private DeliveryDto delivery;
	private TaskDto task;
	private ExistenceDto existence;
	private String comment;
	private Integer weightInKiloGram;
	private ProductDto product;
	private PlaceDto unloadingPlace;
	private DriverDto driver;
	
	public ExistenceDto getExistence(Boolean injectIfNull) {
		if(existence == null && Boolean.TRUE.equals(injectIfNull))
			existence = new ExistenceDto();
		return existence;
	}
	
	public static final String FIELD_DELIVERY = "delivery";
	public static final String FIELD_TASK = "task";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_COMMENT = "comment";
}