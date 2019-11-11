package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=AgreementTruckSecondaryDriver.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=AgreementTruckSecondaryDriver.UNIQUE_CONSTRAINT_AGREEMENT_TRUCK_DRIVER_NAME,columnNames= {AgreementTruckSecondaryDriver.COLUMN_AGREEMENT,AgreementTruckSecondaryDriver.COLUMN_TRUCK,AgreementTruckSecondaryDriver.COLUMN_DRIVER})
		})
public class AgreementTruckSecondaryDriver extends AbstractAgreementTruck implements Serializable {
	private static final long serialVersionUID = 1L;

	public AgreementTruckSecondaryDriver(String agreementCode,String truckCode,String driverCode) {
		super(agreementCode, truckCode, driverCode);
	}
	
	public static final String TABLE_NAME = COLUMN_AGREEMENT+COLUMN_TRUCK+"secondarydriver";	
}