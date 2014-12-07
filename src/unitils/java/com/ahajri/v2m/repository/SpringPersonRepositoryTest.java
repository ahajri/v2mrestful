package com.ahajri.v2m.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import com.ahajri.v2m.domain.Person;

@SpringApplicationContext({"classpath:spring/application-config.xml"})
@Transactional(TransactionMode.COMMIT)
public class SpringPersonRepositoryTest extends UnitilsJUnit4 {

	@PersistenceContext
	EntityManager entityManager;
	
	@SpringBean("personRepository")
	public PersonRepository personRepository;
	
	@Test
	public void testPersist(){
		Person domain = new Person();
		domain.setFullName("Anis HAJRI");
		domain.setEmail("ahajri@vmail.tn");
		personRepository.persist(domain);
		Assert.assertNotNull(domain.getId());
	}
}
