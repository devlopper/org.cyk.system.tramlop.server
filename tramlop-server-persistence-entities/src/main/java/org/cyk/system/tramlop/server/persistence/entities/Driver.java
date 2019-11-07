package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=Driver.TABLE_NAME)
public class Driver extends AbstractPersonableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "driver";	
}
