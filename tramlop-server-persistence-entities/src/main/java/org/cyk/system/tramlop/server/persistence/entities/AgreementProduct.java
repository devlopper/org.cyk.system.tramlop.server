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
@Entity @Table(name=AgreementProduct.TABLE_NAME,
uniqueConstraints= {
		@UniqueConstraint(name=AgreementProduct.UNIQUE_CONSTRAINT_AGREEMENT_PRODUCT_NAME,columnNames= {AgreementProduct.COLUMN_AGREEMENT,AgreementProduct.COLUMN_PRODUCT}
		)})
public class AgreementProduct extends AbstractIdentifiableSystemScalarStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_AGREEMENT) private Agreement agreement;
	@NotNull @ManyToOne @JoinColumn(name = COLUMN_PRODUCT) private Product product;
	@NotNull @Column(name = COLUMN_WEIGHT_IN_KILO_GRAM) private Integer weightInKiloGram;
	
	public AgreementProduct(String agreementCode,String productCode,Integer weightInKiloGram) {
		setAgreementFromCode(agreementCode);
		setProductFromCode(productCode);
		setWeightInKiloGram(weightInKiloGram);
	}
	
	public AgreementProduct setAgreementFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.agreement = null;
		else
			this.agreement = InstanceGetter.getInstance().getByBusinessIdentifier(Agreement.class, code);
		return this;
	}
	
	public AgreementProduct setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	public static final String FIELD_AGREEMENT = "agreement";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
	
	public static final String COLUMN_AGREEMENT = Agreement.TABLE_NAME;
	public static final String COLUMN_PRODUCT = Product.TABLE_NAME;
	public static final String COLUMN_WEIGHT_IN_KILO_GRAM = FIELD_WEIGHT_IN_KILO_GRAM;
	
	public static final String TABLE_NAME = COLUMN_AGREEMENT+COLUMN_PRODUCT;
	
	public static final String UNIQUE_CONSTRAINT_AGREEMENT_PRODUCT_NAME = COLUMN_AGREEMENT+COLUMN_PRODUCT;
}