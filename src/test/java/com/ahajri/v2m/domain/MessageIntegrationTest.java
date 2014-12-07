package com.ahajri.v2m.domain;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
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
import org.unitils.thirdparty.org.apache.commons.io.FileUtils;

import antlr.debug.MessageAdapter;

import com.ahajri.v2m.domain.data.MessageDataOnDemand;
import com.ahajri.v2m.domain.json.adapter.MessageJsonAdapter;
import com.ahajri.v2m.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageIntegrationTest {

	private static final Logger log = LoggerFactory
			.getLogger(MessageIntegrationTest.class);

	private static volatile long count;

	@Autowired
	public MessageService messageService;

	@Autowired
	public MessageDataOnDemand messageDataOnDemand;

	private static transient Message dummyDomain;

	private static List<Message> messages;

	@BeforeClass
	public static void setUp() {
		System.out.println("setUp!...");

	}

	@Test
	@Rollback(false)
	@Ignore
	public void test1_persist() {
		dummyDomain = messageDataOnDemand.instansiateDummyDomain();
		Message persistDomain = messageService.save(dummyDomain);
		Assert.assertNotNull(persistDomain.getId());
		dummyDomain = persistDomain;
		System.out.println("persist[Message created: " + dummyDomain.toString()
				+ "]");
	}

	@Test
	public void test2_count() {
		count = messageService.count();
		System.out.println("count:[" + count + " person(s) saved!]");
		Assert.assertNotEquals(count, 0);
	}

	@Test
	public void test3_findAll() {
		messages = messageService.findAll();
		System.out
				.println("findAll[All listed size = " + messages.size() + "]");

		// try {
		// JsonWriter jw = new JsonWriter(System.out);
		// String json = jw.objectToJson(null);
		// System.out.println("##JSON##"+json);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		Assert.assertEquals(count, messages.size());
	}

	@Test
	@Ignore
	public void test4_find() {
		Message found = messageService.find(dummyDomain.getId());
		Assert.assertNotNull(found);
		System.out.println("find:[Person found for ID= " + dummyDomain.getId()
				+ " = " + found + "]");
	}

	@Test
	@Ignore
	public void test5_findEntries() {
		List<Message> dummies = messageService.findEntries(0, 5, null, null);
		Assert.assertEquals(dummies.size(), count);
	}

	@Test
	@Ignore
	public void test6_merge() {
		Message found = messageService.find(dummyDomain.getId());
		found.setBody("Modified Body");
		found.setSubject("Modified Subject");
		Message merged = messageService.merge(found);
		Assert.assertEquals(merged.getBody(), "Modified Body");
	}

	@Test
	@Ignore
	public void test7_remove() {
		messageService.remove(dummyDomain);
		Message found = messageService.find(dummyDomain.getId());
		Assert.assertNull(found);
	}

	@Test
	public void test8_jsonSerialization() {
		try {
			String jsonArray = MessageJsonAdapter.toJsonArray(messages);
			String json = MessageJsonAdapter.toJson(messages.get(0));
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void test9_jsonArrayDeserialisarion() {
		File jsonArrayFile = new File(MessageIntegrationTest.class.getClassLoader()
				.getResource("com//ahajri//v2m//domain//messageArray.json")
				.getFile());
		try {
			String jsonArray = FileUtils.readFileToString(jsonArrayFile, "UTF-8");
			Collection<Message> list = MessageJsonAdapter
					.fromJsonArrayToMessages(jsonArray);
			Assert.assertEquals(list.size(), 4);

		} catch (IOException e) {
			Assert.assertTrue(false);
		}

	}
	@Test
	public void test_10_jsonDeserialisarion() {
		File jsonFile = new File(MessageIntegrationTest.class.getClassLoader()
				.getResource("com//ahajri//v2m//domain//message.json")
				.getFile());
		try {
			String json = FileUtils.readFileToString(jsonFile, "UTF-8");
			Message message = MessageJsonAdapter
					.fromJsonToMessage(json);
			Assert.assertEquals(message.getId(), 154);

		} catch (IOException e) {
			Assert.assertTrue(false);
		}

	}
	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tear down...");
	}

}
