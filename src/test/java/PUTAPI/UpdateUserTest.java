package PUTAPI;

import com.user.api.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test; 

public class UpdateUserTest {
	
	//create user > post > user id
	//Update user > put > /user id 
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
		//return "apiautomation"+ UUID.randomUUID()+"@mail.com";
	}
	
	@Test
	public void updateUserTest() {
		
	 		RestAssured.baseURI = "https://gorest.co.in";
	 		
	 		User user = new User("Naveen", getRandomEmailId(), "male", "active");
	 		
	 		Response response = RestAssured.given().log().all()
	 					.contentType(ContentType.JSON)
	 					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	 					.body(user)
	 					.when().log().all()
	 						.post("/public/v2/users");
	 		
	 		Integer userId = response.jsonPath().get("id");
	 		System.out.println("user id : " + userId);
	 		
	 		System.out.println("=========================");
	 		user.setName("Chandrima");
	 		user.setStatus("inactive");
	 		
	 		
	 		//PUT and PAtch also work here
	 		RestAssured.given()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.body(user)
				.when()
					.put("/public/v2/users/" +userId)
					   .then().log().all()
					     .assertThat()
					       .statusCode(200)
					          .and()
					            .assertThat()
					               .body("id", equalTo(userId))
					                 .and()
					                   .body("name", equalTo(user.getName()))
					                     .and()
					                     .body("status",equalTo(user.getStatus()));
					                   
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	}

}
