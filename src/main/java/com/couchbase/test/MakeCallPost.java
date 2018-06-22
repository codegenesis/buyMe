package com.couchbase.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class MakeCallPost implements Runnable {

	private Integer index = 0;

	MakeCallPost(Integer i) {
		index = i;
	}

	@SuppressWarnings({"deprecation", "resource"})
	@Override
	public void run() {
		HttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost("http://localhost:8080/student");
		HttpResponse response = null;
		try {
			StringEntity input = new StringEntity(
					"{\"id\":\"Student-" + index
							+ "\",\"docType\":\"learningassets\",\"version\":\"1.0\",\"language\":\"en_US\",\"label\":\"Text to identify this Course\",\"status\":\"PUBLISHED\",\"assetType\":\"ASSESSMENT\",\"expiresOn\":\"2020-12-12T18:29:50.588Z\",\"scope\":\"PUBLIC\",\"context\":{},\"constraints\":[],\"objectives\":\"UnderstandPythagorasTheorem\",\"tags\":\"Trigonometry,MathsGrade7,Pythagoras\",\"authors\":[{\"name\":\"David\",\"authorType\":\"INSTITUTE\",\"links\":[{\"rel\":\"self\",\"href\":\"/lap/v1/authors/781\"}]}],\"owners\":[{\"name\":\"David\",\"ownerType\":\"INSTITUTE\",\"links\":[{\"rel\":\"self\",\"href\":\"/lap/v1/owners/781 \"}]}],\"extensions\":{},\"evaluationAttributes\":{\"score\":\"0\",\"maxScore\":\"3\",\"mastery\":\"3\",\"noOfAttempsAllowed\":\"3\",\"noOfAttemps\":\"0\"},\"resources\":{\"question\":{\"resourceType\":\"CONTENT\",\"scope\":\"PUBLIC\",\"updatedOn\":\"2020-12-12T18:29:50.588Z\",\"content\":{\"category\":\"assessment\",\"format\":\"glp-assessment-mcqsa-image-as-option\",\"type\":\"application/xhtml+glpxml\"},\"service\":{\"type\":\"assessment\",\"style\":\"REST\",\"param\":{\"URL\":\"/dcms/v1/questions/MCQ-SA-500\",\"method\":\"GET\"}},\"security\":{\"strategy\":\"MD5\",\"param\":{\"checksum\":\"bff23ba0b17fd6bf0cac055745c16835\"}},\"links\":[{\"rel\":\"self\",\"href\":\"/lap/v1/resources/R-MCQ-SA-Q1\"}],\"extensions\":{}},\"answer\":{\"resourceType\":\"CONTENT\",\"scope\":\"PUBLIC\",\"updatedOn\":\"2020-12-12T18:29:50.588Z\",\"content\":{\"category\":\"assessment-response\",\"format\":\"glp-assessment-mcqsa-image-as-option\",\"type\":\"application/xhtml+glpxml\"},\"service\":{\"type\":\"assessment-eval\",\"style\":\"REST\",\"param\":{\"URL\":\"/dcms/v1/answers/ANS-MCQ-SA-500\",\"method\":\"GET\"}},\"security\":{\"strategy\":\"MD5\",\"param\":{\"checksum\":\"bff23ba0b17fd6bf0cac055745c16835\"}},\"links\":[{\"rel\":\"self\",\"href\":\"/lap/v1/resources/R-MCQ-SA-A1\"}],\"extensions\":{}}},\"resourcePlan\":{\"resourceElementType\":\"HEADING\",\"label\":\"Question 1\",\"resourceElements\":[{\"resourceElementType\":\"RESOURCE\",\"label\":\"Question\",\"resourceRef\":\"question\",\"resourceElements\":[{\"resourceElementType\":\"RESOURCE\",\"label\":\"Answer\",\"resourceRef\":\"answer\"}]}]},\"assetGraph\":[]}");
			input.setContentType("application/json");
			postRequest.setEntity(input);
			response = client.execute(postRequest);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
			// System.out.println("Time taken for service " + i + " : " + line);
		}

	}
}
