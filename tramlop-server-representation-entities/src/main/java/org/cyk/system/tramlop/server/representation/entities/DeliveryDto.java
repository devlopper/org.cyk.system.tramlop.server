package org.cyk.system.tramlop.server.representation.entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.representation.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class DeliveryDto extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private AgreementDto agreement;
	private ProductDto product;
	private TruckDto truck;
	private DriverDto driver;
	private Boolean closed;
	private Integer weightInKiloGram;
	private Integer weightInKiloGramOfProductAfterLoad;
	private Integer weightInKiloGramOfProductAfterUnload;
	private Integer weightInKiloGramOfProductLost;
	private Integer weightInKiloGramOfProductLostable;
	private ArrayList<TaskDto> tasks;
	private ArrayList<TruckDto> trucks;
	
	public DeliveryDto(String code,String agreementCode,String truckCode,String driverCode,Boolean closed) {
		super(code);
		setAgreementFromCode(agreementCode);
		setTruckFromCode(truckCode);
		setDriverFromCode(driverCode);
		setClosed(closed);
	}
	
	public DeliveryDto(String code,String agreementCode,String truckCode,String driverCode) {
		this(code, agreementCode, truckCode, driverCode, null);
	}
	
	public DeliveryDto setAgreementFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.agreement = null;
		else {
			Agreement agreement = InstanceGetter.getInstance().getByBusinessIdentifier(Agreement.class, code);
			if(agreement == null)
				return this;
			this.agreement = new AgreementDto().setIdentifier(agreement.getIdentifier());
		}
		return this;
	}
	
	public DeliveryDto setTruckFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.truck = null;
		else {
			Truck truck = InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code);
			if(truck == null)
				return this;
			this.truck = new TruckDto(truck.getIdentifier(),null);
		}
		return this;
	}
	
	public DeliveryDto setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else {
			Driver driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
			if(driver == null)
				return this;
			this.driver = new DriverDto(driver.getIdentifier(),null);
		}
		return this;
	}
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_TRUCKS = "trucks";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_CLOSED = "closed";
}