package com.ahajri.v2m.domain.json.adapter;

import java.util.Collection;
import java.util.List;

import com.ahajri.v2m.domain.Receiver;
import com.ahajri.v2m.domain.Sender;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class ReceiverJsonAdapter {

	public static String toJson(Receiver domain) {
		return new JSONSerializer().exclude("*.class").serialize(domain);
	}

	public static String toJson(String[] fields, Sender domain) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(domain);
	}

	public static Receiver fromJsonToReceiver(String json) {
		return new JSONDeserializer<Receiver>().use(null, Receiver.class)
				.deserialize(json);
	}

	public static String toJsonArray(Collection<Receiver> collection) {
		return new JSONSerializer().exclude("*.class")
				.serialize(collection);
	}

	public static String toJsonArray(Collection<Sender> collection,
			String[] fields) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(collection);
	}

	public static Collection<Receiver> fromJsonArrayToReceivers(String json) {
		return new JSONDeserializer<List<Receiver>>().use("values", Receiver.class)
				.deserialize(json);
	}

}
