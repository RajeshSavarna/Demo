//package com.example.demo.config;
//
//import java.util.HashMap;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@PropertySource({"classpath:application.properties"})
//@EnableJpaRepositories(
//  basePackages = "com.example.demo.h2repo",
//  entityManagerFactoryRef = "h2AutoEntityManager",
//  transactionManagerRef = "h2AutoTransactionManager")
//public class PersistenceH2AutoConfiguration {
//
//   
//    @Autowired
//    private Environment env;
//    
//    @Bean
//    public LocalContainerEntityManagerFactoryBean h2AutoEntityManager() {
//        LocalContainerEntityManagerFactoryBean em
//          = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(h2AutoDataSource());
//        em.setPackagesToScan(
//          new String[] { "com.example.demo.h2dbmodel" });
//
//        HibernateJpaVendorAdapter vendorAdapter
//          = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
////        properties.put("hibernate.hbm2ddl.auto",
////          env.getProperty("second-hibernate.hbm2ddl.auto"));
////        properties.put("hibernate.dialect",
////          env.getProperty("second-hibernate.dialect"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
////    @Bean
////    @ConfigurationProperties(prefix="spring.l2-datasource")
////    public DataSource h2AutoDataSource() {
////        return DataSourceBuilder.create().build();
////    }
//    @Bean
//    public DataSource h2AutoDataSource() {
// 
//        DriverManagerDataSource dataSource
//          = new DriverManagerDataSource();
//        dataSource.setDriverClassName(
//          env.getProperty("spring.l2-datasource.driverClassName"));
//        dataSource.setUrl(env.getProperty("spring.l2-datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.l2-datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.l2-datasource.password"));
//
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager h2AutoTransactionManager() {
// 
//        JpaTransactionManager transactionManager
//          = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//          h2AutoEntityManager().getObject());
//        return transactionManager;
//    }
//}