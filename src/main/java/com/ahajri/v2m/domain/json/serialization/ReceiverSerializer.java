package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.ahajri.v2m.domain.Receiver;

public class ReceiverSerializer extends JsonSerializer<Receiver> {

	@Override
	public void serialize(Receiver domain, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeObjectField("person", domain.getPerson());
		jsonGenerator.writeNumberField("id", domain.getId());
		jsonGenerator.writeEndObject();
		
	}

}
