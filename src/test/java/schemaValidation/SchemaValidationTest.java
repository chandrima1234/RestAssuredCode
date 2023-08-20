package schemaValidation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class SchemaValidationTest {
	
	@Test
	public void createUserAPISchemaValidatonTest() {
		
		RestAssured.baseURI="https://gorest.co.in";
	         given()
	        .contentType(ContentType.JSON)
	        .body(new java.io.File("./src/test/resources/TestData/addUSer.json"))
	        .header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")	
	  	  .when().log().all()
	  	    .post("/public/v2/users/")
	  	    .then().log().all()
	  	      .assertThat()
	  	        .statusCode(201)
	  	        .and()
	  	        .body(matchesJsonSchemaInClasspath("getUserSchema.json"));
		
	}
	
	

}
