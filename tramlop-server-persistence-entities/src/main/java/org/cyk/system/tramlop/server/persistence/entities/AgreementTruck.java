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
@Entity @Table(name=AgreementTruck.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=AgreementTruck.UNIQUE_CONSTRAINT_AGREEMENT_TRUCK_NAME,columnNames= {AgreementTruck.COLUMN_AGREEMENT,AgreementTruck.COLUMN_TRUCK})
		,@UniqueConstraint(name=AgreementTruck.UNIQUE_CONSTRAINT_AGREEMENT_DRIVER_NAME,columnNames= {AgreementTruck.COLUMN_AGREEMENT,AgreementTruck.COLUMN_DRIVER})
		})
public class AgreementTruck extends AbstractAgreementTruck implements Serializable {
	private static final long serialVersionUID = 1L;

	public AgreementTruck(String agreementCode,String truckCode,String driverCode) {
		super(agreementCode, truckCode, driverCode);
	}
	
	public static final String TABLE_NAME = COLUMN_AGREEMENT+COLUMN_TRUCK;	
}