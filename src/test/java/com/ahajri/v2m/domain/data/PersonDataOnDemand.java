package com.ahajri.v2m.domain.data;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.ahajri.v2m.domain.Person;

@Configurable
@Component
public class PersonDataOnDemand {
	
	private final Random random = new Random();
	private final RandomStringUtils randomString = new RandomStringUtils();

	public Person instansiateDummyDomain() {
		Person dummyDomain = new Person();
		String fullName = randomString.randomAlphabetic(7)+" "+randomString.randomAlphabetic(9);
		String mail = randomString.randomAlphabetic(10)+"@vmail.tn";
		dummyDomain.setFullName(fullName);
		dummyDomain.setEmail(mail);
		return  dummyDomain;
	}

}
