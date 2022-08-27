package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Attributes {
	
	@JsonProperty("wallet_type")
	private String walletType;
	
	@JsonProperty("service_provider")
	private String serviceProvider;
	
	@JsonProperty("card_type")
	private String cardType;
	
	private String coupon;
	
	@JsonProperty("EMI_type")
	private String emiType;
	
}
