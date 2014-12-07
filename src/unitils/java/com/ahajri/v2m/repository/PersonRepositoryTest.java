package com.ahajri.v2m.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.orm.jpa.JpaUnitils;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

import com.ahajri.v2m.domain.Person;

@JpaEntityManagerFactory(persistenceUnit = "testPersistenceUnit", configFile = "classpath:META-INF/persistence.xml")
@Transactional(TransactionMode.COMMIT)
public class PersonRepositoryTest extends UnitilsJUnit4 {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public PersonRepository personRepository;

	@Before
	public void setUp(){
		personRepository=new PersonRepository();
		Assert.assertNotNull(JpaUnitils.getEntityManager());
		JpaUnitils.injectEntityManagerInto(personRepository);
	}
	
	@Test
	public void testPersistPerson(){
		Person dummy = new Person();
		dummy.setFullName("Anis HAJRI");
		dummy.setEmail("ahajri@vmail.tn");
		Assert.assertNotNull(personRepository.persist(dummy).getId());
	}
}
