package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;

import com.ahajri.v2m.domain.Message;
/**
 * 
 * @author ahajri
 */
public class MessageSerializer extends JsonSerializer<Message> {

	@Override
	public void serialize(Message domain, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("body", domain.getBody());
		jsonGenerator.writeStringField("subject", domain.getSubject());
		jsonGenerator.writeNumberField("id", domain.getId());
		// jsonGenerator.writeFieldName("voiceFile");
		jsonGenerator.writeObjectField("voiceFile", domain.getVoiceFile());
		// jsonGenerator.writeFieldName("receiver");
		jsonGenerator.writeObjectField("receiver", domain.getReceiver());
		// jsonGenerator.writeFieldName("sender");
		jsonGenerator.writeObjectField("sender", domain.getSender());
		jsonGenerator.writeEndObject();
	}

	@Override
	public void serializeWithType(Message value, JsonGenerator jgen,
			SerializerProvider provider, TypeSerializer typeSer)
			throws IOException, JsonProcessingException {
		super.serializeWithType(value, jgen, provider, typeSer);
	}
}
