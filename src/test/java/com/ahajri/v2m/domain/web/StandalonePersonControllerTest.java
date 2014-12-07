package com.ahajri.v2m.domain.web;

import java.util.Properties;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ahajri.v2m.controller.PersonController;
import com.ahajri.v2m.service.PersonService;

//@RunWith(MockitoJUnitRunner.class)
public class StandalonePersonControllerTest {
//	private MockMvc mockMvc;
//
//	@Mock
//	private PersonService personServiceMock;
//
//	@Before
//	public void setUp() {
//		mockMvc = MockMvcBuilders
//				.standaloneSetup(
//						new PersonController(messageSource(), personServiceMock))
//				.setHandlerExceptionResolvers(exceptionResolver())
//				.setValidator(validator()).setViewResolvers(viewResolver())
//				.build();
//	}
//
//	private HandlerExceptionResolver exceptionResolver() {
//		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
//
//		Properties exceptionMappings = new Properties();
//
////		exceptionMappings
////				.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException",
////						"error/404");
//		exceptionMappings.put("java.lang.Exception", "error/error");
//		exceptionMappings.put("java.lang.RuntimeException", "error/error");
//
//		exceptionResolver.setExceptionMappings(exceptionMappings);
//
//		Properties statusCodes = new Properties();
//
//		statusCodes.put("error/404", "404");
//		statusCodes.put("error/error", "500");
//
//		exceptionResolver.setStatusCodes(statusCodes);
//
//		return exceptionResolver;
//	}
//
//	private MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//
//		messageSource.setBasename("i18n/messages");
//		messageSource.setUseCodeAsDefaultMessage(true);
//
//		return messageSource;
//	}
//
//	private LocalValidatorFactoryBean validator() {
//		return new LocalValidatorFactoryBean();
//	}
//
//	private ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/jsp/");
//		viewResolver.setSuffix(".jsp");
//
//		return viewResolver;
//	}
}
