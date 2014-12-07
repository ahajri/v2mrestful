package com.ahajri.v2m.domain.json.adapter;

import java.util.Collection;
import java.util.List;

import com.ahajri.v2m.domain.Message;
import com.ahajri.v2m.domain.json.serialization.MessageDeserializer;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * 
 * @author ahajri
 */
public class MessageJsonAdapter {

	public static String toJson(Message domain) {
		return new JSONSerializer().exclude("*.class").deepSerialize(domain);
	}

	public static String toJson(String[] fields, Message domain) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(domain);
	}

	public static Message fromJsonToMessage(String json) {
		return new JSONDeserializer<Message>()
		.use(null, Message.class)
				.deserialize(json);
	}

	// FIXME: Ensure this serialization is accepted bu AngularJS
	public static String toJsonArray(Collection<Message> collection) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		return serializer.deepSerialize(collection);
	}

	public static String toJsonArray(Collection<Message> collection,
			String[] fields) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(collection);
	}

	public static Collection<Message> fromJsonArrayToMessages(String json) {
		return new JSONDeserializer<List<Message>>().use("values",
				Message.class).deserialize(json);
	}

}
