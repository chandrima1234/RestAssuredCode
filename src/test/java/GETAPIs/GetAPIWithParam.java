package GETAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetAPIWithParam {
	
	@Test
	 public void getCircuitDataAPIWith_ApiTest() {
			RestAssured.baseURI = "http://ergast.com";
			given().log().all()
			.pathParam("year", "2016")
			 .when().log().all()
			    .get("/api/f1/{year}/circuits.json")
			      .then()
			         .assertThat()
			           .statusCode(200)
			             .and()
			               .body("MRData.CircuitTable.season", equalTo("2016"))
	                          .and()
	                             .body("MRData.CircuitTable.Circuits.circuitId",hasSize(21));
	
	}	
	
	
	//2016 ---21
	//2017--- 20
    @DataProvider
	public Object[][] getCircutYearData() {
		return new Object[][] {
			{"2016",21},
			{"2017",20},
			{"2023",22},
			{"1966", 9}
		};
	}
    
    
    
    
	@Test(dataProvider="getCircutYearData")
	 public void getCircuitDataAPIWith_DataProvider(String seasonyear, int totalCircuits) {
			RestAssured.baseURI = "http://ergast.com";
			given().log().all()
			.pathParam("year", seasonyear)
			 .when().log().all()
			    .get("/api/f1/{year}/circuits.json")
			      .then()
			         .assertThat()
			           .statusCode(200)
			             .and()
			               .body("MRData.CircuitTable.season", equalTo(seasonyear))
	                          .and()
	                             .body("MRData.CircuitTable.Circuits.circuitId",hasSize(totalCircuits));
	
	}
	
	
}
