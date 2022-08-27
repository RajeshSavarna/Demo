package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.OmsConfig;
import com.example.demo.dbmodel.TestTable;
import com.example.demo.dto.Attributes;
import com.example.demo.dto.ExcelRowData;
import com.example.demo.dto.Payment;
import com.example.demo.dto.Request;
import com.example.demo.dto.TenderMapping;
import com.example.demo.exception.DigitalInternalServerError;
import com.example.demo.h2dbmodel.H2Table;
import com.example.demo.h2repo.H2Repo;
import com.example.demo.repo.TestTableRepo;
import com.example.demo.service.CsvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


@RequestMapping("/v1")
@RestController
public class CsvConverterController {
	
	private Logger log = LoggerFactory.getLogger(CsvConverterController.class);

	CsvService csvService;
	
	@Autowired
	TestTableRepo repo;
	@Autowired
	H2Repo repoH2;
	
//	@Value("${test}")
//	String test;
	
	OmsConfig oms;
	
	/**
	 * @Constructor
	 */
	@Autowired
	public CsvConverterController(CsvService csvService, OmsConfig oms) {
		this.csvService = csvService;
		this.oms = oms;
	}
	
	@PostMapping(path = "/uploadCsvFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCsvFile(@RequestParam("file") MultipartFile file) {
		
		if (file.isEmpty()) {
			return "emptyFile";
        } else {
        	return "DataReceived";
        }
		
		
	}
	
	@PostMapping(path = "/workbook", consumes="multipart/form-data")
	public String printWorkBook(@RequestParam("file") MultipartFile file) throws DigitalInternalServerError {
		try {
			return csvService.csvService(file);	
		} catch (DigitalInternalServerError ex) {
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new DigitalInternalServerError("CSV110101", "error from controller", ex);
		}	
	}
	
	@PostMapping(path = "/update")
	public ExcelRowData updateAggr(@RequestBody ExcelRowData requestObj) throws DigitalInternalServerError {
		try {
			log.info("Number of Objects :" + requestObj.getRequest().size());
			log.info(new Gson().toJson(requestObj));
			
			Request req = requestObj.getRequest().get(0);
			req.setRemarks("testing");
			
			ExcelRowData response = new ExcelRowData();
			response.setRequest(Arrays.asList(req)); //List.of(req)
			
			log.info(new Gson().toJson(requestObj));
			
			return response;	
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new DigitalInternalServerError("CSV120101", "error from controller", ex);
		}	
	}
	
	@PostMapping(path = "/save-two-db")
	public String abcSave(@RequestHeader("type") String type, @RequestBody Object a) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		if  (type.equalsIgnoreCase("h2")) {
			H2Table convertValue = objMapper.convertValue(a, H2Table.class);
			H2Table res = repoH2.save(convertValue);
			return objMapper.writeValueAsString(res);
		} else if (type.equalsIgnoreCase("test")) {
			TestTable res = repo.save(objMapper.convertValue(a, TestTable.class));
			return objMapper.writeValueAsString(res);
		}
		return "Wrong header value";
	}
	
	@GetMapping(path = "/getAll-two-db")
	public String abcGetAll(@RequestHeader("type") String type) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		if  (type.equalsIgnoreCase("h2")) {
			List<H2Table> res = repoH2.findAll();
			return objMapper.writeValueAsString(res);
		} else if (type.equalsIgnoreCase("test")) {
			List<TestTable> res = repo.findAll();
			return objMapper.writeValueAsString(res);
		}
		return "Wrong header value";
	}
	
	
	@GetMapping(path = "/OMS-POC")
	public Payment omsPOC(@RequestHeader("Tender_Type") String tenderType) {
		Payment p = new Payment();
		Attributes attribute = new Attributes();
		
		String customTenderType = tenderType.replace(" ", "_");
		TenderMapping tenderMap  = oms.getOmsPaymentType().get(customTenderType);
		if (null == tenderMap) return p;
		p.setMode(tenderMap.getCapillaryTender());
		
		if (null != tenderMap.getAttributeName()) {
			switch(tenderMap.getAttributeName()) {
				case "wallet_type":
					attribute.setWalletType(tenderMap.getAttributeValue());
					p.setAttribute(attribute);
					break;
				case "service_provider":
					attribute.setServiceProvider(tenderMap.getAttributeValue());
					p.setAttribute(attribute);
					break;
				case "card_type":
					attribute.setCardType(tenderMap.getAttributeValue());
					p.setAttribute(attribute);
					break;
				case "coupon":
					attribute.setCoupon(tenderMap.getAttributeValue());
					p.setAttribute(attribute);
					break;
				case "EMI_type":
					attribute.setEmiType(tenderMap.getAttributeValue());
					p.setAttribute(attribute);
					break;
				default:
					break;
			}
		}
		
		return p;
	}
	
}
