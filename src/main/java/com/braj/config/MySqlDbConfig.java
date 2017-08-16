package com.braj.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
* MySqlDbConfig class  helps to configure the MySql Database.
* @author  Basavaraj Angadi
* @version 1.0 
*/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityManagerFactory", basePackages = { "com.braj.mysql.repo" })
public class MySqlDbConfig {
	@Bean@ ConfigurationProperties(prefix = "spring.datasource.primary.jndi-name")
    public JndiPropertyHolder primary() {
        return new JndiPropertyHolder();
    }

    @Primary
    @Bean(name = "mySqlDataSource")
    public DataSource primaryDataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        DataSource dataSource = dataSourceLookup.getDataSource("java:/comp/env/jdbc/mysql");
        return dataSource;
    }
    
    //Session is  associated to  Primary data base so we can do save and update operation using
    @Primary
	@Bean(name = "mySqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("mySqlDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.braj.mysql.entity")
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
