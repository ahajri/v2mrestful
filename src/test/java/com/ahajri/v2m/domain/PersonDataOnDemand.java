package com.ahajri.v2m.domain;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class PersonDataOnDemand {

	public Person instansiateDummyDomain() {
		Person dummyDomain = new Person();
		dummyDomain.setFullName("Anis HAJRI");
		dummyDomain.setEmail("ahajri@vmail.tn");
		return  dummyDomain;
	}

}
