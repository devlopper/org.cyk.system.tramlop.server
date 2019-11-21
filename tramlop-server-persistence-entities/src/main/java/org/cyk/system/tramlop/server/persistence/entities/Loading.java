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
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_UNLOADING_PLACE) private Place unloadingPlace;
	
	public Loading(DeliveryTask deliveryTask,Product product,Place unloadingPlace) {
		super(deliveryTask);
		setProduct(product);
		setUnloadingPlace(unloadingPlace);
	}
	
	public Loading(String deliveryTaskIdentifier,String productCode,String unloadingPlaceCode) {
		super(deliveryTaskIdentifier);
		setProductFromCode(productCode);
		setUnloadingPlaceFromCode(unloadingPlaceCode);
	}
	
	public Loading setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	public Loading setUnloadingPlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.unloadingPlace = null;
		else
			this.unloadingPlace = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	/**/
	
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_UNLOADING_PLACE = "unloadingPlace";
	
	public static final String COLUMN_PRODUCT = Product.TABLE_NAME;
	public static final String COLUMN_UNLOADING_PLACE = "unloading"+Place.TABLE_NAME;
	
	public static final String TABLE_NAME = "loading";
}