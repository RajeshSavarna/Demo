package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Request;
import com.example.demo.exception.DigitalInternalServerError;

@Component
public class CsvServiceImpl implements CsvService {
	
	private Logger log = LoggerFactory.getLogger(CsvServiceImpl.class);

	@Override
	public String csvService(MultipartFile file) throws DigitalInternalServerError {

		try {
			//Workbook workbook = new HSSFWorkbook(file.getInputStream()); 
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			 
			for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
				
				log.info("\n\nIndex No. : " + i + " ___ " + workbook.getSheetName(i) + "\n\n");
			}
			
			sheet = workbook.getSheet("Update MID");
					
			List<String> list = new ArrayList<>(); 
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				String str = "";
				str += "\n\n\n";
				for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
					DataFormatter format = new DataFormatter();
//					if (sheet.getRow(i).getCell(j) != null) {
//						sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
//					}
					str += format.formatCellValue(sheet.getRow(i).getCell(j)) +  " * ";
				}
				list.add(str);
			}
			workbook.close();
			
			this.parseDataFromXcelFile(file, "processed.xls");
			
			return list.toString();
		} catch (DigitalInternalServerError ex) {
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage());
			throw new DigitalInternalServerError("SCV210101", "error occured in service", ex);
		}
	}
	
	private void ABC() {
		Request request1111 = new Request();
		request1111.setAgeing111(776);
		
		request1111.getAgeing111();
	}
	
	private void parseDataFromXcelFile(MultipartFile file, String fileName) throws DigitalInternalServerError {

		if ("".equals("")) return; //testing
		
		log.info("Reading Data from File : "+ fileName);

		File outputFile = new File(fileName);

		try {
			//Workbook workbook = new HSSFWorkbook(file.getInputStream()); 
			//Workbook workbook = WorkbookFactory.create(inputFile);
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter df = new DataFormatter();

			Row headerRow = sheet.getRow(0);
			
			log.info("Cell No 0 : " + df.formatCellValue(headerRow.getCell(0)));
			log.info("Cell No 1 : " + df.formatCellValue(headerRow.getCell(1)));
			log.info("Cell No 2 : " + df.formatCellValue(headerRow.getCell(2)));
			log.info("Cell No 3 : " + df.formatCellValue(headerRow.getCell(3)));
			log.info("Cell No 4 : " + df.formatCellValue(headerRow.getCell(4)));
			log.info("Cell No 5 : " + df.formatCellValue(headerRow.getCell(5)));
			log.info("Cell No 6 : " + df.formatCellValue(headerRow.getCell(6)));
			log.info("Cell No 7 : " + df.formatCellValue(headerRow.getCell(7)));


			if(!df.formatCellValue(headerRow.getCell(0)).trim().equalsIgnoreCase("Date and Time") 
					|| !df.formatCellValue(headerRow.getCell(1)).trim().equalsIgnoreCase("Merchant Name")
					|| !df.formatCellValue(headerRow.getCell(2)).trim().equalsIgnoreCase("MID")
					|| !df.formatCellValue(headerRow.getCell(3)).trim().equalsIgnoreCase("Order ID")
					|| !df.formatCellValue(headerRow.getCell(4)).trim().equalsIgnoreCase("Refund ID TPM")
					|| !df.formatCellValue(headerRow.getCell(5)).trim().equalsIgnoreCase("Refund Status")
					|| !df.formatCellValue(headerRow.getCell(6)).trim().equalsIgnoreCase("Refund Amount")
					|| !df.formatCellValue(headerRow.getCell(7)).trim().equalsIgnoreCase("Ageing")) {

				log.info("Rejecting file becuase of incorrect format or column positions : "+fileName);

				workbook.close();
				return;
			}

			CellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			Font font = workbook.createFont();
			font.setBold(true);
			style.setFont(font);
			Cell reasonHeaderLabel = headerRow.createCell(8);
			reasonHeaderLabel.setCellValue("Remark");
			reasonHeaderLabel.setCellStyle(style);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row rowData = sheet.getRow(i);
				
				if(rowData == null) {
					log.info("Null Row at : "+i);
					continue;
				}

				if((rowData.getCell(1) == null || df.formatCellValue(rowData.getCell(1)).isEmpty())
						&& (rowData.getCell(2) == null || df.formatCellValue(rowData.getCell(2)).isEmpty())
						&& (rowData.getCell(3) == null || df.formatCellValue(rowData.getCell(3)).isEmpty())
						&& (rowData.getCell(4) == null || df.formatCellValue(rowData.getCell(4)).isEmpty())
						&& (rowData.getCell(5) == null || df.formatCellValue(rowData.getCell(5)).isEmpty())
						&& (rowData.getCell(6) == null || df.formatCellValue(rowData.getCell(6)).isEmpty())
						&& (rowData.getCell(7) == null || df.formatCellValue(rowData.getCell(7)).isEmpty())) {

					log.info("End of File at row "+i);
					break; 

				} else if(rowData.getCell(0) == null 
						|| df.formatCellValue(rowData.getCell(0)).isEmpty()
						|| !this.checkDateFormat(df.formatCellValue(rowData.getCell(0)))) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Date and Time field is not in correct format");

				} else if(rowData.getCell(1) == null 
						|| df.formatCellValue(rowData.getCell(1)).isEmpty()) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field Merchant Name is missing");

				} else if(rowData.getCell(2) == null 
						|| df.formatCellValue(rowData.getCell(2)).isEmpty()) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field MID is missing");

				} else if(rowData.getCell(3) == null 
						|| df.formatCellValue(rowData.getCell(3)).isEmpty()) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field Order ID is missing");

				} else if(rowData.getCell(4) == null 
						|| df.formatCellValue(rowData.getCell(4)).isEmpty()) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field Refund ID TPM is missing");

				} else if(rowData.getCell(5) == null 
						|| df.formatCellValue(rowData.getCell(5)).isEmpty()) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field Refund Status is missing");

				} else if(rowData.getCell(6) == null 
						|| df.formatCellValue(rowData.getCell(6)).isEmpty()
						|| !this.checkNumericData(df.formatCellValue(rowData.getCell(6)))) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field Refund Amount is missing/Incorrect format");

				} else if(rowData.getCell(7) == null 
						|| df.formatCellValue(rowData.getCell(7)).isEmpty()
						|| !this.checkNumericData(df.formatCellValue(rowData.getCell(7)))) {

					Cell reasonLabel = rowData.createCell(8);
					reasonLabel.setCellValue("Mandatory field Ageing is missing/Incorrect format");

				}
			}

			FileOutputStream outputStream = new FileOutputStream(outputFile);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();

			log.info("Processing Completed for file : ");

		} catch(Exception ex) {
			log.error(ex.getLocalizedMessage());
			throw new DigitalInternalServerError("RCN-210201", "Error occurred while processing the excel file", ex);
		} finally {
			outputFile.delete();
		}

	}
	private boolean checkDateFormat(String formatCellValue) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(formatCellValue.trim());
            return true;
        } catch (ParseException pe) {
            return false;
        }
	}

	private boolean checkNumericData(String formatCellValue) {
		try {
		    double value = Double.parseDouble(formatCellValue);
		    if(value<0)
		       return false;
		    else
		       return true;
		} catch (NumberFormatException e) {
		    return false;
		}
	}
}
