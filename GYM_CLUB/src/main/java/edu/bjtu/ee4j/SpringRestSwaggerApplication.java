package edu.bjtu.ee4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication



@Configuration

@EnableAutoConfiguration
@EntityScan



@EnableJpaRepositories(basePackages = "webroot.webserv",
entityManagerFactoryRef = "entityManagerFactory",
transactionManagerRef = "transactionManager")

@EnableTransactionManagement
@EnableCaching

public class SpringRestSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestSwaggerApplication.class, args);
	}

}
