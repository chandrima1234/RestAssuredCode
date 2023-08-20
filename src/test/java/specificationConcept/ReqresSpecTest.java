package specificationConcept;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
public class ReqresSpecTest {
	
	public static RequestSpecification user_req_spec() {
		RequestSpecification requestSpec= new RequestSpecBuilder()
			    .setBaseUri("https://gorest.co.in")
			    .setContentType(ContentType.JSON)
			    .addHeader("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
		       .build();
		
		return requestSpec;
	}
	
	public static ResponseSpecification get_res_spec_200_OK_with_body() {
		ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.expectBody("$.size()",equalTo(10))
			.expectBody("id",hasSize(10))
			.build();
		
		return res_spec_200_ok;
	}
	
	@Test
	 public void getUser_with_req_Res_Spec_test() {
		given()
		  .spec(user_req_spec())
		    .get("/public/v2/users")
		    .then()
		      .assertThat()
		       .spec(get_res_spec_200_OK_with_body());
	}

}
