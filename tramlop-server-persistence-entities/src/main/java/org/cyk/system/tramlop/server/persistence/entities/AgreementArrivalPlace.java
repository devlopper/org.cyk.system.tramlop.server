package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=AgreementArrivalPlace.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=AgreementArrivalPlace.UNIQUE_CONSTRAINT_AGREEMENT_PLACE_NAME,columnNames= {AgreementArrivalPlace.COLUMN_AGREEMENT,AgreementArrivalPlace.COLUMN_PLACE}
		)})
public class AgreementArrivalPlace extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_AGREEMENT) private Agreement agreement;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PLACE) private Place place;
	@NotNull @Column(name = COLUMN_DURATION_IN_MINUTE) private Integer durationInMinute;
	
	public AgreementArrivalPlace(Agreement agreement,Place place,Integer durationInMinute) {
		setAgreement(agreement);
		setPlace(place);
		setDurationInMinute(durationInMinute);
	}
	
	public AgreementArrivalPlace(String agreementCode,String placeCode,Integer durationInMinute) {
		setAgreementFromCode(agreementCode);
		setPlaceFromCode(placeCode);
		setDurationInMinute(durationInMinute);
	}
	
	public AgreementArrivalPlace setAgreementFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.agreement = null;
		else
			this.agreement = InstanceGetter.getInstance().getByBusinessIdentifier(Agreement.class, code);
		return this;
	}
	
	public AgreementArrivalPlace setPlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.place = null;
		else
			this.place = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_PLACE = "place";
	public static final String FIELD_DURATION_IN_MINUTE = "durationInMinute";
	
	public static final String COLUMN_AGREEMENT = Agreement.TABLE_NAME;
	public static final String COLUMN_PLACE = Place.TABLE_NAME;
	public static final String COLUMN_DURATION_IN_MINUTE = FIELD_DURATION_IN_MINUTE;
	
	public static final String TABLE_NAME = COLUMN_AGREEMENT+"arrival"+COLUMN_PLACE;
	
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_PLACE_NAME = COLUMN_AGREEMENT+COLUMN_PLACE;
}