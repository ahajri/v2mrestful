package com.ahajri.v2m.repository;

import javax.sql.DataSource;

import org.junit.Before;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.orm.jpa.annotation.JpaEntityManagerFactory;

@JpaEntityManagerFactory(persistenceUnit = "testPersistenceUnit", configFile = "persistence.xml")
public abstract class ARepositoryTest<T> extends UnitilsJUnit4 {

	@TestDataSource
	private DataSource dataSource;

	@Before
	public void initializeDao() {
		GenericRepository<T> dao = getRepositoryUnderTest();
	}

	protected abstract GenericRepository<T> getRepositoryUnderTest();
}