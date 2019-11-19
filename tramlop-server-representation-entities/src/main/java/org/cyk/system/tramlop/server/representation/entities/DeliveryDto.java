package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class DeliveryDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private AgreementDto agreement;
	private ProductDto product;
	private TruckDto truck;
	private DriverDto driver;
	private Boolean closed;
	private Integer weightInKiloGram;
	private ArrayList<TaskDto> tasks;
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_CLOSED = "closed";
}