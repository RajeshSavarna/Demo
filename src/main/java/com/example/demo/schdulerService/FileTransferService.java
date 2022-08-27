package com.example.demo.schdulerService;

public interface FileTransferService {

	boolean uploadFile(String localFilePath, String remoteFilePath);
	
	boolean downloadFile(String localFilePath, String remoteFilePath);
	
}
