package com.braj.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
* DerbyDbConfig class helps to configure the derby database.
* @author  Basavaraj Angadi
* @version 1.0 
*/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "derbyEntityManagerFactory", basePackages = { "com.braj.derby.repo"})

public class DerbyDbConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.secondary.jndi-name")
	public JndiPropertyHolder secondary() {
		return new JndiPropertyHolder();
	}
    
	@Bean(name = "derbyDataSource")
	public DataSource secondaryDataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = dataSourceLookup.getDataSource("java:/comp/env/jdbc/derby");
		return dataSource;
	}

	@Bean(name = "derbyEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean derbyEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("derbyDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.braj.derby.entity")
				.build();
	}

	private static class JndiPropertyHolder {
		private String jndiName;

		public String getJndiName() {
			return jndiName;
		}

		public void setJndiName(String jndiName) {
			this.jndiName = jndiName;
		}
	}
}