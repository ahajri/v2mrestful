package com.ahajri.v2m.domain.json.adapter;

import java.util.Collection;
import java.util.List;

import com.ahajri.v2m.domain.Receiver;
import com.ahajri.v2m.domain.Sender;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class SenderJsonAdapter {

	public static String toJson(Sender domain) {
		return new JSONSerializer().exclude("*.class").serialize(domain);
	}

	public static String toJson(String[] fields, Sender domain) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(domain);
	}

	public static Sender fromJsonToSender(String json) {
		return new JSONDeserializer<Sender>().use(null, Sender.class)
				.deserialize(json);
	}

	public static String toJsonArray(Collection<Sender> collection) {
		return new JSONSerializer().exclude("*.class")
				.serialize(collection);
	}

	public static String toJsonArray(Collection<Receiver> collection,
			String[] fields) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(collection);
	}

	public static Collection<Sender> fromJsonArrayToSenders(String json) {
		return new JSONDeserializer<List<Sender>>().use("values", Sender.class)
				.deserialize(json);
	}

}
