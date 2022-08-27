/*
1. File Operations
Nandhini wants to store a decrypted message, and read an encrypted message sent by her friend. 
As part of the design phase, Nandhini creates an EncryptDecryptFile class to store the decrypted 
message in the DecryptionFile.txt file and to read the encrypted message in the EncryptionFile.txt file.

Implement the EncryptDecryptFile class which must implement the public void writeDecryptionFile(String message)
and public String readEncryptionFile() methods.

void writeDecryptionFile(String message):  Create and write the DecryptionFile.txt file in the specified 
file path (must contain the decrypted message passed). 

String readEncryptionFile(): Read the encrypted message from the EncryptionFile.txt  file in 
the specified file path (must return the encrypted message).

Note: Accessing the Solution.filepath variable to know the file path.

The locked code stub validates the implementation of the EncryptDecryptFile class.

Input Format:

The first line contains a decrypted message, which must be stored in the DecryptionFile.txt file.

Sample Input:

Hello World!!!
Sample Output:

Hello World!!!
Output Specification:

The first line contains a decrypted message, which is stored in the DecryptionFile.txt file.
 */
package com.example.demo.service;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

class EncryptDecryptFile{

	String readEncryptionFile() {
		try {
			String encryptFilename = FileOperationsSolution.filepath + "EncryptionFile.txt";
			BufferedReader reader = new BufferedReader(new FileReader(encryptFilename));
			String messageFromFile = reader.readLine();
			reader.close();
			return messageFromFile;
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Occured";
		}
	}

	void writeDecryptionFile(String message){
		try {
			String decryptFilename = FileOperationsSolution.filepath + "DecryptionFile.txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(decryptFilename));
			writer.write(message);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class FileOperationsSolution {
	public static String filepath = System.getenv("OUTPUT_PATH").substring(0, System.getenv("OUTPUT_PATH").lastIndexOf("\\") + 1);

	private static String generateString()
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		StringBuilder generatedString = new StringBuilder(20);
		Random random = new Random();
		for (int i = 0; i < 40; i++) {
			char c = chars[random.nextInt(chars.length)];
			generatedString.append(c);
		}
		return generatedString.toString();
	}

//	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		String message = sc.nextLine();
//
//		try{
//			EncryptDecryptFile f = new EncryptDecryptFile ();
//
//			String encryptFilename = FileOperationsSolution.filepath + "EncryptionFile.txt";
//			String generatedString = generateString();
//			BufferedWriter writer = new BufferedWriter(new FileWriter(encryptFilename));
//			writer.write(generatedString);
//			writer.close();
//
//			if(f.readEncryptionFile().equals(generatedString))
//			{
//				f.writeDecryptionFile(message);
//
//				String decryptFilename = FileOperationsSolution.filepath + "DecryptionFile.txt";
//				BufferedReader reader = new BufferedReader(new FileReader(decryptFilename));
//				String messageFromFile = reader.readLine();
//				reader.close();
//
//				System.out.println(messageFromFile);
//			}
//		}
//		catch (Exception ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//
//	}

}
