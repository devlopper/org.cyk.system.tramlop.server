package org.cyk.system.tramlop.server.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @NoArgsConstructor
@Entity @Table(name=Loading.TABLE_NAME)
public class Loading extends AbstractDeliveryTaskDetailsImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PRODUCT) private Product product;
	
	public Loading(DeliveryTask deliveryTask,Product product) {
		super(deliveryTask);
		setProduct(product);
	}
	
	public Loading(String deliveryTaskIdentifier,String productCode) {
		super(deliveryTaskIdentifier);
		setProductFromCode(productCode);
	}
	
	public Loading setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	/**/
	
	public static final String FIELD_PRODUCT = "product";
	
	public static final String COLUMN_PRODUCT = Product.TABLE_NAME;
	
	public static final String TABLE_NAME = "loading";
}