package com.ahajri.v2m.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectInto;
import org.unitils.inject.annotation.TestedObject;

import com.ahajri.v2m.domain.data.PersonDataOnDemand;
import com.ahajri.v2m.repository.PersonRepository;
import com.ahajri.v2m.service.PersonService;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class PersonServiceTest extends UnitilsJUnit4 {
	
	@TestedObject
	private PersonService personService;
	
	@TestedObject
	private PersonDataOnDemand personDataOnDemand;
	
	@InjectInto(property="personRepository")
	private PersonRepository personRepository;
	
	@Test
	public void savePersonTest(){
		Person instance = personDataOnDemand.instansiateDummyDomain();
		Person domain = personService.save(instance);
		Assert.assertNotNull(domain);
		Assert.assertNotNull(domain.getId());
	}

}
