package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@MappedSuperclass
public class AbstractAgreementTruck extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_AGREEMENT) private Agreement agreement;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_TRUCK) private Truck truck;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DRIVER) private Driver driver;
	
	public AbstractAgreementTruck(Agreement agreement,Truck truck,Driver driver) {
		setAgreement(agreement);
		setTruck(truck);
		setDriver(driver);
	}
	
	public AbstractAgreementTruck(String agreementCode,String truckCode,String driverCode) {
		setAgreementFromCode(agreementCode);
		setTruckFromCode(truckCode);
		setDriverFromCode(driverCode);
	}
	
	public AbstractAgreementTruck setAgreementFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.agreement = null;
		else
			this.agreement = InstanceGetter.getInstance().getByBusinessIdentifier(Agreement.class, code);
		return this;
	}
	
	public AbstractAgreementTruck setTruckFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.truck = null;
		else
			this.truck = InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code);
		return this;
	}
	
	public AbstractAgreementTruck setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else
			this.driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
		return this;
	}
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_DRIVER = "driver";
	
	public static final String COLUMN_AGREEMENT = Agreement.TABLE_NAME;
	public static final String COLUMN_TRUCK = Truck.TABLE_NAME;
	public static final String COLUMN_DRIVER = Driver.TABLE_NAME;
	
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_TRUCK_NAME = COLUMN_AGREEMENT+COLUMN_TRUCK;
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_DRIVER_NAME = COLUMN_AGREEMENT+COLUMN_DRIVER;
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_TRUCK_DRIVER_NAME = COLUMN_AGREEMENT+COLUMN_TRUCK+COLUMN_DRIVER;
}