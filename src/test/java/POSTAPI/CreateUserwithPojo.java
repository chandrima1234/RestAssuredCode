package POSTAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertNotEqualsDeep;



import org.testng.annotations.Test;

import Pojo.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserwithPojo {
	
	public static String getRandomEmailId() {
		return "api"+System.currentTimeMillis()+"gmail.com";
	}
	@Test
	public void addUserTest() {
	
		RestAssured.baseURI = "https://gorest.co.in";
	   User user = new User("Chandrima", getRandomEmailId(), "female", "active");
		
		
		int userId=  given()
	  .contentType(ContentType.JSON)
	  .body(user)
      .header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")	
	  .when().log().all()
	    .post("/public/v2/users/")
	    .then().log().all()
	      .assertThat()
	        .statusCode(201)
	         .and()
	         .body("name", equalTo(user.getName()))
	          .extract()
	           .path("id");
	
	System.out.println("user id>>>>" +userId);
	given()
	.header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")	
	  .when().log().all()
	    .get("/public/v2/users/"+userId)
	    .then().log().all()
	      .assertThat()
	        .statusCode(200)
	           .and()
	             .body("id", equalTo(userId))
	         .and()
	          .body("status",equalTo(user.getStatus()));
	
	}
	
	

}
