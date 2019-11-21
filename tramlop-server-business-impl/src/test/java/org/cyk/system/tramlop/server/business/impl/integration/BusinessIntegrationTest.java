package org.cyk.system.tramlop.server.business.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.business.api.query.FindTaskByDeliveriesCodes;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByTasksCodes;
import org.cyk.system.tramlop.server.business.impl.ApplicationScopeLifeCycleListener;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.WeighingPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
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
	public void delivery_weightBeforeLoad(){
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t1", "d1").setWeightInKiloGram(weight));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
	}
	
	@Test
	public void delivery_weightBeforeLoad_detectAgreement(){
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, null,"t1", "d1").setWeightInKiloGram(weight));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		Weighing weighing =  __inject__(WeighingPersistence.class).readByDeliveryCodeByTaskCode(deliveryCode, Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(weighing).isNotNull();
		assertThat(weighing.getWeightInKiloGram()).isEqualTo(weight);
	}
	
	@Test
	public void delivery_load(){
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t1", "d1").addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01"));
		Collection<Task> tasks = ((FindTaskByDeliveriesCodes)__inject__(TaskBusiness.class)).findByDeliveriesCodes(deliveryCode);
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE);
	}
	
	@Test
	public void delivery_weightAfterLoad(){
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t1", "d1").addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
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
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t1", "d1").addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_ARRIVEE).setWeightInKiloGram(140));
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
		String deliveryCode = __getRandomCode__();
		Integer weight = 15248;
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t1", "d1").addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, weight));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_PESE_CHARGE).setWeightInKiloGram(150));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_ARRIVEE).setWeightInKiloGram(140));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_PESE_VIDE_APRES_DECHARGE).setWeightInKiloGram(500));
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
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, "a1","t1", "d1").addTaskFromCode(Task.CODE_PESE_VIDE_AVANT_CHARGE, 255));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_CHARGE).setProductFromCode("p01").setUnloadingPlaceFromCode("p01"));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_PESE_CHARGE).setWeightInKiloGram(500));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_ARRIVEE).setWeightInKiloGram(490));
		__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, Task.CODE_PESE_VIDE_APRES_DECHARGE).setWeightInKiloGram(255));
		
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier(deliveryCode);
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier(deliveryCode,new Properties().setFields(Delivery.FIELD_TASKS));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE
				, Task.CODE_PESE_CHARGE, Task.CODE_DEPART, Task.CODE_ARRIVEE, Task.CODE_PESE_DECHARGE, Task.CODE_DECHARGE, Task.CODE_PESE_VIDE_APRES_DECHARGE);
	}
}
