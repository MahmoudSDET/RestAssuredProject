package Demo8_ReadResponseInDiffWays;


	
	import org.testng.annotations.Test;

	import io.restassured.http.ContentType;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;

	public class Demo8_ReadResponseInDiffWays {

		@Test(priority=1)
		public void testGetResponseAsString()
		{
			/*String response=
				when()
					.get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad")
					.asString();*/
			
			String response=get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").asString();
			
			System.out.println("My Response is:"+response);
		}
		
		@Test(priority=2)
		public void testExtractDetailsUsingPath()
		{
			String href=
			given()
			
				.when()
					.get("http://jsonplaceholder.typicode.com/photos/1")
					.then()
						.contentType(ContentType.JSON)
						.body("albumId", is(1))
						.extract().path("url");
			
			System.out.println("Extracted URL value is:"+href);
		}
		
		@Test(priority=3)
		public void testExtractPathinOneLine()
		{
			//Approach1
			
			String href1=get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
			System.out.println("Fetched Thumbnail URL1 : --->"+href1);
			
			
			//Appraoch2
			
			String href2=get("http://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("thumbnailUrl");
			System.out.println("Fetched Thumbnail URL2 : --->"+href2);
			
			when()
				.get(href1)
			.then()
				.statusCode(200);
		
		}
		
		
		//Extract details as Response for Further use
		
		@Test(priority=4)
		public void testExtractDetailsUsingResponse()
		{
			Response response=given()
			
			.when()
			
				.get("http://jsonplaceholder.typicode.com/photos/1")
			.then()
				.extract().response();
			
			System.out.println("Content Type of Response:"+response.contentType());
			System.out.println("Title from the Response:"+response.path("title"));
			System.out.println("Title from the Response:"+response.getStatusCode());
				
		}

}
