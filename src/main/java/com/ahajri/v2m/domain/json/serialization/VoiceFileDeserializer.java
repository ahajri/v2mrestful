package com.ahajri.v2m.domain.json.serialization;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

public class VoiceFileDeserializer extends JsonDeserializer<VoiceFile> {

	@Override
	public VoiceFile deserialize(JsonParser jsonParser,
			DeserializationContext context) throws IOException,
			JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		VoiceFile domain = new VoiceFile();
		domain.setId(node.get("id").getLongValue());
		domain.setFormat(node.get("format").getTextValue());
		domain.setPath(node.get("path").getTextValue());
		JsonNode messagesNode = node.path("messages");
		Set<Message> messages = new HashSet<Message>();
		
//		for(JsonNode msgNode:messagesNode.getElements().next()){
//			Message message = new Message();
//			message.setBody(msgNode.get("body").getTextValue());
//			message.setSubject(msgNode.get("subject").getTextValue());
//			message.setId(msgNode.get("id").getLongValue());
//			//receiver
//			JsonNode receiverNode = msgNode.path("receiver");
//			Receiver receiver = new Receiver();
//			receiver.setId(receiverNode.get("id").getLongValue());
//			JsonNode receiverPersonNode = receiverNode.path("person");
//			Person receiverPerson = new Person();
//			receiverPerson.setEmail(receiverPersonNode.get("email").getTextValue());
//			receiverPerson.setId(receiverPersonNode.get("id").getLongValue());
//			receiverPerson.setFullName(receiverPersonNode.get("fullName").getTextValue());
//			receiver.setPerson(receiverPerson);
//			message.setReceiver(receiver);
//			//sender
//			JsonNode senderNode = msgNode.path("receiver");
//			Sender sender = new Sender();
//			sender.setId(senderNode.get("id").getLongValue());
//			JsonNode senderPersonNode = senderNode.path("person");
//			Person senderPerson = new Person();
//			senderPerson.setEmail(senderPersonNode.get("email").getTextValue());
//			senderPerson.setId(senderPersonNode.get("id").getLongValue());
//			senderPerson.setFullName(senderPersonNode.get("fullName").getTextValue());
//			message.setSender(sender);
//			
//			messages.add(message);
//		}
		domain.setMessages(messages);
		return domain;
	}

}
