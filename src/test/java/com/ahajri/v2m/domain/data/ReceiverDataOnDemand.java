package com.ahajri.v2m.domain.data;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.Receiver;

@Configurable
@Component
public class ReceiverDataOnDemand {


	public Receiver instansiateDummyDomain() {
		Receiver dummyDomain = new Receiver();
		Person person = new Person();
		person.setId(253);
		dummyDomain.setPerson(person);
		return dummyDomain;
	}

}
