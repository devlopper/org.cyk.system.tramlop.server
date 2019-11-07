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
@Entity @Table(name=AgreementPath.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=AgreementPath.UNIQUE_CONSTRAINT_AGREEMENT_PATH_NAME,columnNames= {AgreementPath.COLUMN_AGREEMENT,AgreementPath.COLUMN_PATH}
		)})
public class AgreementPath extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_AGREEMENT) private Agreement agreement;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PATH) private Path path;
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_PATH = "path";
	
	public static final String COLUMN_AGREEMENT = Agreement.TABLE_NAME;
	public static final String COLUMN_PATH = Path.TABLE_NAME;
	
	public static final String TABLE_NAME = COLUMN_AGREEMENT+COLUMN_PATH;
	
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_PATH_NAME = COLUMN_AGREEMENT+COLUMN_PATH;
}