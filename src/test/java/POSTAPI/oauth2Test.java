package POSTAPI;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;




public class oauth2Test {
	static String accessToken;
	@BeforeMethod
	  public void getAccessToken() {
		RestAssured.baseURI="https://test.api.amadeus.com";
		 accessToken =given()
			.header("Content-Type","application/x-www-form-urlencoded")
			.formParam("grant_type","client_credentials")
			.formParam("client_id","E9lSBGRSz78ADLIAB28Kab3v9rcrIAuJ")
			.formParam("client_secret","GkRl54A8bMg5werg")
	    .when()
	      .post("/v1/security/oauth2/token")
	      .then()
	         .assertThat()
	           .statusCode(200)
	             .extract().path("access_token");
		System.out.println(accessToken);
	}
	
	@Test
	  public void getFlightInfoTest() {
		
	               
	//get flight info
	Response flightDatares=	given().log().all()
		  .header("Authorization","Bearer "+accessToken)	
		    .queryParam("origin", "PAR")
		    .queryParam("maxPrice", 200)
		    .when()
		       .get("/v1/shopping/flight-destinations")
	         .then().log().all()
	             .assertThat()
	               .statusCode(200)
	                 .and()
	                   .extract().response();
	JsonPath js = flightDatares.jsonPath();
	String type = js.get("data[0].type");
	System.out.println(type);
	}

}
