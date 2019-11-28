package org.cyk.system.tramlop.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckSecondaryDriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.CustomerPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryTaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByTasksCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruckSecondaryDriver;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Contact;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		super.__listenBefore__();
		createDataBaseForReadTruckQueries();		
	}
	
	@Test
	public void readTrucksByAgreementsCodes_a1() {
		Collection<Truck> trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a1");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t5");
	}
	
	@Test
	public void readTrucksByAgreementsCodes_a2() {
		Collection<Truck> trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a2");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t6","t7","t8","t9","t10");
	}
	
	@Test
	public void readAgreement_WhereAgreementClosedIsFalseExistByTrucksCodes_t1() {
		Collection<Agreement> agreements = __inject__(AgreementPersistence.class).readWhereAgreementClosedIsFalseExistByTrucksCodes("t1");
		assertThat(agreements).isNotEmpty();
		assertThat(agreements.stream().map(Agreement::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("a1");
	}
	
	@Test
	public void readAgreement_WhereAgreementClosedIsFalseExistByTrucksCodes_t11() {
		Collection<Agreement> agreements = __inject__(AgreementPersistence.class).readWhereAgreementClosedIsFalseExistByTrucksCodes("t11");
		assertThat(agreements).isEmpty();
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_VIDE_AVANT_CHARGE() {
		Collection<Truck> trucks = ((ReadTruckByTasksCodes)__inject__(TruckPersistence.class)).readByTasksCodes(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_CHARGE() {
		Collection<Truck> trucks = ((ReadTruckByTasksCodes)__inject__(TruckPersistence.class)).readByTasksCodes(Task.CODE_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t2","t3","t4","t7","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_CHARGE() {
		Collection<Truck> trucks = ((ReadTruckByTasksCodes)__inject__(TruckPersistence.class)).readByTasksCodes(Task.CODE_PESE_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t3","t4","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_DECHARGE() {
		Collection<Truck> trucks = ((ReadTruckByTasksCodes)__inject__(TruckPersistence.class)).readByTasksCodes(Task.CODE_PESE_DECHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t4","t9");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExist() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t5","t6","t7","t8","t9","t10");
	}
	/*
	@Test
	public void readTruckWhereAgreementClosedIsFalseDoesNotExist() {
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04","t03","t05");
	}
	*/
	@Test
	public void readTruckWhereDeliveryClosedIsFalseExist() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readTruckWhereDeliveryClosedIsFalseDoesNotExist() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10","t11","t12");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist_usingRead() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).read(new Properties().setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST));
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void readTruckWhereTaskCodeIs_pese_vide() {
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void readDeliveryTransientFields_d1_tasks() {
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d1");
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d1",new Properties().setFields(Delivery.FIELD_TASKS));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
	}
	
	@Test
	public void readDeliveryTransientFields_d1_tasksExistence() {
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d1");
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d1",new Properties().setFields(Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
	}
	
	@Test
	public void readDeliveryTransientFields_d1_taksWeightInKiloGram() {
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d1");
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d1",new Properties().setFields(Delivery.FIELD_TASKS+"."+Delivery.FIELD_WEIGHT_IN_KILO_GRAM));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotNull();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE);
	}
	
	@Test
	public void readDeliveryTransientFields_d2_tasks() {
		Delivery delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d2");
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryPersistence.class).readByBusinessIdentifier("d2",new Properties().setFields(Delivery.FIELD_TASKS));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE);
	}
	
	@Test
	public void readDeliveryTasks() {
		Collection<DeliveryTask> deliveryTasks = __inject__(DeliveryTaskPersistence.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT));
		assertThat(deliveryTasks).isNotNull();
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
	
	/**/
	
	private void createDataBaseForReadTruckQueries() {
		try {
			userTransaction.begin();
			__inject__(TaskPersistence.class).createMany(List.of(new Task(Task.CODE_PESE_VIDE_AVANT_CHARGE,"Peser à vide avant chargement",1),new Task(Task.CODE_CHARGE,"Charger",2,Boolean.FALSE,Boolean.TRUE)
					,new Task(Task.CODE_PESE_CHARGE,"Peser chargé",3),new Task(Task.CODE_DEPART,"Départ",4),new Task(Task.CODE_ARRIVEE,"Arrivée",5)
					,new Task(Task.CODE_PESE_DECHARGE,"Peser arrivée",6),new Task(Task.CODE_DECHARGE,"Décharger",7),new Task(Task.CODE_PESE_VIDE_APRES_DECHARGE,"Peser à vide apres déchargement",8)));
			__inject__(ProductPersistence.class).createMany(List.of(new Product("p01","Sable",new BigDecimal("0.001"))));
			Integer numberOfTrucks = 10;
			for(Integer index = 1 ; index <= numberOfTrucks ; index = index + 1) {
				__inject__(TruckPersistence.class).create(new Truck("t"+index));
				__inject__(DriverPersistence.class).create(new Driver("d"+index, new Person("a", "a", "a", new Contact())));
			}
			__inject__(TruckPersistence.class).create(new Truck("t11"));
			__inject__(TruckPersistence.class).create(new Truck("t12"));
			
			__inject__(DriverPersistence.class).create(new Driver("dA", new Person("a", "a", "a", new Contact())));
			__inject__(DriverPersistence.class).create(new Driver("dB", new Person("a", "a", "a", new Contact())));
			
			__inject__(CustomerPersistence.class).create(new Customer("c01", new Person("a", "a", "a", new Contact())));
			__inject__(PlacePersistence.class).create(new Place("p01", "Place", null, null));
			
			createAgreement("a1", "c01", "p01", Boolean.FALSE,0);
			createAgreement("a2", "c01", "p01", Boolean.FALSE,5);
			
			userTransaction.commit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void createDeliveryTasks(String deliveryCode,Collection<String> tasksCodes) {
		if(StringHelper.isBlank(deliveryCode) || CollectionHelper.isEmpty(tasksCodes))
			return;
		for(String taskCode : tasksCodes) {
			__inject__(DeliveryTaskPersistence.class).create(new DeliveryTask(null, deliveryCode, taskCode));
		}
	}
	/*
	private void createDeliveryTasks(String deliveryCode,String...tasksCodes) {
		if(StringHelper.isBlank(deliveryCode) || ArrayHelper.isEmpty(tasksCodes))
			return;
		createDeliveryTasks(deliveryCode, CollectionHelper.listOf(tasksCodes));
	}
	*/
	private void createDelivery(String agreementCode,String productCode,String deliveryCode,String truckCode,String driverCode,Boolean closed,Collection<String> tasksCodes) {
		__inject__(DeliveryPersistence.class).create(new Delivery(deliveryCode, agreementCode,truckCode,driverCode,closed));
		createDeliveryTasks(deliveryCode, tasksCodes);
	}
	
	private void createDelivery(String agreementCode,String productCode,String deliveryCode,String truckCode,String driverCode,Boolean closed,String...tasksCodes) {
		createDelivery(agreementCode, productCode, deliveryCode, truckCode, driverCode, closed, CollectionHelper.listOf(tasksCodes));
	}
	
	private void createAgreement(String agreementCode,String customerCode,String departurePlaceCode,Boolean closed,Integer numberOfTrucksOfAgreementOffSet) {
		__inject__(AgreementPersistence.class).create(new Agreement(agreementCode, customerCode, departurePlaceCode, closed));
		Integer numberOfTrucksOfAgreement = 5;
		for(Integer index = 1+numberOfTrucksOfAgreementOffSet ; index <= numberOfTrucksOfAgreement+numberOfTrucksOfAgreementOffSet ; index = index + 1) {
			__inject__(AgreementTruckPersistence.class).create(new AgreementTruck(agreementCode, "t"+index, "d"+index));
			if(index == 2) {
				__inject__(AgreementTruckSecondaryDriverPersistence.class).create(new AgreementTruckSecondaryDriver(agreementCode, "t"+index, "dA"));
			}else if(index == 3) {
				__inject__(AgreementTruckSecondaryDriverPersistence.class).create(new AgreementTruckSecondaryDriver(agreementCode, "t"+index, "dB"));
			}
		}
		for(Integer index = 1+numberOfTrucksOfAgreementOffSet ; index <= numberOfTrucksOfAgreement+numberOfTrucksOfAgreementOffSet ; index = index + 1) {
			if(index == 1+numberOfTrucksOfAgreementOffSet)
				createDelivery(agreementCode, "p01", "d"+index, "t"+index,"d"+index, Boolean.FALSE, Task.CODE_PESE_VIDE_AVANT_CHARGE);
			else if(index == 2+numberOfTrucksOfAgreementOffSet)
				createDelivery(agreementCode, "p01", "d"+index, "t"+index,"d"+index, Boolean.FALSE, Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE);
			else if(index == 3+numberOfTrucksOfAgreementOffSet)
				createDelivery(agreementCode, "p01", "d"+index, "t"+index,"d"+index, Boolean.FALSE, Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE,Task.CODE_PESE_CHARGE,Task.CODE_DEPART);
			else if(index == 4+numberOfTrucksOfAgreementOffSet)
				createDelivery(agreementCode, "p01", "d"+index, "t"+index,"d"+index, Boolean.FALSE, Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE,Task.CODE_PESE_CHARGE,Task.CODE_DEPART
						,Task.CODE_ARRIVEE,Task.CODE_PESE_DECHARGE,Task.CODE_DECHARGE,Task.CODE_PESE_VIDE_APRES_DECHARGE);
		}
	}
}
