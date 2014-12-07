package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.ahajri.v2m.domain.Person;

public class PersonDeserializer extends JsonDeserializer<Person> {

	@Override
	public Person deserialize(JsonParser jsonParser,
			DeserializationContext context) throws IOException,
			JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		Person domain = new Person();
		domain.setEmail(node.get("email").getTextValue());
		domain.setId(node.get("id").getLongValue());
		domain.setFullName(node.get("fullName").getTextValue());
		return domain;
	}

}
