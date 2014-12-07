package com.ahajri.v2m.domain;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ahajri.v2m.domain.data.VoiceFileDataOnDemand;
import com.ahajri.v2m.service.VoiceFileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VoiceFileIntegrationTest {

	private static final Logger log = LoggerFactory
			.getLogger(VoiceFileIntegrationTest.class);
	@Autowired
	public VoiceFileService voiceFileService;
	
	
	@Autowired
	public VoiceFileDataOnDemand voiceFileDataOnDemand;
	
	
	private static VoiceFile dummyDomain;
	
	
	@Test
	@Rollback(false)
	public void test1_saveVoiceFile(){
		VoiceFile persisted = voiceFileService.save(voiceFileDataOnDemand.instansiateDummyDomain());
		Assert.assertNotNull(persisted.getId());
		dummyDomain=persisted;
	}
	
	@Test
	public void test2_mergeVoiceFile(){
		VoiceFile merged = voiceFileService.merge(dummyDomain);
		Assert.assertNotNull(merged.getId());
	}
	
	@Test
	public void test3_findVoiceFile(){
		System.out.println(dummyDomain.getId());
		VoiceFile found = voiceFileService.find(dummyDomain.getId());
		Assert.assertNotNull(found);
	}
	
	@Test
	public void test4_removeVoiceFile(){
		System.out.println(dummyDomain);
		voiceFileService.remove(dummyDomain);
		Assert.assertNull(voiceFileService.find(dummyDomain.getId()));
	}
}
