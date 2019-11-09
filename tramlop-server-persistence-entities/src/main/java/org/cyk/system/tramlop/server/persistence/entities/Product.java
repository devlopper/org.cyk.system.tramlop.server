package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.cyk.utility.__kernel__.object.__static__.persistence.AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
@Entity @Table(name=Product.TABLE_NAME)
public class Product extends AbstractIdentifiableSystemScalarStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Product setCode(String code) {
		return (Product) super.setCode(code);
	}
	
	@Override
	public Product setName(String name) {
		return (Product) super.setName(name);
	}
	
	public static final String TABLE_NAME = "product";	
}
