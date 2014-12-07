package com.ahajri.v2m.domain.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ahajri.v2m.configuration.TestContext;
import com.ahajri.v2m.configuration.WebAppContext;
import com.ahajri.v2m.domain.Person;
import com.ahajri.v2m.domain.data.PersonDataOnDemand;
import com.ahajri.v2m.service.PersonService;
import com.ahajri.v2m.utils.TestUtil;

//import static org.springframework.test.web.server.samples.context.SecurityRequestPostProcessors.userDetailsService;

//@Configuration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:**/mvc-config.xml",
//		"classpath:spring/application-config.xml" })
//// @ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@WebAppConfiguration
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebAppPersonControllerTest {
//
//	// @Resource
//	// private FilterChainProxy springSecurityFilterChain;
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	PersonDataOnDemand personDataOnDemand;
//
//	@Autowired
//	private PersonService personService;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Before
//	public void setUp() {
//		// We have to reset our mock between tests because the mock objects
//		// are managed by the Spring container. If we would not reset them,
//		// stubbing and verified behavior would "leak" from one test to another.
//		// Mockito.reset(personService);
//
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//		// .addFilter(springSecurityFilterChain)
//				.build();
//
//	}
//
//	@Test
//	@Transactional
//	public void test1_listAllPersons_should_return_all_persons()
//			throws Exception {
//		Person first = personDataOnDemand.instansiateDummyDomain();
//		Person second = personDataOnDemand.instansiateDummyDomain();
//		mockMvc.perform(get("/people/240")).andExpect(status().isOk());
//		// when(personService.findAll())
//		// .thenReturn(Arrays.asList(first, second));
//		//
//		mockMvc.perform(get("/people/240"))
//				.andExpect(status().isOk())
//				.andExpect(
//						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//				.andExpect(jsonPath("$", hasSize(1)))
//				// .andExpect(jsonPath("$[0].id", is(1)))
//
//				.andExpect(jsonPath("$[1].id", is(240)));
//
////		verify(personService, times(1)).findAll();
////		verifyNoMoreInteractions(personService);
//	}
}
