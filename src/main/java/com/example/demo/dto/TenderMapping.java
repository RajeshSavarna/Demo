package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TenderMapping {

	String attributeName;
	String attributeValue;
	String capillaryTender;
	String masterTender;
}
