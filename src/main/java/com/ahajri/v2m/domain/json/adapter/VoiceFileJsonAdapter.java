package com.ahajri.v2m.domain.json.adapter;

import java.util.Collection;
import java.util.List;

import com.ahajri.v2m.domain.VoiceFile;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class VoiceFileJsonAdapter {

	public static String toJson(VoiceFile domain) {
		return new JSONSerializer().exclude("*.class").serialize(domain);
	}

	public static String toJson(String[] fields, VoiceFile domain) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(domain);
	}

	public static VoiceFile fromJsonToVoiceFile(String json) {
		return new JSONDeserializer<VoiceFile>().use(null, VoiceFile.class)
				.deserialize(json);
	}

	public static String toJsonArray(Collection<VoiceFile> collection) {
		return new JSONSerializer().exclude("*.class")
				.serialize(collection);
	}

	public static String toJsonArray(Collection<VoiceFile> collection,
			String[] fields) {
		return new JSONSerializer().include(fields).exclude("*.class")
				.serialize(collection);
	}

	public static Collection<VoiceFile> fromJsonArrayToVoiceFiles(String json) {
		return new JSONDeserializer<List<VoiceFile>>().use("values", VoiceFile.class)
				.deserialize(json);
	}

}
