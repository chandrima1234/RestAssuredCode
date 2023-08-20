package AuthApi;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTest {
	
	//allure serve allure-results
	@BeforeTest
	public void allureSetup() {
		RestAssured.filters(new AllureRestAssured());
	}

	@Test
	public void jwtauthwithJsonBody() {
		RestAssured.baseURI= "https://fakestoreapi.com";
	String jwtTokenId= 	RestAssured.given()
		   .contentType(ContentType.JSON)
		   .body("{\n"
		   		+ "   \"username\": \"mor_2314\",\n"
		   		+ "  \"password\": \"83r5^_\"\n"
		   		+ "  }")
		   .post("/auth/login")
		   .then()
		   .assertThat().statusCode(200)
		   .and()
		   .extract().path("token");
	System.out.println(jwtTokenId);
	
	String tokenArr[] = jwtTokenId.split("\\.");
	System.out.println(tokenArr[0]);
		
	}
	
	@Test
	public void basicauthwithJsonBody() {
		RestAssured.baseURI= "https://the-internet.herokuapp.com/";
	String responsebody= 	RestAssured.given()
		  .auth().basic("admin", "admin")
		   .get("/basic_auth")
		   .then()
		   .assertThat().statusCode(200)
		   .and()
		   .extract().body().asString();
	System.out.println(responsebody);
	
	
		
	}
	
	
	
	@Test
	public void preemptiveTest() {
		RestAssured.baseURI= "https://the-internet.herokuapp.com/";
	String responsebody= 	RestAssured.given()
		  .auth().preemptive().basic("admin", "admin")
		   .get("/basic_auth")
		   .then()
		   .assertThat().statusCode(200)
		   .and()
		   .extract().body().asString();
	System.out.println(responsebody);
	
	
		
	}
	//3 types of basic auth - basic,preemptive,digest
	
	@Test
	public void digestAuthTest() {
		RestAssured.baseURI= "https://the-internet.herokuapp.com/";
	String responsebody= 	RestAssured.given()
		  .auth().digest("admin", "admin")
		   .get("/basic_auth")
		   .then()
		   .assertThat().statusCode(200)
		   .and()
		   .extract().body().asString();
	System.out.println(responsebody);
	
	
		
	}
	
	
	@Test
	public void apikeyAuthTest() {
		RestAssured.baseURI= "http://api.weatherapi.com";
	String responsebody= 	RestAssured.given()
			.queryParams("q", "London")
			.queryParams("aqi", "no")
			.queryParams("key", "4b043978f4a44fcba6453528230807")
		   .get("/auth.login")
		   .then()
		   .assertThat().statusCode(200)
		   .and()
		   .extract().body().asString();
	System.out.println(responsebody);
	
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
