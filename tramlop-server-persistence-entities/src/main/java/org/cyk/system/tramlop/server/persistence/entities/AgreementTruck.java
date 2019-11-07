package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=AgreementTruck.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=AgreementTruck.UNIQUE_CONSTRAINT_AGREEMENT_TRUCK_DRIVER_NAME,columnNames= {AgreementTruck.COLUMN_AGREEMENT,AgreementTruck.COLUMN_TRUCK,AgreementTruck.COLUMN_DRIVER}
		)})
public class AgreementTruck extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_AGREEMENT) private Agreement agreement;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_TRUCK) private Truck truck;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DRIVER) private Driver driver;
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_DRIVER = "driver";
	
	public static final String COLUMN_AGREEMENT = Agreement.TABLE_NAME;
	public static final String COLUMN_TRUCK = Truck.TABLE_NAME;
	public static final String COLUMN_DRIVER = Driver.TABLE_NAME;
	
	public static final String TABLE_NAME = COLUMN_AGREEMENT+COLUMN_TRUCK;
	
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_TRUCK_DRIVER_NAME = COLUMN_AGREEMENT+COLUMN_TRUCK+COLUMN_DRIVER;
}