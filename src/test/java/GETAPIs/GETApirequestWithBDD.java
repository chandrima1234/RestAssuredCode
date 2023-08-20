package GETAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GETApirequestWithBDD {

	@Test
	public void getProductTest() {
		given().log().all().when().log().all().get("https://fakestoreapi.com/products").then().log().all().assertThat()
				.statusCode(200).contentType(ContentType.JSON).and().header("Connection", "keep-alive").and()
				.body("$.size()", equalTo(20)).and().body("id", is(notNullValue())).and()
				.body("title", hasItem("Mens Casual Slim Fit"));
	}

	@Test
	public void getUserAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
				.header("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
				.when().log().all().get("/public/v2/users/").then().log().all().assertThat().statusCode(200)
				.contentType(ContentType.JSON).and().body("$.size()", equalTo(10));

	}

	@Test
	public void getProductDataWithQueryParamsTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		given().log().all().queryParam("limit", 5).when().get("/products").then().log().all().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON);

	}

	@Test
	public void getProductDataAPIwithExtract_body() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all().queryParam("limit", 5).when().get("/products");
		JsonPath js = response.jsonPath();

		// get the id of the first product
		int firstProductid = js.getInt("[0].id");
		System.err.println("first product id  " + firstProductid);
		String firstProductTitle = js.getString("[0].title");
		System.out.println("first product title   " + firstProductTitle);
		float price = js.getFloat("[0].price");
		System.out.println("price   " + price);

		int count = js.getInt("[0].rating.count");
		System.out.println("Count is " + count);
	}
	
	@Test
	public void getProductDataAPIwithExtract_body_withArray() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all().queryParam("limit", 10).when().get("/products");
		JsonPath js = response.jsonPath(); //json array
        List<Integer> idList= js.getList("id",Integer.class);
        List<String> titleList= js.getList("title");
        List<Object> rateList= js.getList("rating.rate");
        System.out.println(rateList.size());
        
        for(int i=0;i<idList.size();i++) {
        	int id = idList.get(i);
        	String title = titleList.get(i);
        	Object rate= rateList.get(i);
        	
        	System.out.println("ID   " +id + " "+"Title   " +title + " " +"Rate  " +rate) ;
        }
        
	}

	
	@Test
	public void getProductDataAPIwithExtract_body_withJson() {
		RestAssured.baseURI = "https://gorest.co.in";
		Response response =given().log().all()
		.header("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
		   .when().log().all()
		     .get("/public/v2/users/3684305");
		JsonPath js= response.jsonPath();
		System.out.println(js.getInt("id"));
		System.out.println(js.getString("email"));
		
        
	}
	
	
	@Test
	public void getProductDataAPIwithExtract_body_withJson_extract() {
		RestAssured.baseURI = "https://gorest.co.in";
		/*int userId  =given().log().all()
		.header("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
		   .when().log().all()
		     .get("/public/v2/users/3684305")
		        .then()
		          .extract().path("id");
		System.out.println(userId);
		*/
		Response response  =given().log().all()
				.header("authorization", "Bearer 9bd6eab47286e6d7d59145af9abe3e64ca83bfdbdbee8b80c5d593b356c75d70")
				   .when().log().all()
				     .get("/public/v2/users/3684305");
		                
		                 
		int userId= response.then().extract().path("id");
		String userEmail= response.then().extract().path("email");
		System.out.println(userId);
		System.out.println(userEmail);
		
		
		
		
		
		
		
        
	}
	
	
	
	
	
}
