package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringImpl;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=IncidentResolution.TABLE_NAME)
public class IncidentResolution extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @JoinColumn(name = COLUMN_INCIDENT) private Incident incident;
	@NotNull @Column(name = COLUMN_COMMENT) private String comment;
	
	public IncidentResolution(String incidentCode,String comment) {
		setIncidentFromCode(incidentCode);
		setComment(comment);
	}
	
	public IncidentResolution setIncidentFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.incident = null;
		else
			this.incident = InstanceGetter.getInstance().getByBusinessIdentifier(Incident.class, code);
		return this;
	}
	
	public static final String FIELD_INCIDENT = "incident";
	public static final String FIELD_COMMENT = "comment";
	
	public static final String COLUMN_INCIDENT = FIELD_INCIDENT;
	public static final String COLUMN_COMMENT = FIELD_COMMENT;
	
	public static final String TABLE_NAME = "incidentresolution";
}