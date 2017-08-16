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
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
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
	//Session is  associate only Primary data base so we cannot  do save and update operation
	@Bean(name = "derbyEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean derbyEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("derbyDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.braj.derby.entity")
				.build();
	}
	//Include  for oracle  SQL Server and other database  not required for MYSQL and DERBY Datbase
  	 
	/*@Bean
	public AnnotationMBeanExporter mbeanExporter() {
	    AnnotationMBeanExporter exporterThatCatchesExceptions = new AnnotationMBeanExporter();
	    exporterThatCatchesExceptions.addExcludedBean("derbyDataSource");
	    exporterThatCatchesExceptions.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);;
	    return exporterThatCatchesExceptions;
	}*/
	 

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