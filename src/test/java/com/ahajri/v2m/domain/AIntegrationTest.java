package com.ahajri.v2m.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ahajri.v2m.config.DatabaseConfigProfile;

@ActiveProfiles(DatabaseConfigProfile.POSTGRESQL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-config.xml" }

// classes = { JpaPostgreSqlConfig.class,
// IntegrationTestConfig.class }
)
@Transactional
@Configurable
public abstract class AIntegrationTest<T> {

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
