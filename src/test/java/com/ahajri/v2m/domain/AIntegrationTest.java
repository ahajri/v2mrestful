package com.ahajri.v2m.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.ahajri.v2m.domain.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
(
// locations = { "classpath*:**/WEB-INF/classes/spring/application-config.xml"
// }
loader = AnnotationConfigContextLoader.class
//, classes = { TestContextConfig.class }
 )
@Transactional
public abstract class AIntegrationTest<T> {
	
	@Configuration
	@ImportResource("classpath*:**/WEB-INF/classes/spring/application-config.xml")
	static class ContextConfiguration{
		
		@Bean
		public PersonRepository personRepository(){
			return new PersonRepository();
		}
		
		
		
	}

	public T dummyDomain;

	@Before
	public abstract void setUp();

	@Test
	public abstract void persist();

	@Test
	public abstract void count();

	@Test
	public abstract void findAll();

	@Test
	public abstract void find();

	@Test
	public abstract void findEntries();

	@Test
	public abstract void remove();

	@Test
	public abstract void flush();

	@Test
	public abstract void merge();

}
