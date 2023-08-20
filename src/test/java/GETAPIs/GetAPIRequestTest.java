package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIRequestTest {

	RequestSpecification request;

	
	
	//NON BDD Approach
	@BeforeTest

	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70");

	}

	@Test
	public void getUserApiTest() {
		// Request
		Response response = request.get("/public/v2/users/");
		// ------------------

		// response
		int statuscode = response.statusCode();
		System.out.println("status Code :" + statuscode);
		Assert.assertEquals(statuscode, 200);
		String statusMSg = response.statusLine();
		// status msg
		System.out.println(statusMSg);
		// fetch body
		response.prettyPrint();
		// fetch header
		String contentType = response.header("Content-Type");
		System.out.println(contentType);
		// fetch headers:
		List<Header> headerList = response.headers().asList();
		System.out.println(headerList.size());
		for (Header h : headerList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}
	}

	@Test
	public void getUserApiTestwithQueryParams() {
		// Request
		request.queryParam("name", "naveen");
		request.queryParam("status", "inactive");
		Response response = request.get("/public/v2/users/");
		// ------------------
		// response
		int statuscode = response.statusCode();
		System.out.println("status Code :" + statuscode);
		Assert.assertEquals(statuscode, 200);
		String statusMSg = response.statusLine();
		// status msg
		System.out.println(statusMSg);
		// fetch body
		response.prettyPrint();

	}

	@Test
	public void getUserApiTestwithQueryParams_withHAshMap_APITest() {
		// Request
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("status", "inactive");
		request.queryParams(queryParamsMap);
		Response response = request.get("/public/v2/users/");
		// ------------------
		// response
		int statuscode = response.statusCode();
		System.out.println("status Code :" + statuscode);
		Assert.assertEquals(statuscode, 200);
		String statusMSg = response.statusLine();
		// status msg
		System.out.println(statusMSg);
		// fetch body
		response.prettyPrint();

	}

}
