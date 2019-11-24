package org.cyk.system.tramlop.server.representation.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadArrivalPlaceByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadProductByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.system.tramlop.server.representation.api.AgreementRepresentation;
import org.cyk.system.tramlop.server.representation.api.TruckRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementDto;
import org.cyk.system.tramlop.server.representation.entities.TruckDto;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
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

	@Test
	public void agreement_create_withoutProducts_withoutArrivalPlaces_withoutTrucks(){
		AgreementDto agreement = new AgreementDto("a100","c01","p01");
		__inject__(AgreementRepresentation.class).createOne(agreement);
		assertThat(CollectionHelper.isEmpty(((ReadProductByAgreementsCodes)__inject__(ProductPersistence.class)).readByAgreementsCodes("a100"))).isTrue();
		assertThat(CollectionHelper.isEmpty(((ReadArrivalPlaceByAgreementsCodes)__inject__(PlacePersistence.class)).readArrivalByAgreementsCodes("a100"))).isTrue();
		assertThat(CollectionHelper.isEmpty(((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a100"))).isTrue();
	}
	
	@Test
	public void agreement_create_withProducts_withArrivalPlaces_withTrucks(){
		AgreementDto agreement = new AgreementDto("a100","c01","p01").addProduct("p01",30000).addArrivalPlace("p01", 100).addTruck("t1", "d1");
		__inject__(AgreementRepresentation.class).createOne(agreement);
		Collection<Product> products = ((ReadProductByAgreementsCodes)__inject__(ProductPersistence.class)).readByAgreementsCodes("a100");
		assertThat(products).isNotEmpty();
		assertThat(products.stream().map(Product::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("p01");
		Collection<Place> arrivalPlaces = ((ReadArrivalPlaceByAgreementsCodes)__inject__(PlacePersistence.class)).readArrivalByAgreementsCodes("a100");
		assertThat(arrivalPlaces).isNotEmpty();
		assertThat(arrivalPlaces.stream().map(Place::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("p01");
		Collection<Truck> trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a100");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1");
	}
	
}
