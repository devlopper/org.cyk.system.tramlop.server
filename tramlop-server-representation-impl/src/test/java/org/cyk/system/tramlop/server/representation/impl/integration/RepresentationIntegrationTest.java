package org.cyk.system.tramlop.server.representation.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.query.FindTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.WeighingPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadArrivalPlaceByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadProductByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.system.tramlop.server.representation.api.AgreementRepresentation;
import org.cyk.system.tramlop.server.representation.api.DeliveryRepresentation;
import org.cyk.system.tramlop.server.representation.api.DeliveryTaskRepresentation;
import org.cyk.system.tramlop.server.representation.api.TruckRepresentation;
import org.cyk.system.tramlop.server.representation.entities.AgreementDto;
import org.cyk.system.tramlop.server.representation.entities.DeliveryDto;
import org.cyk.system.tramlop.server.representation.entities.DeliveryTaskDto;
import org.cyk.system.tramlop.server.representation.entities.TruckDto;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
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
		AgreementDto agreement = new AgreementDto("a100","c01","COCODY");
		__inject__(AgreementRepresentation.class).createOne(agreement);
		assertThat(CollectionHelper.isEmpty(((ReadProductByAgreementsCodes)__inject__(ProductPersistence.class)).readByAgreementsCodes("a100"))).isTrue();
		assertThat(CollectionHelper.isEmpty(((ReadArrivalPlaceByAgreementsCodes)__inject__(PlacePersistence.class)).readArrivalByAgreementsCodes("a100"))).isTrue();
		assertThat(CollectionHelper.isEmpty(((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a100"))).isTrue();
	}
	
	@Test
	public void agreement_create_withProducts_withArrivalPlaces_withTrucks(){
		AgreementDto agreement = new AgreementDto("a100","c01","COCODY").addProduct("SABLE",30000).addArrivalPlace("ANYAMA", 100).addTruck("t1", "d1");
		__inject__(AgreementRepresentation.class).createOne(agreement);
		Collection<Product> products = ((ReadProductByAgreementsCodes)__inject__(ProductPersistence.class)).readByAgreementsCodes("a100");
		assertThat(products).isNotEmpty();
		assertThat(products.stream().map(Product::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("SABLE");
		Collection<Place> arrivalPlaces = ((ReadArrivalPlaceByAgreementsCodes)__inject__(PlacePersistence.class)).readArrivalByAgreementsCodes("a100");
		assertThat(arrivalPlaces).isNotEmpty();
		assertThat(arrivalPlaces.stream().map(Place::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("ANYAMA");
		Collection<Truck> trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a100");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1");
	}
	
	@Test
	public void delivery_weightBeforeLoad(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","COCODY",null).addProduct("SABLE", 5000).addArrivalPlace("ANYAMA", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryRepresentation.class).createOne(new DeliveryDto(deliveryCode, null,"t11", null).setWeightInKiloGram(15248));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_WEIGH_BEFORE_LOAD);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_BEFORE_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(15248);
	}
	
	@Test
	public void delivery_load(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","COCODY",null).addProduct("SABLE", 5000).addArrivalPlace("ANYAMA", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryRepresentation.class).createOne(new DeliveryDto(deliveryCode, null,"t11", null).setWeightInKiloGram(15248));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_LOAD).setProductFromCode("SABLE").setUnloadingPlaceFromCode("ANYAMA").setDriverFromCode("d1"));		
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_WEIGH_BEFORE_LOAD,Task.CODE_LOAD);
	}
	
	@Test
	public void delivery_weightAfterLoad(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","COCODY",null).addProduct("SABLE", 5000).addArrivalPlace("ANYAMA", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryRepresentation.class).createOne(new DeliveryDto(deliveryCode, null,"t11", null).setWeightInKiloGram(15248));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_LOAD).setProductFromCode("SABLE").setUnloadingPlaceFromCode("ANYAMA").setDriverFromCode("d1"));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_WEIGH_BEFORE_LOAD,Task.CODE_LOAD,Task.CODE_WEIGH_AFTER_LOAD);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_BEFORE_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(15248);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_AFTER_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(150);
	}
	
	@Test
	public void delivery_unload(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","COCODY",null).addProduct("SABLE", 5000).addArrivalPlace("ANYAMA", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryRepresentation.class).createOne(new DeliveryDto(deliveryCode, null,"t11", null).setWeightInKiloGram(15248));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_LOAD).setProductFromCode("SABLE").setUnloadingPlaceFromCode("ANYAMA").setDriverFromCode("d1"));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_WEIGH_BEFORE_LOAD,Task.CODE_LOAD
				,Task.CODE_WEIGH_AFTER_LOAD,Task.CODE_WEIGH_BEFORE_UNLOAD);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_BEFORE_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(15248);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_AFTER_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(150);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_BEFORE_UNLOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(140);
	}
	
	@Test
	public void delivery_weightAfterUnLoad(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","COCODY",null).addProduct("SABLE", 5000).addArrivalPlace("ANYAMA", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryRepresentation.class).createOne(new DeliveryDto(deliveryCode, null,"t11", null).setWeightInKiloGram(15248));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_LOAD).setProductFromCode("SABLE").setUnloadingPlaceFromCode("ANYAMA").setDriverFromCode("d1"));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_AFTER_UNLOAD).setWeightInKiloGram(500));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_WEIGH_BEFORE_LOAD,Task.CODE_LOAD
				,Task.CODE_WEIGH_AFTER_LOAD,Task.CODE_WEIGH_BEFORE_UNLOAD,Task.CODE_WEIGH_AFTER_UNLOAD);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_BEFORE_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(15248);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_AFTER_LOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(150);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_BEFORE_UNLOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(140);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_WEIGH_AFTER_UNLOAD);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(500);
	}
	
	@Test
	public void delivery_readTransientFields_tasks() {
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","COCODY",null).addProduct("SABLE", 5000).addArrivalPlace("ANYAMA", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryRepresentation.class).createOne(new DeliveryDto(deliveryCode, null,"t11", null).setWeightInKiloGram(15248));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_LOAD).setProductFromCode("SABLE").setUnloadingPlaceFromCode("ANYAMA").setDriverFromCode("d1"));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		__inject__(DeliveryTaskRepresentation.class).createOne(new DeliveryTaskDto().setTruckFromCode("t11").setTaskFromCode(Task.CODE_WEIGH_AFTER_UNLOAD).setWeightInKiloGram(500));
		
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier(deliveryCode);
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier(deliveryCode,new Properties().setFields(Delivery.FIELD_TASKS));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_WEIGH_BEFORE_LOAD,Task.CODE_LOAD
				, Task.CODE_WEIGH_AFTER_LOAD, Task.CODE_WEIGH_BEFORE_UNLOAD, Task.CODE_WEIGH_AFTER_UNLOAD);
	}
	
}
