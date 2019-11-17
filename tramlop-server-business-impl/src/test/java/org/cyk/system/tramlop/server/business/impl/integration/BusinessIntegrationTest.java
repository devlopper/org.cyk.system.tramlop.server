package org.cyk.system.tramlop.server.business.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.CustomerBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DriverBusiness;
import org.cyk.system.tramlop.server.business.api.PlaceBusiness;
import org.cyk.system.tramlop.server.business.api.ProductBusiness;
import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.business.api.query.FindTruckByTasksCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Contact;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class BusinessIntegrationTest extends AbstractBusinessArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	/* Create */
	
	@Test
	public void readTrucksByAgreementsCodes_a1() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((FindTruckByAgreementsCodes)__inject__(TruckBusiness.class)).findByAgreementsCodes("a1");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t5");
	}
	
	@Test
	public void readTrucksByAgreementsCodes_a2() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((FindTruckByAgreementsCodes)__inject__(TruckBusiness.class)).findByAgreementsCodes("a2");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t6","t7","t8","t9","t10");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_VIDE_AVANT_CHARGE() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_PESE_VIDE_AVANT_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_CHARGE() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t2","t3","t4","t7","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_CHARGE() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_PESE_CHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t3","t4","t8","t9");
	}
	
	@Test
	public void readTrucksByTasksCodes_CODE_PESE_DECHARGE() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((FindTruckByTasksCodes)__inject__(TruckBusiness.class)).findByTasksCodes(Task.CODE_PESE_DECHARGE);
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t4","t9");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t5","t6","t7","t8","t9","t10");
	}
	/*
	@Test
	public void readTruckWhereAgreementClosedIsFalseDoesNotExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04","t03","t05");
	}
	*/
	@Test
	public void readWhereDeliveryClosedIsFalseExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readWhereDeliveryClosedIsFalseDoesNotExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10","t11","t12");
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t1","t2","t3","t4","t6","t7","t8","t9");
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void readWhereTaskCodeIs_pese_vide() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckBusiness.class).findWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	/**/
	
	private void createDataBaseForReadTruckQueries() throws Exception{
		__inject__(TaskBusiness.class).createMany(List.of(new Task(Task.CODE_PESE_VIDE_AVANT_CHARGE,"Peser à vide avant chargement",1),new Task(Task.CODE_CHARGE,"Charger",2)
				,new Task(Task.CODE_PESE_CHARGE,"Peser chargé",3),new Task(Task.CODE_DEPART,"Départ",4),new Task(Task.CODE_ARRIVEE,"Arrivée",5)
				,new Task(Task.CODE_PESE_DECHARGE,"Peser arrivée",6),new Task(Task.CODE_DECHARGE,"Décharger",7),new Task(Task.CODE_PESE_VIDE_APRES_DECHARGE,"Peser à vide apres déchargement",8)));
		__inject__(ProductBusiness.class).createMany(List.of(new Product("p01","Sable",new BigDecimal("0.001"))));
		Integer numberOfTrucks = 10;
		for(Integer index = 1 ; index <= numberOfTrucks ; index = index + 1) {
			__inject__(TruckBusiness.class).create(new Truck("t"+index));
			__inject__(DriverBusiness.class).create(new Driver("d"+index, new Person("a", "a", "a", new Contact())));
		}
		__inject__(TruckBusiness.class).create(new Truck("t11"));
		__inject__(TruckBusiness.class).create(new Truck("t12"));
		__inject__(CustomerBusiness.class).create(new Customer("c01", new Person("a", "a", "a", new Contact())));
		__inject__(PlaceBusiness.class).create(new Place("p01", "Place", null, null));
		
		createAgreement("a1", "c01", "p01", Boolean.FALSE,0);
		createAgreement("a2", "c01", "p01", Boolean.FALSE,5);
	}
	
	private void createDelivery(String agreementCode,String productCode,String deliveryCode,String truckCode,String driverCode,Boolean closed,Collection<String> tasksCodes) {
		Delivery delivery = new Delivery(deliveryCode, agreementCode, productCode,truckCode,driverCode,closed);
		delivery.addTasksFromCodes(tasksCodes);
		__inject__(DeliveryBusiness.class).create(delivery);
	}
	
	private void createDelivery(String agreementCode,String productCode,String deliveryCode,String truckCode,String driverCode,Boolean closed,String...tasksCodes) {
		createDelivery(agreementCode, productCode, deliveryCode, truckCode, driverCode, closed, CollectionHelper.listOf(tasksCodes));
	}
	
	private void createAgreement(String agreementCode,String customerCode,String departurePlaceCode,Boolean closed,Integer numberOfTrucksOfAgreementOffSet) {
		Agreement agreement = new Agreement(agreementCode, customerCode, departurePlaceCode, null);
		Integer numberOfTrucksOfAgreement = 5;
		for(Integer index = 1+numberOfTrucksOfAgreementOffSet ; index <= numberOfTrucksOfAgreement+numberOfTrucksOfAgreementOffSet ; index = index + 1) {
			agreement.addTruck("t"+index,"d"+index);
		}
		__inject__(AgreementBusiness.class).create(agreement);
		
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
