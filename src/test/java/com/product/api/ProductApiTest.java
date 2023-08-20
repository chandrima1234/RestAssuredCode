package com.product.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import GETAPIs.productLombok;

public class ProductApiTest {
	
	@Test
	public void getProductTest_with_POJO() {
		RestAssured.baseURI ="https://fakestoreapi.com";
	   Response response = given()
		 .when()
		   .get("/products");
	   
	   //json to pojo mapping:Deserialization
	   
	   ObjectMapper mapper = new ObjectMapper();
	   try {
	   product product[]= mapper.readValue(response.getBody().asString(),product[].class);
	   
	   for(product p:product) {
		   System.out.println("ID:   " +p.getId());
		   System.out.println("title:   " +p.getTitle());
		   System.out.println("Description:   " +p.getDescription());
		   System.out.println("price:   " +p.getPrice());
		   System.out.println("Cateogy:   " +p.getCategory());
		   System.out.println("Image:   " +p.getImage());
		   System.out.println("Rate " +p.getRating().getRate());
		   System.out.println("Rate " +p.getRating().getCount());
		   System.out.println("==========================");
		   
	   }
	   
	   }
	   catch(JsonMappingException e) {
		   e.printStackTrace();
	   }
	   catch(JsonProcessingException e) {
		   e.printStackTrace();
	   }
	   
		  
	}
	
	@Test
	public void getProductTest_with_POJO_lombok() {
		RestAssured.baseURI ="https://fakestoreapi.com";
	   Response response = given()
		 .when()
		   .get("/products");
	   
	   //json to pojo mapping:Deserialization
	   
	   ObjectMapper mapper = new ObjectMapper();
	  
	  
	try {
		 productLombok product[] = mapper.readValue(response.getBody().asString(),productLombok[].class);
	
	   
	   for(productLombok p: product) {
		   System.out.println("ID:   " + p.getId());
		   System.out.println("title:   " +p.getTitle());
		   System.out.println("Description:   " +p.getDescription());
		   System.out.println("price:   " +p.getPrice());
		   System.out.println("Cateogy:   " +p.getCategory());
		   System.out.println("Image:   " +p.getImage());
		   System.out.println("Rate " +p.getRating().getRate());
		   System.out.println("Rate " +p.getRating().getCount());
		   System.out.println("==========================");
		   
	   }
	   
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }   
	   
	   
	   
	   
	   
	   
	 
	   }
