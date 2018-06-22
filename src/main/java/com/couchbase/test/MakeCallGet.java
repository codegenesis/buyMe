package com.couchbase.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MakeCallGet implements Runnable {

	private Integer index = 0;

	MakeCallGet(Integer i) {
		index = i;
	}

	@Override
	public void run() {
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet("http://localhost:8080/user/User-" + index);
		// long startTime = System.currentTimeMillis();
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// long endTime = System.currentTimeMillis();
		// System.out.println(endTime - startTime);
		try {
			printResponse(response, index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printResponse(HttpResponse response, int i) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			// System.out.println(line);
		}

	}
}
