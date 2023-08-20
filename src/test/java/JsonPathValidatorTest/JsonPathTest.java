package JsonPathValidatorTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {
	
	@Test
	 public void getCircuitDataAPIWith_ApiTest() {
			RestAssured.baseURI = "http://ergast.com";
			Response response = given()
					.when()
			   .get("/api/f1/2017/circuits.json");
	      
		String jsonResponse = response.asPrettyString();	
	   System.out.println(jsonResponse);
	   int totalCircuits= JsonPath.read(jsonResponse, "$..MRData.CircuitTable.Circuits.length()");
	   System.out.println(totalCircuits);
	   
	   List<String> countryList= JsonPath.read(jsonResponse, "$..Circuits..country");
	  System.out.println(countryList.size());
	  System.out.println(countryList);
	}
	
	
	@Test
	public void getProductTest() {
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response = given()
				.when()
				   .get("/products");
		String jsonResponse = response.asPrettyString();	
		   System.out.println(jsonResponse); 
		   
		   List<Float> rateLessThanThree=JsonPath.read(jsonResponse,"$[?(@.rating.rate < 3)].rating.rate");
	       System.out.println(rateLessThanThree);
	       
	       System.out.println("==========================");
	       // with two attributes
	      List<Map<String,Object>>jewelleryList=  JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery' && @.rating.rate >=2 && @.rating.rate <= 4)].[\"title\",\"price\"]");
	      System.out.println(jewelleryList);
	      
	      for(Map<String,Object> product:jewelleryList )
	      {
	    	  String title = (String)product.get("title");
	    	 Object price = (Object)product.get("price");
	    	 System.out.println("title:    " + title);
	    	 System.out.println("price:  " +price);
	    	 System.out.println("==========================");
	      }
	      
	      
	      //with three attributes
	      
	      List<Map<String,Object>>jewelleryList2=  JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery' && @.rating.rate >=2 && @.rating.rate <= 4)].[\"title\",\"price\",\"id\"]");
	      System.out.println(jewelleryList);
	      
	      for(Map<String,Object> product:jewelleryList2 )
	      {
	    	  String title = (String)product.get("title");
	    	 Object price = (Object)product.get("price");
	    	 Integer id = (Integer)product.get("id");
	    	 System.out.println("title:    " + title);
	    	 System.out.println("price:  " +price);
	    	 System.out.println("Id   " +id);
	    	 System.out.println("==========================");
	      }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}