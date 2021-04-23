package basicXmlValidation;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicXMLValidators {
	
	
	@Test(priority=1)
	void testSingleContent()
	{
		given()
		
		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
			
		.then()
			.body("CUSTOMER.ID",equalTo("15"))
			.log().all();
		
	}
	
	//2) Verifying Multiple contents in XML Response
	@Test(priority=2)
	void testMultipleContents()
	{
		given()
		
		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
			
		.then()
			.body("CUSTOMER.ID",equalTo("15"))
			.body("CUSTOMER.FIRSTNAME",equalTo("Bill"))
			.body("CUSTOMER.LASTNAME",equalTo("Clancy"))
			.body("CUSTOMER.STREET",equalTo("319 Upland Pl."))
			.body("CUSTOMER.CITY",equalTo("Seattle"));
				
	}
	
	
	//3) Verifying all the content in XML response in one go
	@Test(priority=3)
	void testMultipleContentsInOneGo()
	{
		given()
		
		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
			
		.then()
			.body("CUSTOMER.text()",equalTo("15BillClancy319 Upland Pl.Seattle"));
					
	}
	
	
	// Find values using XML Path in XML response
	
	@Test(priority=4)
	void testUsingXPath1()
	{
		 
		given()
		
		.when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
			
		.then()
			.body(hasXPath("/CUSTOMER/FIRSTNAME",containsString("Bill")));
	}
	
	
	@Test(priority=5)
	void testUsingXPath2()
	{
		 given()
			
			.when()
				.get("http://thomas-bayer.com/sqlrest/INVOICE/")
				
			.then()
				.body(hasXPath("/INVOICEList/INVOICE[text()='30']"))
		 		.log().all().extract();
	}
	
	@Test(priority=6)
	public void testWithDetachRootPathXML()
	{
		when()
			.get("http://thomas-bayer.com/sqlrest/CUSTOMER/15/")
		.then()
			.rootPath("CUSTOMER")
			.body("FIRSTNAME",is("Bill"))
			.body("LASTNAME",is("Clancy"))
					
			//.detachRootPath("CUSTOMER")
			
			.body("STREET",is("319 Upland Pl."))
			.body("CITY",is("Seattle"));
		
	}
	
	
	

}
