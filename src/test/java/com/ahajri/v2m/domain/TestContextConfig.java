package com.ahajri.v2m.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = { "com.ahajri.v2m" })
@ImportResource("classpath*:**/WEB-INF/classes/spring/application-config.xml")
public class TestContextConfig {

}
