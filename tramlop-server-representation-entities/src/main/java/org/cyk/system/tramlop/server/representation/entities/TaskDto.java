package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;
import org.cyk.utility.__kernel__.object.__static__.representation.ExistenceDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class TaskDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private WeighingDto weighing;
	private ProductDto product;
	private PlaceDto unloadingPlace;
	private Integer orderNumber;
	private Integer weightInKiloGram;
	private ExistenceDto existence;
	
	public TaskDto(String identifier,String code,String name) {
		super(identifier, code, name);
	}
	
	public static final String FIELD_ORDER_NUMBER = "orderNumber";
}