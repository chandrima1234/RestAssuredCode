package multibody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyApiTest {
	
	@Test
	public void bodyWithTexttest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload= "hii this is chandrima";
			Response response=	RestAssured.given()
				.contentType(ContentType.TEXT)
				.body(payload)
				.when()
				.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());
	}
	
	@Test
	public void bodyWithJavaScripttest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload= "fucntion log(){\n"
				+ "let x =10\n;"
				+ "console.log(x)\n"
				+ "}\n";
			Response response=	RestAssured.given()
				.header("Content-Type","Application/JavaScript")
				.body(payload)
				.when()
				.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());
	}
	@Test
	public void bodyWithHTMtest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload= "<!DOCTYPE html>\r\n"
				+ "<html dir=\"ltr\" lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\" />\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "<title>Your Store</title>"
				+ "<head>"
				+ "</html>";
			Response response=	RestAssured.given()
				.contentType(ContentType.HTML)
				.body(payload)
				.when()
				.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());
	}
	@Test
	public void bodyWithXMLtest() {
		RestAssured.baseURI="http://httpbin.org";
		String payload= "<note>\r\n"
				+ "<script>window._wordtune_extension_installed = true;</script>\r\n"
				+ "<to>Tove</to>\r\n"
				+ "<from>Jani</from>\r\n"
				+ "<heading>Reminder</heading>\r\n"
				+ "<body>Don't forget me this weekend!</body>\r\n"
				+ "</note>";
			Response response=	RestAssured.given()
				.contentType(ContentType.XML)
				.body(payload)
				.when()
				.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());
	}
	
	
	
	@Test
	public void bodyWithmultiparttest() {
		RestAssured.baseURI="http://httpbin.org";
	Response response=	RestAssured.given()
				.contentType(ContentType.MULTIPART)
				.multiPart("name","testing")
				.multiPart("fileName",new File("/Users/Chandrima/OneDrive/Desktop/sample.pdf"))
				.when()
				.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());
	}
	
	@Test
	public void bodyWithbinarytest() {
		RestAssured.baseURI="http://httpbin.org";
	Response response=	RestAssured.given()
				.header("Content-Type","application/pdf")
				.body(new File("/Users/Chandrima/OneDrive/Desktop/sample.pdf"))
				.when()
				.post("/post");
				
			response.prettyPrint();
			System.out.println(response.statusCode());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
