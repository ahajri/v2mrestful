package com.ahajri.v2m.domain.data;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.Sender;

@Configurable
@Component
public class SenderDataOnDemand {


	public Sender instansiateDummyDomain() {
		Sender dummyDomain = new Sender();
		Person person = new Person();
		person.setId(258);
		dummyDomain.setPerson(person);
		return dummyDomain;
	}

}
