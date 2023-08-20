package POSTAPI;

import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class BookinhAuthTest {
	
	@Test
	public void getBookingAuthApiTest_Json_string() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
     String tokenId=    given()
        .contentType(ContentType.JSON)
        .body("{\n"
            + "  \"username\" : \"admin\",\n"
            + " \"password\" :  \"password123\"\n"
           + "}")
        .when()
         .post("/auth")
           .then()
             .assertThat()
                .statusCode(200)
                  .extract()
                    .path("token");
     System.out.println(tokenId);
     Assert.assertNotNull(tokenId);
	}
	
	
	@Test
	public void getBookingAuthApiTest_withJson_body() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
     String tokenId=    given()
        .contentType(ContentType.JSON)
        .body(new java.io.File("./src/test/resources/TestData/Basicauth.json"))
        .when()
         .post("/auth")
           .then()
             .assertThat()
                .statusCode(200)
                  .extract()
                    .path("token");
     System.out.println(tokenId);
     Assert.assertNotNull(tokenId);
	}
	
	
	//post === add a user - user id 123 -assert(201,body)
	//get > get a user >/users/123 > 200, userid =123
	
	
	@Test
	public void addUserTest() {
	 RestAssured.baseURI = "https://gorest.co.in";
	int userId=  given()
	  .contentType(ContentType.JSON)
	  .body(new File("./src/test/resources/TestData/addUser.json"))
      .header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")	
	  .when()
	    .post("/public/v2/users/")
	    .then()
	      .assertThat()
	        .statusCode(201)
	         .and()
	         .body("name", equalTo("Yoginder Ahuja IV"))
	          .extract()
	           .path("id");
	
	System.out.println("user id>>>>" +userId);
	given()
	.header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")	
	  .when().log().all()
	    .get("/public/v2/users/"+userId)
	    .then()
	      .assertThat()
	        .statusCode(200)
	           .and()
	             .body("id", equalTo(userId));
	
	}
	
	
	
	

}
