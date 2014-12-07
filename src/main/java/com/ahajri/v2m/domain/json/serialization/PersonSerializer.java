package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.ahajri.v2m.domain.Person;

public class PersonSerializer extends JsonSerializer<Person> {

	@Override
	public void serialize(Person domain, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("email", domain.getEmail());
		jsonGenerator.writeStringField("fullName", domain.getFullName());
		jsonGenerator.writeNumberField("id", domain.getId());
		jsonGenerator.writeEndObject();
		
	}

}
