package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Delivery.TABLE_NAME)
public class Delivery extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_AGREEMENT) private Agreement agreement;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_TRUCK) private Truck truck;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DRIVER) private Driver driver;
	@NotNull @Column(name=COLUMN_CLOSED) private Boolean closed;
	
	@Transient private Product product;
	@Transient private Integer weightInKiloGram;
	@Transient private Collection<Task> tasks;
	
	public Delivery(String code,String agreementCode,String truckCode,String driverCode,Boolean closed) {
		super(code);
		setAgreementFromCode(agreementCode);
		setTruckFromCode(truckCode);
		setDriverFromCode(driverCode);
		setClosed(closed);
	}
	
	public Delivery(String code,String agreementCode,String truckCode,String driverCode) {
		this(code, agreementCode, truckCode, driverCode, null);
	}
	
	public Delivery setAgreementFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.agreement = null;
		else
			this.agreement = InstanceGetter.getInstance().getByBusinessIdentifier(Agreement.class, code);
		return this;
	}
	
	public Delivery setTruckFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.truck = null;
		else
			this.truck = InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code);
		return this;
	}
	
	public Delivery setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else
			this.driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
		return this;
	}
	
	public Collection<Task> getTasks(Boolean injectIfNull) {
		if(tasks == null && Boolean.TRUE.equals(injectIfNull))
			tasks = new ArrayList<>();
		return tasks;
	}
	
	public Delivery addTasksFromCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String code : codes)
			getTasks(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Task.class, code));
		return this;
	}
	
	public Delivery addTasksFromCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addTasksFromCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	public Delivery addTaskFromCode(String code,Integer weightInKiloGram) {
		if(StringHelper.isBlank(code))
			return this;
		addTasksFromCodes(CollectionHelper.listOf(code));
		CollectionHelper.getLast(tasks).setWeightInKiloGram(weightInKiloGram);
		return this;
	}
	
	public Delivery addTasks(Collection<Task> tasks) {
		if(CollectionHelper.isEmpty(tasks))
			return this;
		getTasks(Boolean.TRUE).addAll(tasks);
		return this;
	}
	
	public Delivery addTasks(Task...tasks) {
		if(ArrayHelper.isEmpty(tasks))
			return this;
		addTasks(CollectionHelper.listOf(tasks));
		return this;
	}
	
	public Delivery setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_CLOSED = "closed";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
	public static final String FIELD_TASKS = "tasks";
	
	public static final String COLUMN_AGREEMENT = Agreement.TABLE_NAME;
	public static final String COLUMN_TRUCK = Truck.TABLE_NAME;
	public static final String COLUMN_DRIVER = Driver.TABLE_NAME;
	public static final String COLUMN_CLOSED = FIELD_CLOSED;
	
	public static final String TABLE_NAME = "delivery";
}