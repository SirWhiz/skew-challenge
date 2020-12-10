package com.skew.challenge.service.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skew.challenge.payload.OrderBook;
import com.skew.challenge.service.FileService;
import static com.skew.challenge.constants.Constants.CSV_FILE_NAME;

public class FileServiceTest {

	private CountDownLatch lock = new CountDownLatch(1);

	@Test
	public void testFileWrite() throws Exception {
		String exampleBookJson = "{\"exchange\":\"Bitstamp\",\"symbol\":\"BTC/USD\",\"bids\":[ {\"quantity\":2.0,\"price\":18226.44},{\"quantity\":0.412,\"price\":18225.93}],\"asks\":[{\"quantity\":0.11741774,\"price\":18233.0},{\"quantity\":0.29937035,\"price\":18236.1}]}";
		String generatedCSVLine = "1607605437435,BTC/USD,2.0,18226.44,18233.0,0.11741774";
		String fixedTimestamp = "1607605437435";
		OrderBook book = new OrderBook();

		// Empty the content of the file if exist
		new PrintWriter(CSV_FILE_NAME).close();

		book = new ObjectMapper().readValue(exampleBookJson, OrderBook.class);
		new FileService(book, fixedTimestamp).run();

		lock.await(500, TimeUnit.MILLISECONDS);
		List<String> lines = readFile();

		assertEquals(1, lines.size());
		assertEquals(generatedCSVLine, lines.get(0));
	}

	private List<String> readFile() throws Exception {
		BufferedReader br = null;
		List<String> lines = new ArrayList<String>();
		String line = "";

		br = new BufferedReader(new FileReader(CSV_FILE_NAME));
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();

		return lines;
	}

}
