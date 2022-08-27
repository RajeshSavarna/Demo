//package com.example.demo.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@PropertySource({"classpath:application.properties"})
//public class PersistenceLocalAutoConfiguration {
//    
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource localAutoDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//    // localAutoEntityManager bean 
//
//    // localAutoTransactionManager bean
//}