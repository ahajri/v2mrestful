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

import com.ahajri.v2m.domain.data.ReceiverDataOnDemand;
import com.ahajri.v2m.domain.data.SenderDataOnDemand;
import com.ahajri.v2m.service.ReceiverService;
import com.ahajri.v2m.service.SenderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SenderReceiverIntegrationTest {

	private static final Logger log = LoggerFactory
			.getLogger(SenderReceiverIntegrationTest.class);
	@Autowired
	public SenderService senderService;
	
	@Autowired
	public ReceiverService receiverService;
	
	@Autowired
	public SenderDataOnDemand senderDataOnDemand;
	
	@Autowired
	ReceiverDataOnDemand receiverDataOnDemand;
	
	private static Sender dummySenderDomain;
	
	private static Receiver dummyReceiverDomain;
	
	@Test
	@Rollback(false)
	@Ignore
	public void test1_saveSender(){
		Sender sender = senderService.save(senderDataOnDemand.instansiateDummyDomain());
		Assert.assertNotNull(sender.getId());
		dummySenderDomain=sender;
	}
	
	@Test
	@Rollback(false)
	@Ignore
	public void test2_saveReceiver(){
		Receiver created = receiverService.save(receiverDataOnDemand.instansiateDummyDomain());
		Assert.assertNotNull(created.getId());
		dummyReceiverDomain=created;
	}
	
	@Test
	public void test3_findSender(){
		Sender found = senderService.find((long) 305);
		Assert.assertNotNull(found);
		System.out.println("[Sender found for ID= "+305+" is: "+found.toString()+"]");
	}
}
