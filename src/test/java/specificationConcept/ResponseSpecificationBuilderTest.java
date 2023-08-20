package specificationConcept;


	
	import org.testng.annotations.Test;
	import static io.restassured.RestAssured.given;
    import io.restassured.RestAssured;
	import io.restassured.builder.ResponseSpecBuilder;
	import io.restassured.http.ContentType;
	import io.restassured.specification.ResponseSpecification;
	import static org.hamcrest.Matchers.*;

	public class ResponseSpecificationBuilderTest {
		
		public static ResponseSpecification get_res_spec_200_OK() {
			ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectHeader("Server", "cloudflare")
				.build();
			
			return res_spec_200_ok;
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
			
		public static ResponseSpecification get_res_spec_401_Auth_fail() {
			ResponseSpecification res_spec_401_AUTH_FAIL = new ResponseSpecBuilder()
				.expectStatusCode(401)
				.expectHeader("Server", "cloudflare")
				.build();
			
			return res_spec_401_AUTH_FAIL;
		}

		@Test
		public void get_user_res_200_spec_Test() {
			RestAssured.baseURI = "https://gorest.co.in";
			given()
				.header("Authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
					.when()
						.get("/public/v2/users")
							.then()
								.assertThat()
									.spec(get_res_spec_200_OK_with_body());
		}
		@Test
		public void get_user_res_401_auth_fail_spec_Test() {
			RestAssured.baseURI = "https://gorest.co.in";
			given()
				.header("Authorization", "Bearer 7286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
					.when()
						.get("/public/v2/users")
							.then()
								.assertThat()
									.spec(get_res_spec_401_Auth_fail());
		}
		
		
		
		
		

	}



	