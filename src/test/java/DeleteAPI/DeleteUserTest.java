package DeleteAPI;

import org.testng.annotations.Test;

import com.user.api.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

//1. Post -- create user 
//2. delete/user id  - 204
//3 get -get user /userid -404
public class DeleteUserTest {
	public static String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@mail.com";
		// return "apiautomation"+ UUID.randomUUID()+"@mail.com";
	}

	@Test
	public void DeleteUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Naveen", getRandomEmailId(), "male", "active");

		Response response = RestAssured.given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.body(user).when().log().all().post("/public/v2/users");
		response.prettyPrint();

		Integer userId = response.jsonPath().get("id");
		System.out.println("user id : " + userId);

		System.out.println("=========================");
		// DELete
		RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().delete("/public/v2/users/" + userId).then().log().all()
				.assertThat().statusCode(204);
		// GEt
		RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().delete("/public/v2/users/" + userId).then().log().all()
				
				.assertThat().statusCode(404)
				.assertThat()
				 .body("message", equalTo("Resource not found"));
				;

	}

}
