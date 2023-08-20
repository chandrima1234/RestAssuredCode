package XML_APIs;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetCircuitAPITest {
	
	@Test
	public void xmlTest() {
		RestAssured.baseURI="https://ergast.com";
		Response response = RestAssured.given()
		 .when()
		 .get("/api/f1/2017/circuits.xml")
		 .then()
		 .extract().response();
		
		String responseBody = response.body().asString();
		System.out.println(responseBody);
		
		//create object of xmlPath
		
		XmlPath xmlPath = new XmlPath(responseBody);
		List<String> circuitNames = xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		 for(String e:circuitNames) {
			 System.out.println(e);
		 }
		 
		 List<String> circuitIds = xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");
		 for(String e:circuitIds) {
			 System.out.println(e);
		 }
		 
		 //fetch the locality where curcuitId= americas
		 
		 String locality = xmlPath.get("**.findAll{it.@circuitId =='americas'}.Location.Locality").toString();
		 //deep scanning of XML method >> grovvy scripting 
		 System.out.println(locality);
		 
		String latVal=  xmlPath.get("**.findAll{it.@circuitId == 'bahrain'}.Location.@lat");
		String longVal= xmlPath.get("**.findAll{it.@circuitId == 'bahrain'}.Location.@lat");
		 System.out.println(latVal);
		 System.out.println(longVal);
		 
		 //fetch locality where circuit =americas and bahrain
		 String circuitName = xmlPath.get("**.findAll{it.@circuitId =='americas' || it.@circuitId =='bahrain'}.CircuitName").toString();
		 System.out.println(circuitName);
		 
		 //
		 
		 
		 
		 
		 
	}

}
