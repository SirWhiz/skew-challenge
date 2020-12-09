package com.skew.challenge.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.skew.challenge.constants.Constants.CSV_FILE_NAME;

import com.skew.challenge.payload.OrderBook;

public class FileService extends Thread {

	private OrderBook orderBook;
	private String timestamp;
	
	public FileService(OrderBook orderBook, String timestamp) {
		super();
		this.orderBook = orderBook;
		this.timestamp = timestamp;
	}
	
	private String getCSVLineFromBook(OrderBook book, String timestamp) {
		List<String> csvLine = new ArrayList<String>();
		
		csvLine.add(timestamp);
		csvLine.add(book.getSymbol());
		csvLine.add(String.valueOf(book.getBids().get(0).getQuantity()));
		csvLine.add(String.valueOf(book.getBids().get(0).getPrice()));
		csvLine.add(String.valueOf(book.getAsks().get(0).getPrice()));
		csvLine.add(String.valueOf(book.getAsks().get(0).getQuantity()));
		
		StringBuilder stringLine = new StringBuilder();
		for (int i = 0; i < csvLine.size(); i++) {
			stringLine.append(csvLine.get(i));
			if(i < csvLine.size()-1) {
				stringLine.append(",");
			}
		}
		
		return stringLine.toString();
	}
	
	public void run(){
		String csvLine = getCSVLineFromBook(orderBook, timestamp);
		
		try {
			File csvFile = new File(CSV_FILE_NAME);
			FileWriter fileWriter = new FileWriter(csvFile.getPath(), true);
			
			PrintWriter writer = new PrintWriter(fileWriter);
			writer.println(csvLine);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		} catch (IOException e) {
			System.out.println("There was an error tying to write to csv file");
		}
    }
	
}
