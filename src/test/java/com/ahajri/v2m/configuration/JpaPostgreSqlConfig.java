package com.ahajri.v2m.configuration;

import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.GENERATE_STATISTICS;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.USE_SQL_COMMENTS;
import static org.hibernate.ejb.AvailableSettings.NAMING_STRATEGY;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQL81Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ahajri.v2m.config.DatabaseConfigProfile;

@Configuration
@Profile(DatabaseConfigProfile.POSTGRESQL)
@PropertySource("classpath:META-INF/database.properties")
@EnableJpaRepositories
@EnableTransactionManagement
public class JpaPostgreSqlConfig extends AJpaCommonConfig {

	@Override
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(getDriverClassName());
		dataSource.setUrl(getUrl());
		dataSource.setUsername(getUser());
		dataSource.setPassword(getPassword());
		dataSource.setValidationQuery(getDatabaseValidationQuery());
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1800000);
		return dataSource;
	}

	@Override
	protected Class<? extends Dialect> getDatabaseDialect() {
		return PostgreSQL81Dialect.class;
	}

	@Override
	protected Properties getJpaProperties() {
		Properties properties = new Properties();
		properties.setProperty(HBM2DDL_AUTO, "none");
		properties.setProperty(GENERATE_STATISTICS, Boolean.TRUE.toString());
		properties.setProperty(SHOW_SQL, Boolean.TRUE.toString());
		properties.setProperty(FORMAT_SQL, Boolean.TRUE.toString());
		properties.setProperty(USE_SQL_COMMENTS, Boolean.TRUE.toString());
		properties.setProperty(CONNECTION_CHAR_SET, getHibernateCharSet());
		properties.setProperty(NAMING_STRATEGY,
				ImprovedNamingStrategy.class.getName());
		return properties;
	}

	@Bean
	public DatabasePopulator databasePopulator(DataSource dataSource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);
		// populator.addScript(new ClassPathResource("/sql/mydata-dml.sql"));
		try {
			populator.populate(dataSource.getConnection());
		} catch (SQLException ignored) {
			//Do Nothing
		}
		return populator;
	}
}
