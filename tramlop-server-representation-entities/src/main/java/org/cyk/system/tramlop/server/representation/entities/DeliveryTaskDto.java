package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;

import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.object.__static__.representation.ExistenceDto;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class DeliveryTaskDto extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private DeliveryDto delivery;
	private TaskDto task;
	private TruckDto truck;
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
	
	public DeliveryTaskDto setTruckFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.truck = null;
		else {
			Truck truck = InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code);
			if(truck == null)
				return this;
			this.truck = __inject__(TruckDtoMapper.class).getSource(truck);
		}
		return this;
	}
	
	public DeliveryTaskDto setTaskFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.task = null;
		else {
			Task task = InstanceGetter.getInstance().getByBusinessIdentifier(Task.class, code);
			if(task == null)
				return this;
			this.task = new TaskDto(task.getIdentifier(), null, null);
		}
		return this;
	}
	
	public DeliveryTaskDto setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else {
			Product product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
			if(product == null)
				return this;
			this.product = __inject__(ProductDtoMapper.class).getSource(product);
		}
		return this;
	}
	
	public DeliveryTaskDto setUnloadingPlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.unloadingPlace = null;
		else {
			Place place = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
			if(place == null)
				return this;
			this.unloadingPlace = __inject__(PlaceDtoMapper.class).getSource(place);
		}
		return this;
	}
	
	public static final String FIELD_DELIVERY = "delivery";
	public static final String FIELD_TASK = "task";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_COMMENT = "comment";
}