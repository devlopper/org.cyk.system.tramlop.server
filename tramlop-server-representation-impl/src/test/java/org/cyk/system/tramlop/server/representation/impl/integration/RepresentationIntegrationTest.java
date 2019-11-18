package org.cyk.system.tramlop.server.representation.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.representation.api.TruckRepresentation;
import org.cyk.system.tramlop.server.representation.entities.TruckDto;
import org.cyk.utility.server.representation.test.arquillian.AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class RepresentationIntegrationTest extends AbstractRepresentationArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		try {
			ApplicationScopeLifeCycleListener.createDataBase();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(){
		@SuppressWarnings("unchecked")
		Collection<TruckDto> trucks = (Collection<TruckDto>) __inject__(TruckRepresentation.class)
				.getMany(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST,Boolean.FALSE,null,null,null,null).getEntity();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(TruckDto::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}

}
