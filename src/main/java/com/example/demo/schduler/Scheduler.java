package com.example.demo.schduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.schdulerService.FileTransferService;

@EnableAsync
@Component
public class Scheduler {
	
	@Autowired
	private FileTransferService fileTransferService;
	
	Logger log = LoggerFactory.getLogger(Scheduler.class);
	

	//@Scheduled(cron = "0 0/1 * * * *")
	@Async
	//@Scheduled(fixedRate = 1000)
	public void runScheduler() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		log.info("====>>>>>"+ dtf.format(now));
		
		log.info("Start download file");
		boolean isDownloaded = fileTransferService.downloadFile("/home/simplesolution/readme.txt", "/readme.txt");
		log.info("Download result: " + String.valueOf(isDownloaded));
		
//        log.info("Start upload file");
//		boolean isUploaded = fileTransferService.uploadFile("abc.txt", "/abc.txt");
//		log.info("Upload result: " + String.valueOf(isUploaded));
	}

}
