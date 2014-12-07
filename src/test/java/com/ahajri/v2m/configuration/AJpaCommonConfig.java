package com.ahajri.v2m.configuration;

import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:META-INF/database.properties")
public abstract class AJpaCommonConfig {
	public static final String UNDEFINED = "**UNDEFINED**";
	public static final String CONNECTION_CHAR_SET = "hibernate.connection.charSet";
	public static final String VALIDATOR_APPLY_TO_DDL = "hibernate.validator.apply_to_ddl";
	public static final String VALIDATOR_AUTOREGISTER_LISTENERS = "hibernate.validator.autoregister_listeners";

	@Autowired
	Environment environment;

	@Value("#{ environment['entity.package'] }")
	private String entityPackage = "com.ahajri.v2m.domain";

	@Bean
	public abstract DataSource dataSource();

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabasePlatform(getDatabaseDialect().getName());
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(entityPackage);
		factory.setDataSource(dataSource());
		if (getJpaProperties() != null) {
			factory.setJpaProperties(getJpaProperties());
		}
		return factory;
	}

	@Bean
	public EntityManager entityManger() {
		return entityManagerFactory().getObject().createEntityManager();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}

	protected abstract Class<? extends Dialect> getDatabaseDialect();

	protected Properties getJpaProperties() {
		return null;
	}

	public String getDatabaseName() {
		return environment.getProperty("database.name", UNDEFINED);
	}

	public String getHost() {
		return environment.getProperty("database.host", UNDEFINED);
	}

	public String getPort() {
		return environment.getProperty("database.port", UNDEFINED);
	}

	public String getUrl() {
		return environment.getProperty("database.url", UNDEFINED);
	}

	public String getUser() {
		return environment.getProperty("database.username", UNDEFINED);
	}

	public String getPassword() {
		return environment.getProperty("database.password", UNDEFINED);
	}

	public String getDriverClassName() {
		return environment.getProperty("database.driverClassName", UNDEFINED);
	}

	public String getDialect() {
		return environment.getProperty("database.dialect", UNDEFINED);
	}

	public String getDatabaseVendor() {
		return environment.getProperty("database.vendor", UNDEFINED);
	}

	public String getHbm2ddl() {
		return environment.getProperty("database.hbm2ddl.auto", "none");
	}

	public String getHibernateCharSet() {
		return environment.getProperty("database.hibernateCharSet", "UTF-8");
	}

	public String getDatabaseValidationQuery() {
		return environment.getProperty("database.validation.query", UNDEFINED);
	}
}
