package com.ahajri.v2m.domain.json.adapter;

import java.util.Collection;
import java.util.List;

import com.ahajri.v2m.domain.Person;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class PersonJsonAdapter {

	public static String toJson(Person domain) {
		return new JSONSerializer().exclude("*.class").serialize(domain);
	}

	public static String toJson(String[] fields, Person domain) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(domain);
	}

	public static Person fromJsonToPerson(String json) {
		return new JSONDeserializer<Person>().use(null, Person.class)
				.deserialize(json);
	}

	public static String toJsonArray(Collection<Person> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	public static String toJsonArray(Collection<Person> collection,
			String[] fields) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(collection);
	}

	public static Collection<Person> fromJsonArrayToPersons(String json) {
		return new JSONDeserializer<List<Person>>().use("values", Person.class)
				.deserialize(json);
	}

}
