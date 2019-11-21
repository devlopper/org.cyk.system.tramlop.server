package org.cyk.system.tramlop.server.business.impl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.CustomerBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.business.api.DriverBusiness;
import org.cyk.system.tramlop.server.business.api.PlaceBusiness;
import org.cyk.system.tramlop.server.business.api.ProductBusiness;
import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.persistence.api.TaskPersistence;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Contact;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;
import org.cyk.utility.__kernel__.random.RandomHelper;
import org.cyk.utility.server.business.AbstractApplicationScopeLifeCycleListenerImplementation;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListenerImplementation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void __initialize__(Object object) {
		__inject__(org.cyk.utility.server.persistence.impl.ApplicationScopeLifeCycleListener.class).initialize(null);
	}
	
	@Override
	public void __destroy__(Object object) {}

	/**/
	
	public static void createDataBase() throws Exception{
		__inject__(TaskBusiness.class).createMany(List.of(new Task(Task.CODE_PESE_VIDE_AVANT_CHARGE,"Peser à vide avant chargement",1,Boolean.TRUE)
				,new Task(Task.CODE_CHARGE,"Charger",2,Boolean.FALSE,Boolean.TRUE),new Task(Task.CODE_PESE_CHARGE,"Peser chargé",3,Boolean.TRUE),new Task(Task.CODE_DEPART,"Départ",4)
				,new Task(Task.CODE_ARRIVEE,"Arrivée",5),new Task(Task.CODE_PESE_DECHARGE,"Peser arrivée",6,Boolean.TRUE),new Task(Task.CODE_DECHARGE,"Décharger",7)
				,new Task(Task.CODE_PESE_VIDE_APRES_DECHARGE,"Peser à vide apres déchargement",8,Boolean.TRUE)));
		__inject__(ProductBusiness.class).createMany(List.of(new Product("p01","Sable",new BigDecimal("0.001"))));
		Integer numberOfTrucks = 10;
		for(Integer index = 1 ; index <= numberOfTrucks ; index = index + 1) {
			__inject__(TruckBusiness.class).create(new Truck("t"+index));
			__inject__(DriverBusiness.class).create(new Driver("d"+index, new Person(RandomHelper.getFirstName(), RandomHelper.getMaleLastName(), "a", new Contact())));
		}
		__inject__(TruckBusiness.class).create(new Truck("t11"));
		__inject__(TruckBusiness.class).create(new Truck("t12"));
		__inject__(CustomerBusiness.class).create(new Customer("c01", new Person(RandomHelper.getFirstName(), RandomHelper.getMaleLastName(), "a", new Contact())));
		__inject__(PlaceBusiness.class).create(new Place("p01", "Place", null, null));
		
		createAgreement("a1", "c01", "p01", Boolean.FALSE,0);
		createAgreement("a2", "c01", "p01", Boolean.FALSE,5);
	}
	
	public static void createDelivery(String agreementCode,String productCode,String deliveryCode,String truckCode,String driverCode,Boolean closed,Collection<String> tasksCodes) {		
		if(CollectionHelper.isNotEmpty(tasksCodes)) {			
			for(String taskCode : tasksCodes) {
				Task task = __inject__(TaskPersistence.class).readByBusinessIdentifier(taskCode);
				if(Boolean.TRUE.equals(task.getWeighable()))
					task.setWeightInKiloGram(1000);
				if(Boolean.TRUE.equals(task.getProductable()))
					task.setProductFromCode("p01").setUnloadingPlaceFromCode("p01");
				if(task.getOrderNumber() == 1)
					__inject__(DeliveryBusiness.class).create(new Delivery(deliveryCode, agreementCode,truckCode,driverCode,closed).addTasks(task));
				else
					__inject__(DeliveryTaskBusiness.class).create(new DeliveryTask(null, deliveryCode, taskCode).setWeightInKiloGram(1000).setProductFromCode("p01").setUnloadingPlaceFromCode("p01"));
			}
		}
	}
	
	public static void createDelivery(String agreementCode,String productCode,String deliveryCode,String truckCode,String driverCode,Boolean closed,String...tasksCodes) {
		createDelivery(agreementCode, productCode, deliveryCode, truckCode, driverCode, closed, ArrayHelper.isEmpty(tasksCodes) ? null : CollectionHelper.listOf(tasksCodes));
	}
	
	public static void createAgreement(String agreementCode,String customerCode,String departurePlaceCode,Boolean closed,Integer numberOfTrucksOfAgreementOffSet) {
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
				createDelivery(agreementCode, "p01", "d"+index, "t"+index,"d"+index, Boolean.FALSE, Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE,Task.CODE_PESE_CHARGE);
			else if(index == 4+numberOfTrucksOfAgreementOffSet)
				createDelivery(agreementCode, "p01", "d"+index, "t"+index,"d"+index, Boolean.FALSE, Task.CODE_PESE_VIDE_AVANT_CHARGE,Task.CODE_CHARGE,Task.CODE_PESE_CHARGE
						,Task.CODE_ARRIVEE,Task.CODE_PESE_VIDE_APRES_DECHARGE);
			
		}
	}
	
}
