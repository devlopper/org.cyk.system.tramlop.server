package org.cyk.system.tramlop.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.CustomerPersistence;
import org.cyk.system.tramlop.server.persistence.api.DeliveryPersistence;
import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.ProductPersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Delivery;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Product;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Contact;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void readTrucksByAgreementsCodes() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a01");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01");
		
		trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a02");
		assertThat(trucks).isEmpty();
		
		trucks = ((ReadTruckByAgreementsCodes)__inject__(TruckPersistence.class)).readByAgreementsCodes("a03");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseDoesNotExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04","t03","t05");
	}
	
	@Test
	public void readWhereDeliveryClosedIsFalseExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereDeliveryClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02");
	}
	
	@Test
	public void readWhereDeliveryClosedIsFalseDoesNotExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01","t03","t04","t05");
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseExist();
		assertThat(trucks).isEmpty();
	}
	
	@Test
	public void readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist() throws Exception{
		createDataBaseForReadTruckQueries();
		Collection<Truck> trucks = __inject__(TruckPersistence.class).readWhereAgreementClosedIsFalseExistAndDeliveryClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01");
	}
	
	/**/
	
	private void createDataBaseForReadTruckQueries() throws Exception{
		userTransaction.begin();
		__inject__(ProductPersistence.class).createMany(List.of(new Product("p01","Sable",new BigDecimal("0.001"))));
		__inject__(TruckPersistence.class).createMany(List.of(new Truck("t01"),new Truck("t02"),new Truck("t03"),new Truck("t04"),new Truck("t05")));
		__inject__(CustomerPersistence.class).create(new Customer("c01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d02", new Person("a", "a", "a", new Contact())));
		__inject__(PlacePersistence.class).create(new Place("p01", "Place", null, null));
		__inject__(AgreementPersistence.class).createMany(List.of(new Agreement("a01", "c01", "p01", Boolean.FALSE),new Agreement("a02", "c01", "p01", Boolean.FALSE)
				,new Agreement("a03", "c01", "p01", Boolean.TRUE)));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a01", "t01", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t02", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t04", "d02")));
		__inject__(DeliveryPersistence.class).createMany(List.of(new Delivery("d01", "a01", "p01","t01","d01",Boolean.TRUE)));
		__inject__(DeliveryPersistence.class).createMany(List.of(new Delivery("d02", "a01", "p01","t01","d01",Boolean.TRUE)));
		__inject__(DeliveryPersistence.class).createMany(List.of(new Delivery("d03", "a03", "p01","t02","d01",Boolean.FALSE)));
		userTransaction.commit();
	}
	
}
