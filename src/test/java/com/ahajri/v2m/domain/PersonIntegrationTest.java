package com.ahajri.v2m.domain;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.ahajri.v2m.domain.repository.PersonRepository;

public class PersonIntegrationTest extends AIntegrationTest<Person> {

	@Autowired
	public PersonRepository personRepository;

	// @Autowired
	// public void setPersonRepository(PersonRepository personRepository){
	// this.personRepository=personRepository;
	// }

	@Autowired
	public PersonDataOnDemand personDataOnDemand;

	// @Autowired
	// public void setPersonDataOnDemand(PersonDataOnDemand personDataOnDemand){
	// this.personDataOnDemand=personDataOnDemand;
	// }

	@Override
	public void setUp() {
		dummyDomain = personDataOnDemand.instansiateDummyDomain();
	}

	@Override
	public void persist() {
		Person persistDomain = personRepository.persist(dummyDomain);
		Assert.assertNotNull(persistDomain.getId());
	}

	@Override
	public void count() {
		// TODO Auto-generated method stub

	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}

	@Override
	public void findEntries() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		Assert.assertTrue(true);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void merge() {
		// TODO Auto-generated method stub

	}

}
