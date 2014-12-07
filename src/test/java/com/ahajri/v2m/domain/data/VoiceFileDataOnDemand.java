package com.ahajri.v2m.domain.data;

import java.util.Random;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.VoiceFile;

@Configurable
@Component
public class VoiceFileDataOnDemand {

	private final Random random=new Random();

	public VoiceFile instansiateDummyDomain() {
		VoiceFile dummyDomain = new VoiceFile();
		Person person = new Person();
		person.setId(258);
		dummyDomain.setFormat("mp3");
		dummyDomain.setPath("/media/message/voice/v_"+random.nextInt()+"5.mp3");
		return dummyDomain;
	}

}
