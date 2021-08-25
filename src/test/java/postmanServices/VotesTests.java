package postmanServices;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class VotesTests extends TestBase {

	
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
	
	logger.info("*********Started TC001_Get_All_Employees **********");
		
	RestAssured.baseURI = "https://api.thecatapi.com/v1/votes";
	httpRequest = RestAssured.given().headers("Content-Type", "application/json","x-api-key","17d94b92-754f-46eb-99a0-65be65b5d18f");
	response = httpRequest.request(Method.GET);
	
	
	}
			
	@Test
	void checkResposeBody()
	{
		logger.info("***********  Checking Respose Body **********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		System.out.println(responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
}
