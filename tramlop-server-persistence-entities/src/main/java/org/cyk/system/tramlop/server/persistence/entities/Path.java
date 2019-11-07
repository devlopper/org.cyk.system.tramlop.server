package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=Path.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=Path.UNIQUE_CONSTRAINT_PATH_DEPARTURE_ARRIVAL_NAME,columnNames= {Path.COLUMN_DEPARTURE,Path.COLUMN_ARRIVAL}
		)})
public class Path extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_DEPARTURE) private Place departure;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_ARRIVAL) private Place arrival;
	@NotNull @Column(name = COLUMN_DURATION_IN_MINUTE) private Integer durationInMinute;
	
	public static final String FIELD_DEPARTURE = "departure";
	public static final String FIELD_ARRIVAL = "arrival";
	public static final String FIELD_DURATION_IN_MINUTE = "durationInMinute";
	
	public static final String COLUMN_DEPARTURE = FIELD_DEPARTURE;
	public static final String COLUMN_ARRIVAL = FIELD_ARRIVAL;
	public static final String COLUMN_DURATION_IN_MINUTE = FIELD_DURATION_IN_MINUTE;
	
	public static final String TABLE_NAME = "path";
	
	public static final String UNIQUE_CONSTRAINT_PATH_DEPARTURE_ARRIVAL_NAME = COLUMN_DEPARTURE+COLUMN_ARRIVAL;
}