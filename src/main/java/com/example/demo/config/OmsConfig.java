package com.example.demo.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TenderMapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class OmsConfig {
	
	private Map<String, TenderMapping> omsPaymentType;

}
