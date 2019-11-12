package org.cyk.system.tramlop.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.server.persistence.api.AgreementPersistence;
import org.cyk.system.tramlop.server.persistence.api.AgreementTruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.CustomerPersistence;
import org.cyk.system.tramlop.server.persistence.api.DriverPersistence;
import org.cyk.system.tramlop.server.persistence.api.PlacePersistence;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.system.tramlop.server.persistence.api.query.ReadTruckByAgreementsCodes;
import org.cyk.system.tramlop.server.persistence.entities.Agreement;
import org.cyk.system.tramlop.server.persistence.entities.AgreementTruck;
import org.cyk.system.tramlop.server.persistence.entities.Customer;
import org.cyk.system.tramlop.server.persistence.entities.Driver;
import org.cyk.system.tramlop.server.persistence.entities.Place;
import org.cyk.system.tramlop.server.persistence.entities.Truck;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Contact;
import org.cyk.utility.__kernel__.object.__static__.persistence.embeddedable.Person;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment;
import org.junit.Test;

public class PersistenceIntegrationTest extends AbstractPersistenceArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Test
	public void readTrucksByAgreementsCodes() throws Exception{
		TruckPersistence truckPersistence = __inject__(TruckPersistence.class);
		AgreementPersistence agreementPersistence = __inject__(AgreementPersistence.class);
		userTransaction.begin();
		truckPersistence.createMany(List.of(new Truck("t01"),new Truck("t02"),new Truck("t03"),new Truck("t04"),new Truck("t05")));
		__inject__(CustomerPersistence.class).create(new Customer("c01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d02", new Person("a", "a", "a", new Contact())));
		__inject__(PlacePersistence.class).create(new Place("p01", "Place", null, null));
		agreementPersistence.createMany(List.of(new Agreement("a01", "c01", "p01", Boolean.FALSE),new Agreement("a02", "c01", "p01", Boolean.FALSE),new Agreement("a03", "c01", "p01", Boolean.FALSE)));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a01", "t01", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t02", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t04", "d02")));
		userTransaction.commit();
		Collection<Truck> trucks = truckPersistence.read();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01","t02","t03","t04","t05");
		trucks = ((ReadTruckByAgreementsCodes)truckPersistence).readByAgreementsCodes("a01");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01");
		
		trucks = ((ReadTruckByAgreementsCodes)truckPersistence).readByAgreementsCodes("a02");
		assertThat(trucks).isEmpty();
		
		trucks = ((ReadTruckByAgreementsCodes)truckPersistence).readByAgreementsCodes("a03");
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseExist() throws Exception{
		TruckPersistence truckPersistence = __inject__(TruckPersistence.class);
		AgreementPersistence agreementPersistence = __inject__(AgreementPersistence.class);
		userTransaction.begin();
		truckPersistence.createMany(List.of(new Truck("t01"),new Truck("t02"),new Truck("t03"),new Truck("t04"),new Truck("t05")));
		__inject__(CustomerPersistence.class).create(new Customer("c01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d02", new Person("a", "a", "a", new Contact())));
		__inject__(PlacePersistence.class).create(new Place("p01", "Place", null, null));
		agreementPersistence.createMany(List.of(new Agreement("a01", "c01", "p01", Boolean.FALSE),new Agreement("a02", "c01", "p01", Boolean.FALSE),new Agreement("a03", "c01", "p01", Boolean.TRUE)));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a01", "t01", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t02", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t04", "d02")));
		userTransaction.commit();
		Collection<Truck> trucks = truckPersistence.readWhereAgreementClosedIsFalseExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t01");
	}
	
	@Test
	public void readTruckWhereAgreementClosedIsFalseDoesNotExist() throws Exception{
		TruckPersistence truckPersistence = __inject__(TruckPersistence.class);
		AgreementPersistence agreementPersistence = __inject__(AgreementPersistence.class);
		userTransaction.begin();
		truckPersistence.createMany(List.of(new Truck("t01"),new Truck("t02"),new Truck("t03"),new Truck("t04"),new Truck("t05")));
		__inject__(CustomerPersistence.class).create(new Customer("c01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d01", new Person("a", "a", "a", new Contact())));
		__inject__(DriverPersistence.class).create(new Driver("d02", new Person("a", "a", "a", new Contact())));
		__inject__(PlacePersistence.class).create(new Place("p01", "Place", null, null));
		agreementPersistence.createMany(List.of(new Agreement("a01", "c01", "p01", Boolean.FALSE),new Agreement("a02", "c01", "p01", Boolean.FALSE),new Agreement("a03", "c01", "p01", Boolean.TRUE)));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a01", "t01", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t02", "d01")));
		__inject__(AgreementTruckPersistence.class).createMany(List.of(new AgreementTruck("a03", "t04", "d02")));
		userTransaction.commit();
		Collection<Truck> trucks = truckPersistence.readWhereAgreementClosedIsFalseDoesNotExist();
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t02","t04","t03","t05");
	}
	
}
