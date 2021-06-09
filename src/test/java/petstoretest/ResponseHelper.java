package petstoretest;

import org.junit.Assert;

import io.restassured.response.Response;

public class ResponseHelper {
	public static void assertResponse(long expected, Response response, String log) {
		Assert.assertEquals(expected, response.getStatusCode());
		System.out.println("\n[INFO] " + log + ":");
		System.out.println("\nResponse Status Code: " + response.getStatusCode());
		System.out.println("\nResponse body: \n" + response.getBody().asString());
	}
}
