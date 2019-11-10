package org.cyk.system.tramlop.server.representation.impl;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

import org.cyk.system.tramlop.server.business.api.AgreementArrivalPlaceBusiness;
import org.cyk.system.tramlop.server.business.api.AgreementBusiness;
import org.cyk.system.tramlop.server.business.api.AgreementTruckBusiness;
import org.cyk.system.tramlop.server.business.api.CustomerBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryBusiness;
import org.cyk.system.tramlop.server.business.api.DeliveryTaskBusiness;
import org.cyk.system.tramlop.server.business.api.DriverBusiness;
import org.cyk.system.tramlop.server.business.api.PathBusiness;
import org.cyk.system.tramlop.server.business.api.PlaceBusiness;
import org.cyk.system.tramlop.server.business.api.ProductBusiness;
import org.cyk.system.tramlop.server.business.api.TaskBusiness;
import org.cyk.system.tramlop.server.business.api.TruckBusiness;
import org.cyk.system.tramlop.server.business.api.WeighingBusiness;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementArrivalPlace;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.DeliveryTask;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Path;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.system.tramlop.server.persistence.entities.Weighing;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Contact;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;
import org.cyk.utility.server.representation.impl.AbstractDataLoaderImpl;

@org.cyk.system.tramlop.server.annotation.System
public class DataLoaderImpl extends AbstractDataLoaderImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Response __execute__() throws Exception {
		__inject__(TaskBusiness.class).createMany(List.of(new Task(Task.CODE_PESE_VIDE,"Peser à vide",1),new Task(Task.CODE_CHARGE,"Charger",2)
				,new Task(Task.CODE_PESE_CHARGE,"Peser chargé",3),new Task(Task.CODE_DEPART,"Départ",4),new Task(Task.CODE_ARRIVEE,"Arrivée",5)
				,new Task(Task.CODE_PESE_DECHARGE,"Peser arrivée",6),new Task(Task.CODE_DECHARGE,"Décharger",7)));
		
		__inject__(ProductBusiness.class).createMany(List.of(new Product("SABLE","Sable"),new Product("GRAVIER","Gravier")));
		
		__inject__(TruckBusiness.class).createMany(List.of(new Truck("0101AA01"),new Truck("0202AA01"),new Truck("0303AA01"),new Truck("0404BB01"),new Truck("0202CC01")));
		
		__inject__(CustomerBusiness.class).createMany(List.of(new Customer("c01",new Person("KOFFI", "Gérard", "CNI0001", new Contact("01020304", "abc@mail.com")))
				,new Customer("c02",new Person("KONE", "Mamadou", "CNI0002", new Contact("06070809", "abc01@mail.com")))));
		
		__inject__(DriverBusiness.class).createMany(List.of(new Driver("d01",new Person("ALI", "Idrissa", "CNI0003", new Contact("77777777", "abc03@mail.com")))
				,new Driver("d02",new Person("KADIO", "Marc", "CNI0004", new Contact("88888888", "abc04@mail.com")))));
		
		__inject__(PlaceBusiness.class).createMany(List.of(new Place("PAA","Port Autônome d'Abidjan",null,null),new Place("BK","Bouaké",null,null)
				,new Place("AY","Ayama",null,null),new Place("AGB","Agboville",null,null),new Place("MAN","Man",null,null),new Place("SP","San Perdro",null,null)));
		
		__inject__(PathBusiness.class).createMany(List.of(new Path("PAA_BK","PAA","BK",60 * 2),new Path("PAA_MAN","PAA","MAN",60 * 6),new Path("PAA_SP","PAA","SP",60 * 7)));
		
		__inject__(AgreementBusiness.class).createMany(List.of(new Agreement("c01","c01","SABLE",10000,"PAA")));
		__inject__(AgreementTruckBusiness.class).createMany(List.of(new AgreementTruck("c01","0101AA01","d01")));
		__inject__(AgreementArrivalPlaceBusiness.class).createMany(List.of(new AgreementArrivalPlace("c01","BK")));
		__inject__(AgreementArrivalPlaceBusiness.class).createMany(List.of(new AgreementArrivalPlace("c01","SP")));
		
		__inject__(DeliveryBusiness.class).createMany(List.of(new Delivery("l01","c01","0101AA01")));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0101","l01",Task.CODE_PESE_VIDE)));
		__inject__(WeighingBusiness.class).createMany(List.of(new Weighing("dtl0101",5000)));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0102","l01",Task.CODE_CHARGE)));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0103","l01",Task.CODE_PESE_CHARGE)));
		__inject__(WeighingBusiness.class).createMany(List.of(new Weighing("dtl0103",6000)));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0104","l01",Task.CODE_DEPART)));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0105","l01",Task.CODE_ARRIVEE)));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0106","l01",Task.CODE_PESE_DECHARGE)));
		__inject__(WeighingBusiness.class).createMany(List.of(new Weighing("dtl0106",6000)));
		
		__inject__(DeliveryTaskBusiness.class).createMany(List.of(new DeliveryTask("dtl0107","l01",Task.CODE_DECHARGE)));
		
		return Response.ok().build();
	}
	
}