package com.couchbase.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class TestClass extends Thread{

	public static void main(String[] args) throws ClientProtocolException, IOException {

		for (int i = 1; i <= 100; i++) {
			// new Thread(new MakeCallPost(i)).start();
			new Thread(new MakeCallGet(i)).start();
		}
	}
}
