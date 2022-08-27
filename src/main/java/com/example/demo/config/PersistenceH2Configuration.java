package com.example.demo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
    basePackages = "com.example.demo.h2repo", 
    entityManagerFactoryRef = "h2EntityManager", 
    transactionManagerRef = "h2TransactionManager"
)
public class PersistenceH2Configuration {
	
    @Autowired
    private Environment env;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(h2DataSource());
        em.setPackagesToScan(
          new String[] { "com.example.demo.h2dbmodel" });

        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("second-hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
          env.getProperty("second-hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource h2DataSource() {
 
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("spring.second-datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.second-datasource.url"));
        dataSource.setUsername(env.getProperty("spring.second-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.second-datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager h2TransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          h2EntityManager().getObject());
        return transactionManager;
    }
}