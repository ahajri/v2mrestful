package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.ahajri.v2m.domain.Sender;

public class SenderSerializer extends JsonSerializer<Sender> {

	@Override
	public void serialize(Sender domain, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeObjectField("person", domain.getPerson());
		jsonGenerator.writeNumberField("id", domain.getId());
		jsonGenerator.writeEndObject();
		
	}

}
