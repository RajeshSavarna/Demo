package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.DigitalInternalServerError;

@Service
public interface CsvService {

	public String csvService(MultipartFile file) throws DigitalInternalServerError;
}
