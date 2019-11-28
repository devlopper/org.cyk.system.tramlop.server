package org.cyk.system.tramlop.server.business.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.business.api.query.FindTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByTasksCodes;
import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.WeighingPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadArrivalPlaceByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadProductByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class BusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
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
	public void readTrucksByAgreementsCodes_a1(){
		Collection<Truck> trucks = ((FindTruckByAgreementsCodes)__inject__(TruckBusiness.class)).findByAgreementsCodes("a1");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t5");
	}
	
	@Test
	public void readTrucksByAgreementsCodes_a2(){
		Collection<Truck> trucks = ((FindTruckByAgreementsCodes)__inject__(TruckBusiness.class)).findByAgreementsCodes("a2");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t6","t7","t8","t9","t10");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_VIDE_AVANT_CHARGE(){
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_CHARGE(){
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t2","t3","t4","t7","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_CHARGE(){
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_PESE_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t3","t4","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_DECHARGE(){
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_PESE_DECHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t4","t9");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExist(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t5","t6","t7","t8","t9","t10");
	}
	/*
	@Test
	public void readTruckWhereAgreementClosedIsFalseDoesNotExist(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04","t03","t05");
	}
	*/
	@Test
	public void readWhereDeliveryClosedIsFalseExist(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readWhereDeliveryClosedIsFalseDoesNotExist(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10","t11","t12");
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist_usingFind(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).find(new Properties().setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST));
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void readWhereTaskCodeIs_pese_vide(){
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void agreement_create_withoutProducts_withoutArrivalPlaces_withoutTrucks(){
		Agreement agreement = new Agreement("a100","c01","p01",null);
		__inject__(AgreementBusiness.class).create(agreement);
		assertThat(CollectionHelper.isEmpty(((ReadProductByAgreementsCodes)__inject__(ProductPersistence.class)).readByAgreementsCodes("a100"))).isTrue();
		assertThat(CollectionHelper.isEmpty(((ReadArrivalPlaceByAgreementsCodes)__inject__(PlacePersistence.class)).readArrivalByAgreementsCodes("a100"))).isTrue();
		assertThat(CollectionHelper.isEmpty(((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a100"))).isTrue();
	}
	
	@Test
	public void agreement_create_withProducts_withArrivalPlaces_withTrucks(){
		Agreement agreement = new Agreement("a100","c01","p01",null);
		agreement.addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t1","d1");
		__inject__(AgreementBusiness.class).create(agreement);
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
	
	@Test
	public void delivery_weightBeforeLoad(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t11", "d1").setWeightInKiloGram(weight));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
	}
	
	@Test
	public void delivery_weightBeforeLoad_detectAgreement(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t11", null).setWeightInKiloGram(weight));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
	}
	
	@Test
	public void delivery_load(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t11", null).addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));		
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE);
	}
	
	@Test
	public void delivery_weightAfterLoad(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t11", null).addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE,Task.CODE_PESE_CHARGE,Task.CODE_DEPART);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(150);
	}
	
	@Test
	public void delivery_unload(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t11", null).addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_ARRIVEE).setWeightInKiloGram(140));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE
				,Task.CODE_PESE_CHARGE,Task.CODE_DEPART,Task.CODE_ARRIVEE,Task.CODE_PESE_DECHARGE,Task.CODE_DECHARGE);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(150);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_DECHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(140);
	}
	
	@Test
	public void delivery_weightAfterUnLoad(){
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t11", null).addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_ARRIVEE).setWeightInKiloGram(140));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_PESE_VIDE_APRES_DECHARGE).setWeightInKiloGram(500));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE
				,Task.CODE_PESE_CHARGE,Task.CODE_DEPART,Task.CODE_ARRIVEE,Task.CODE_PESE_DECHARGE,Task.CODE_DECHARGE,Task.CODE_PESE_VIDE_APRES_DECHARGE);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(150);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_DECHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(140);
		weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_APRES_DECHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(500);
	}
	
	@Test
	public void delivery_readTransientFields_tasks() {
		__inject__(AgreementBusiness.class).create(new Agreement("a100","c01","p01",null).addProduct("p01", 5000).addArrivalPlace("p01", 100).addTruck("t11","d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t11", null).addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, 1000));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_ARRIVEE).setWeightInKiloGram(140));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(Task.CODE_PESE_VIDE_APRES_DECHARGE).setWeightInKiloGram(500));
		
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier(deliveryCode);
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier(deliveryCode,new Properties().setFields(Delivery.FIELD_TASKS));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE
				, Task.CODE_PESE_CHARGE, Task.CODE_DEPART, Task.CODE_ARRIVEE, Task.CODE_PESE_DECHARGE, Task.CODE_DECHARGE, Task.CODE_PESE_VIDE_APRES_DECHARGE);
	}
	
	@Test
	public void readTruckByDrivers_a1_t1() {
		Collection<Driver> drivers = __inject__(DriverPersistence.class).readByAgreementCodeByTruckCode("a1", "t1");
		assertThat(drivers).isNotNull();
		assertThat(drivers.stream().map(Driver::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("d1");
	}
	
	@Test
	public void readTruckByDrivers_a1_t2() {
		Collection<Driver> drivers = __inject__(DriverPersistence.class).readByAgreementCodeByTruckCode("a1", "t2");
		assertThat(drivers).isNotNull();
		assertThat(drivers.stream().map(Driver::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("d2","dA");
	}
}
