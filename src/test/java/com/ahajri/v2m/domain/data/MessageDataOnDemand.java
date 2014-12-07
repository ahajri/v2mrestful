package com.ahajri.v2m.domain.data;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.ahajri.v2m.domain.Message;
import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.Receiver;
import com.ahajri.v2m.domain.Sender;
import com.ahajri.v2m.domain.VoiceFile;

@Configurable
@Component
public class MessageDataOnDemand {
	
	public Message instansiateDummyDomain() {
		Message dummyDomain = new Message();
		dummyDomain.setBody("Dummy Message");
		dummyDomain.setSubject("Dumy Subject");
		Sender sender=new Sender();
		sender.setId(305);
		Person senderPerson =new Person();
		senderPerson.setId(258);
		sender.setPerson(senderPerson);
		Receiver receiver=new Receiver();receiver.setId(122);
		Person receiverPerson = new Person();
		receiverPerson.setId(253);
		receiver.setPerson(receiverPerson);
		dummyDomain.setSender(sender);
		dummyDomain.setReceiver(receiver);
		VoiceFile voiceFile = new VoiceFile();
		voiceFile.setId(305);
		dummyDomain.setVoiceFile(voiceFile);
		return  dummyDomain;
	}

}
