package POSTAPI;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.CredentialClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookingauthPojoTest {
	
@Test	
	
public void getBookingAuthApiTest_Json_string() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		CredentialClass creds = new CredentialClass("admin","password123");
     String tokenId=    given()
        .contentType(ContentType.JSON)
        .body(creds)
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

}
