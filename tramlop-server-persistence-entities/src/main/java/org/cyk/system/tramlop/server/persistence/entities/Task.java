package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=Task.TABLE_NAME)
public class Task extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name = COLUMN_ORDER_NUMBER) private Integer orderNumber;
	
	public static final String FIELD_ORDER_NUMBER = "orderNumber";
	
	public static final String COLUMN_ORDER_NUMBER = FIELD_ORDER_NUMBER;
	
	public static final String TABLE_NAME = "task";
	
	public static final String CODE_PESEE_A_VIDE = "PESEE_A_VIDE";
	public static final String CODE_CHARGE = "CHARGE";
	public static final String CODE_PESEE_APRES_CHARGE = "PESEE_APRES_CHARGE";
	public static final String CODE_DEPART = "DEPART";
	public static final String CODE_ARRIVEE = "ARRIVEE";
	public static final String CODE_PESEE_AVANT_DECHARGE = "PESEE_AVANT_DECHARGE";
	public static final String CODE_DECHARGE = "DECHARGE";
}