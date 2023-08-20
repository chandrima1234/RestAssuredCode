package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilderTest {

	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec= new RequestSpecBuilder()
			    .setBaseUri("https://gorest.co.in")
			    .setContentType(ContentType.JSON)
			    .addHeader("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
		       .build();
		
		return requestSpec;
	}
	
	@Test
	public void getUser_With_Request_Spec() {
		RestAssured.given().log().all()
		   .spec(user_req_spec())
		      .get("/public/v2/users")
		         .then()
		            .statusCode(200);
	}
	
	@Test
	public void getUser_With_paramRequest_Spec() {
		RestAssured.given().log().all()
		  .queryParam("name", "chandrima")
		  .queryParam("status", "active")
		   .spec(user_req_spec())
		      .get("/public/v2/users")
		         .then()
		            .statusCode(200);
	}
}
