package com.ahajri.v2m.domain;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ahajri.v2m.domain.data.PersonDataOnDemand;
import com.ahajri.v2m.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml" })
// @TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
// DirtiesContextTestExecutionListener.class,
// TransactionalTestExecutionListener.class })
// @TransactionConfiguration(transactionManager = "transactionManager",
// defaultRollback = false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonIntegrationTest {

	private static final Logger log = LoggerFactory
			.getLogger(PersonIntegrationTest.class);

	private static volatile long count;

	@Autowired
	public PersonService personService;

	@Autowired
	public PersonDataOnDemand personDataOnDemand;

	private static transient Person dummyDomain;

	@BeforeClass
	public static void setUp() {
		System.out.println("setUp!...");

	}

	@Test
	@Rollback(false)
	public void test1_persist() {
		dummyDomain = personDataOnDemand.instansiateDummyDomain();
		Person persistDomain = personService.save(dummyDomain);
		Assert.assertNotNull(persistDomain.getId());
		dummyDomain = persistDomain;
		System.out.println("persist[Person created: " + dummyDomain.toString()
				+ "]");
	}

	@Test
	public void test2_count() {
		count = personService.count();
		System.out.println("count:[" + count + " person(s) saved!]");
		Assert.assertNotEquals(count, 0);
	}

	@Test
	public void test3_findAll() {
		List<Person> persons = personService.findAll();
		System.out.println("findAll[All listed size = " + persons.size() + "]");
		Assert.assertEquals(persons.size(), persons.size());
	}

	@Test
	public void test4_find() {
		Person found = personService.find(dummyDomain.getId());
		Assert.assertNotNull(found);
		System.out.println("find:[Person found for ID= " + dummyDomain.getId()
				+ " = " + found + "]");
	}

	@Test
	public void test5_findEntries() {
		List<Person> persons = personService.findEntries(0, 5, null, null);
		Assert.assertEquals(persons.size(), 5);
	}

	 @Test
	 public void test6_merge() {
	   Person found = personService.find(dummyDomain.getId());
	   found.setEmail("ahajri+"+dummyDomain.getId()+"@vmail.tn");
	   found.setFullName("Anis HAJRI");
	   Person merged = personService.merge(found);
	   Assert.assertEquals(merged.getFullName(), "Anis HAJRI");
	 }
	
	@Test
	public void test7_remove() {
		personService.remove(dummyDomain);
		Person found = personService.find(dummyDomain.getId());
		Assert.assertNull(found);
	}
	
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tear down...");
	}

}
