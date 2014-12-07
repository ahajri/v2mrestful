package com.ahajri.v2m.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ahajri.v2m.service.PersonService;

/**
 * Configuration Class for Integration Tests
 * 
 * @author Anis HAJRI
 *
 */
@Configuration
@ComponentScan(basePackages = "com.ahajri.v2m", excludeFilters = { @ComponentScan.Filter(Configuration.class) })
@EnableTransactionManagement
@EnableJpaRepositories( basePackages = {"com.ahajri.v2m.repository"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class IntegrationTestConfig {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PersonService personService() {
		return new PersonService();
	}
}
