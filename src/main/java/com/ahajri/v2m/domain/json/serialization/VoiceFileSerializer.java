package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.ahajri.v2m.domain.VoiceFile;

public class VoiceFileSerializer extends JsonSerializer<VoiceFile> {

	@Override
	public void serialize(VoiceFile domain, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeStartObject();
//		jsonGenerator.writeStartArray();
		jsonGenerator.writeObjectField("messages", domain.getMessages());
//		jsonGenerator.writeEndArray();
		jsonGenerator.writeNumberField("id", domain.getId());
		jsonGenerator.writeStringField("path", domain.getPath());
		jsonGenerator.writeStringField("format", domain.getFormat());
		jsonGenerator.writeEndObject();
		
	}

}
