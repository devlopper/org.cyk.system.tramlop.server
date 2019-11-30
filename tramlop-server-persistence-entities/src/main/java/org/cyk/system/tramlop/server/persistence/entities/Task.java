package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Existence;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Task.TABLE_NAME)
public class Task extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name = COLUMN_ORDER_NUMBER,unique = true) private Integer orderNumber;
	@NotNull @Column(name = COLUMN_WEIGHABLE) private Boolean weighable;
	@NotNull @Column(name = COLUMN_PRODUCTABLE) private Boolean productable;
	@NotNull @Column(name = COLUMN_STARTABLE) private Boolean startable;
	@NotNull @Column(name = COLUMN_ENDABLE) private Boolean endable;
	
	@Transient private Collection<Delivery> deliveries;
	@Transient private Weighing weighing;
	@Transient private Integer weightInKiloGram;
	@Transient private Product product;
	@Transient private Driver driver;
	@Transient private Place unloadingPlace;
	@Transient private Existence existence;
	
	public Task(String code,String name,Integer orderNumber,Boolean weighable,Boolean productable) {
		super(code,name);
		setOrderNumber(orderNumber);
		setWeighable(weighable);
		setProductable(productable);
	}
	
	public Task(String code,String name,Integer orderNumber,Boolean weighable) {
		this(code, name, orderNumber, weighable,Boolean.FALSE);
	}
	
	public Task(String code,String name,Integer orderNumber) {
		this(code, name, orderNumber, Boolean.FALSE,Boolean.FALSE);
	}
	
	public Task setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	public Task setUnloadingPlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.unloadingPlace = null;
		else
			this.unloadingPlace = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	public Task setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else
			this.driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
		return this;
	}
	
	public static final String FIELD_ORDER_NUMBER = "orderNumber";
	public static final String FIELD_WEIGHABLE = "weighable";
	public static final String FIELD_PRODUCTABLE = "productable";
	public static final String FIELD_STARTABLE = "startable";
	public static final String FIELD_ENDABLE = "endable";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_DELIVERIES = "deliveries";
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
	public static final String FIELD_UNLOADING_PLACE = "unloadingPlace";
	public static final String FIELD_DRIVER = "driver";
	
	public static final String COLUMN_ORDER_NUMBER = FIELD_ORDER_NUMBER;
	public static final String COLUMN_WEIGHABLE = FIELD_WEIGHABLE;
	public static final String COLUMN_PRODUCTABLE = FIELD_PRODUCTABLE;
	public static final String COLUMN_STARTABLE = FIELD_STARTABLE;
	public static final String COLUMN_ENDABLE = FIELD_ENDABLE;
	
	public static final String TABLE_NAME = "task";
	
	public static final String CODE_WEIGH_BEFORE_LOAD = "PESE_AVANT_CHARGE";
	public static final String CODE_LOAD = "CHARGE";
	public static final String CODE_WEIGH_AFTER_LOAD = "PESE_APRES_CHARGE";
	public static final String CODE_WEIGH_BEFORE_UNLOAD = "PESE_AVANT_DECHARGE";
	public static final String CODE_WEIGH_AFTER_UNLOAD = "PESE_APRES_DECHARGE";
}