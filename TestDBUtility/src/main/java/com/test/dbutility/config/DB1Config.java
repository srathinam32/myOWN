package com.test.dbutility.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.test.dbutility.db1.model.Customer;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "db1EntityManager", 
		transactionManagerRef = "db1TransactionManager", 
		basePackages = "com.test.dbutility.db1.repository"
)
public class DB1Config {

	@Bean
	@ConfigurationProperties(prefix = "spring.db1.datasource")
	public DataSource postgresqlDataSource() {
		return DataSourceBuilder
					.create()
					.build();
	}

	@Bean(name = "db1EntityManager")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(postgresqlDataSource())
					.properties(hibernateProperties())
					.packages(Customer.class)
					.persistenceUnit("db1PU")
					.build();
	}

	@Bean(name = "db1TransactionManager")
	public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("db1EntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.format_sql", "true");
		map.put("hibernate.hbm2ddl.auto", "create");
		return map;
	}
}
