package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.ahajri.v2m.domain.Message;
import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.Receiver;
import com.ahajri.v2m.domain.Sender;
import com.ahajri.v2m.domain.VoiceFile;

public class MessageDeserializer extends JsonDeserializer<Message> {

	@Override
	public Message deserialize(JsonParser jsonParser,
			DeserializationContext context) throws IOException,
			JsonProcessingException {
		System.out.println("##Deserialize##");
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		Message domain = new Message();
		domain.setId(node.get("id").getLongValue());
		domain.setBody(node.get("body").getTextValue());
		domain.setSubject(node.get("subject").getTextValue());
		//receiver
		JsonNode receiverNode = node.path("receiver");
		Receiver receiver = new Receiver();
		receiver.setId(receiverNode.get("id").getLongValue());
		JsonNode receiverPersonNode = receiverNode.path("person");
		Person receiverPerson = new Person();
		receiverPerson.setEmail(receiverPersonNode.get("email").getTextValue());
		receiverPerson.setId(receiverPersonNode.get("id").getLongValue());
		receiverPerson.setFullName(receiverPersonNode.get("fullName").getTextValue());
		receiver.setPerson(receiverPerson);
		domain.setReceiver(receiver);
		//sender
		JsonNode senderNode = node.path("receiver");
		Sender sender = new Sender();
		sender.setId(senderNode.get("id").getLongValue());
		JsonNode senderPersonNode = senderNode.path("person");
		Person senderPerson = new Person();
		senderPerson.setEmail(senderPersonNode.get("email").getTextValue());
		senderPerson.setId(senderPersonNode.get("id").getLongValue());
		senderPerson.setFullName(senderPersonNode.get("fullName").getTextValue());
		domain.setSender(sender);
		JsonNode voiceFileNode = node.path("voiceFile");
		VoiceFile voiceFile = new VoiceFile();
		voiceFile.setId(voiceFileNode.get("id").getLongValue());
		voiceFile.setFormat(voiceFileNode.get("format").getTextValue());
		voiceFile.setPath(voiceFileNode.get("path").getTextValue());
		domain.setVoiceFile(voiceFile);
		return domain;
	}

}
