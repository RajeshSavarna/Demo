package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExcelRowData {

	private List <Request> request;
	private String updateType;
	
}
