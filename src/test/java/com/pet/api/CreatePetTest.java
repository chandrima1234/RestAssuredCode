package com.pet.api;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.tag;
import com.pet.api.PetLombok.tag.tagBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePetTest {
	
	
	@Test
	public void createPettest() {
		RestAssured.baseURI="https://petStore.swagger.io";
		
		Category category = new Category(1,"Dog");
		List<String> photoUrls= Arrays.asList("https://dog.com","https://dog1.com");
		tag tag1 = new tag(5,"red");
		List<tag> tags= Arrays.asList(tag1);
	
				
		PetLombok pet = new PetLombok(300, category, "Ronny", photoUrls, tags, "available");
				Response response = RestAssured.given()
					.contentType(ContentType.JSON)
					.body(pet)
					.when()
					.post("/v2/pet");
				
				System.out.println(response.statusCode());
				response.prettyPrint();
				
				//De-serialize
				
				ObjectMapper mapper = new ObjectMapper();
				try {
					PetLombok petRes= mapper.readValue(response.getBody().asString(), PetLombok.class);
				     System.out.println(petRes.getId());
				     System.out.println(petRes.getName());
				     System.out.println(petRes.getCategory().getId());
				     System.out.println(petRes.getCategory().getName());
				     System.out.println(petRes.getPhotoUrls());
				     System.out.println(petRes.getTags().get(0).getId());
				     System.out.println(petRes.getTags().get(0).getName());
				     
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	
	@Test
	public void createPet_withBuilderPattern_test() {
		
	RestAssured.baseURI="https://petStore.swagger.io";
	
	Category category = new Category.CategoryBuilder()
	.id(400)
	.name("Animal")
	.build();
	
	tag tag1= new tag.tagBuilder()
	.id(50)
	.name("red")
	.build();
	
	tag tag2= new tag.tagBuilder()
			.id(51)
			.name("red")
			.build();
	
	PetLombok pet = new PetLombok.PetLombokBuilder()
	.id(5000)
	.category(category)
	.name("robby")
	.photoUrls(Arrays.asList("https://cat.com","https://dog.com"))
	.tags(Arrays.asList(tag1,tag2))
	.status("available")
	.build();
			
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(pet)
				.when()
				.post("/v2/pet");
			
			System.out.println(response.statusCode());
			response.prettyPrint();
			
			//De-serialize
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				PetLombok petRes= mapper.readValue(response.getBody().asString(), PetLombok.class);
			     System.out.println(petRes.getId());
			     System.out.println(petRes.getName());
			     System.out.println(petRes.getCategory().getId());
			     System.out.println(petRes.getCategory().getName());
			     System.out.println(petRes.getPhotoUrls());
			     System.out.println(petRes.getTags().get(0).getId());
			     System.out.println(petRes.getTags().get(0).getName());
			     
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
}


}
