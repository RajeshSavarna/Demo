package com.example.demo.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Request {

	private Date date;
	private String merchantName;
	private String mID;
	private String orderID;
	private String refundIDTPM;
	private String refundStatus;
	private double refundAmount;
	private double ageing111;
	private String remarks;
}
